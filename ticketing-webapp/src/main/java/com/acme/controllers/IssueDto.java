package com.acme.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String title;
    private String description;
    private String state;
    private String attachment;
    private List<IssueHistoryDto> history;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public List<IssueHistoryDto> getHistory() {
        return history;
    }

    public void setHistory(List<IssueHistoryDto> history) {
        this.history = history;
    }

}