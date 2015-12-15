package com.getfivestars.api;

public abstract class Client {

    public static final String URL = "https://getfivestars.com/api";

    protected Request request;

    public Client(Request request) {
        this.request = request;
    }

    abstract Response sendRequest();
}
 
