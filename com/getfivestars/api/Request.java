package com.getfivestars.api;

import java.util.HashMap;

public class Request {

    protected HashMap<String, String> request;
    protected String action;

    public Request(String action, HashMap<String, String> request) {
        this.request = request;
        this.action = action;
    }

    public void set(String name, String value) {
       this.request.put(name, value);
    }

    public String get(String name) {
        if (this.request.containsKey(name)) {
            return this.request.get(name);
        }
        return "";
    }

    public HashMap<String, String> getParameters() {
        return this.request;
    }
    
    public String getAction() {
        return this.action;
    }

}
