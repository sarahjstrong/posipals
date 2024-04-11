package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class Message {
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String location;
    @NotNull
    private String message;

    public Message() {
    }

    public Message(int id, String username, String location, String message) {
        this.id = id;
        this.username = username;
        this.location = location;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
