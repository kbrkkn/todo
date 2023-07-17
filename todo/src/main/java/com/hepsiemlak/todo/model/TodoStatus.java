package com.hepsiemlak.todo.model;

public enum TodoStatus {
    TODO("TODO"),
    DONE("DONE");

    private String statusValue;

    private TodoStatus(final String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
