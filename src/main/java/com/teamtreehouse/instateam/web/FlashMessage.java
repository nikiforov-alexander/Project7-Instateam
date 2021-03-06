package com.teamtreehouse.instateam.web;

/**
 * Created by GoranB on 2017-01-26.
 */
public class FlashMessage {
    private String message;
    private Status status;

    public FlashMessage(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public static enum Status{
        SUCCESS, FAILURE
    }
}
