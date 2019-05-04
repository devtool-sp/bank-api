package com.garant.dev.util;

public class ChatRoom {

    public enum Status {
        SUCCESS, FAILURE
    }

    private Status status;
    private String message = null;
    private String data = null;

    public ChatRoom(Status status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ChatRoom(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public ChatRoom(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("status = %s message = %s data = %s",
                status, message, data);
    }

}
