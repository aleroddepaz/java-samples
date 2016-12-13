package org.arp.example;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class TodoMapper {
    
    private TodoMapper() {}

    public static Todo fromJson(JsonObject json) {
        Todo todo = new Todo();
        todo.setId(json.getString("_id"));
        todo.setTitle(json.getString("title"));
        return todo;
    }
    
    public static Todo fromString(String string) {
        return Json.decodeValue(string, Todo.class);
    }

    public static JsonObject toJson(Todo todo) {
        return new JsonObject().put("title", todo.getTitle());
    }

}
