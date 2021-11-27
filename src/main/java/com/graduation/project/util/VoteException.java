package com.graduation.project.util;

public class VoteException extends Exception {
    private String message = "Vote is already exist! Come tomorrow!";

    public VoteException() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
