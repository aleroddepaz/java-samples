package org.arp.example;

import java.util.List;
import java.util.stream.Collectors;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Verticle for a REST endpoint that stores to-dos in a database.
 */
public class TodoVerticle extends AbstractVerticle {

    static final int DEFAULT_PORT = 9090;

    private HttpServer httpServer;
    private MongoClient mongoClient;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        this.httpServer = vertx.createHttpServer();
        this.mongoClient = MongoClient.createShared(vertx, config());

        initHttpServer().setHandler(handler -> {
            if (handler.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(handler.cause());
            }
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        mongoClient.close();
        httpServer.close(stopFuture.completer());
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
        mongoClient.find("todos", new JsonObject(), handler -> {
            if (handler.failed()) {
                context.fail(500);
            } else {
                List<Todo> todos = handler.result().stream().map(TodoMapper::fromJson).collect(Collectors.toList());
                context.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(todos));
            }
        });
    }

    void createTodo(final RoutingContext context) {
        Todo todo = TodoMapper.fromString(context.getBodyAsString());
        mongoClient.insert("todos", TodoMapper.toJson(todo), handler -> {
            if (handler.failed()) {
                context.fail(500);
            } else {
                todo.setId(handler.result());
                context.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(todo));
            }
        });
    }

}