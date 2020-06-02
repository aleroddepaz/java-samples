package com.acme.controllers;

import java.io.Serializable;
import java.util.Date;

public class IssueHistoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String state;
    private final Date date;

    public IssueHistoryDto(String state, Date date) {
        this.state = state;
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

}