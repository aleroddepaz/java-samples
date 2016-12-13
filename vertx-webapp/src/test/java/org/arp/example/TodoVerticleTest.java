package org.arp.example;

import static org.arp.example.TodoVerticle.DEFAULT_PORT;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class TodoVerticleTest {

    private Vertx vertx;

    @Before
    public void setUp(TestContext context) throws IOException {
        JsonObject config = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "my_db");
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        vertx = Vertx.vertx();
        vertx.deployVerticle(TodoVerticle.class.getName(), options, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testGetTodos(TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient()
            .get(DEFAULT_PORT, "localhost", "/todos")
            .handler(response -> {
                context.assertEquals(200, response.statusCode());
                async.complete();
            }).end();
    }

    @Test
    public void testPostTodo(TestContext context) {
        Todo todo = new Todo();
        todo.setTitle("test");

        final Async async = context.async();
        vertx.createHttpClient()
            .post(DEFAULT_PORT, "localhost", "/todos")
            .handler(response -> {
                context.assertEquals(200, response.statusCode());
                async.complete();
            }).putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encode(todo));
    }

}