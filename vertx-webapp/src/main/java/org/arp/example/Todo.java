package org.arp.example;

import java.io.Serializable;

/**
 * Entity class that represents a to-do.
 */
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}