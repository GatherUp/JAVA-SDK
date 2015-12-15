package com.getfivestars.api;

import java.util.HashMap;

public class Response {

    protected HashMap<String, String> response;
    protected String errorMessage;
    protected Integer errorCode;

    public Response(HashMap<String, String> response) {
        if(!response.containsKey("errorCode") || !response.containsKey("errorMessage")) {
            response.put("errorCode", "-1");
            response.put("errorMessage", "Unknown error");
        }
        
        this.errorMessage = response.get("errorMessage");
        this.errorCode = Integer.parseInt(response.get("errorCode"));
        
        response.remove("errorMessage");
        response.remove("errorCode");
        this.response = response;
    }

    public Boolean getStatus() {
        return this.getErrorCode() == 0;
    }
    
    public Integer getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public HashMap<String, String> getResponse() {
        return response;
    }
}
