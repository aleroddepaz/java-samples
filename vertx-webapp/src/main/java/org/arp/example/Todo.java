package org.arp.example;

import java.io.Serializable;

import io.vertx.core.json.JsonArray;

/**
 * Entity class that represents a to-do.
 */
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Todo fromJson(JsonArray json) {
        Todo todo = new Todo();
        todo.setId(json.getLong(0));
        todo.setTitle(json.getString(1));
        return todo;
    }

}