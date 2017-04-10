package com.codecool.controller;

public enum  ResponseEnum {
    JOB_SAVED("Job successfully saved."),
    ACCESS_DENIED("Invalid api key was given.");

    private final String status;

    ResponseEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
