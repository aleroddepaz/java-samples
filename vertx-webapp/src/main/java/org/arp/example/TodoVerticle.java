package org.arp.example;

import java.util.List;
import java.util.stream.Collectors;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Verticle for a REST endpoint that stores to-dos in a database.
 */
public class TodoVerticle extends AbstractVerticle {

    static final int DEFAULT_PORT = 9090; 

    private HttpServer httpServer;
    private JDBCClient jdbcClient;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        this.httpServer = vertx.createHttpServer();
        this.jdbcClient = JDBCClient.createShared(vertx, config());

        CompositeFuture.all(initJdbcClient(), initHttpServer()).setHandler(handler -> {
            if (handler.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(handler.cause());
            }
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        jdbcClient.close();
        httpServer.close(stopFuture.completer());
    }

    private Future<Void> initJdbcClient() {
        Future<Void> future = Future.future();
        jdbcClient.getConnection(handler -> {
            if (handler.failed()) {
                future.fail(handler.cause());
            } else {
                handler.result().execute("CREATE TABLE IF NOT EXISTS Todos (id INTEGER IDENTITY, title VARCHAR(25))", future.completer()).close();
            }
        });
        return future;
    }

    private Future<HttpServer> initHttpServer() {
        Router router = Router.router(vertx);
        router.route("/todos*").handler(BodyHandler.create());
        router.get("/todos").handler(this::findAllTodos);
        router.post("/todos").handler(this::createTodo);

        Future<HttpServer> future = Future.future();
        httpServer.requestHandler(router::accept);
        httpServer.listen(config().getInteger("http.port", DEFAULT_PORT), future.completer());
        return future;
    }

    void findAllTodos(final RoutingContext context) {
        jdbcClient.getConnection(asyncConn -> {
            if (asyncConn.failed()) {
                context.fail(500);
            } else {
                asyncConn.result().query("SELECT id, title FROM Todos", handler -> {
                    if (handler.failed()) {
                        context.fail(500);
                    } else {
                        List<Todo> todos = handler.result().getResults().stream().map(Todo::fromJson).collect(Collectors.toList());
                        context.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(todos));
                    }
                }).close();
            }
        });
    }

    void createTodo(final RoutingContext context) {
        Todo todo = Json.decodeValue(context.getBodyAsString(), Todo.class);
        JsonArray parms = new JsonArray().add(todo.getTitle());
        jdbcClient.getConnection(asyncConn -> {
            if (asyncConn.failed()) {
                context.fail(500);
            } else {
                asyncConn.result().updateWithParams("INSERT INTO Todos (title) VALUES (?)", parms, handler -> {
                    if (handler.failed()) {
                        context.fail(500);
                    } else {
                        todo.setId(handler.result().getKeys().getLong(0));
                        context.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(todo));
                    }
                }).close();
            }
        });
    }

}