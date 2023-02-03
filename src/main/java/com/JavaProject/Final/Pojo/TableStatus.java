package com.JavaProject.Final.Pojo;

public enum TableStatus {
    AVAIABLE("Avaiable"),
    UNAVAIABLE("Unavaiable");

    private final String statusText;
    TableStatus(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }
}