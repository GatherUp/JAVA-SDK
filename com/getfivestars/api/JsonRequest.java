package com.getfivestars.api;

import java.util.HashMap;

public class JsonRequest extends Request {

    public JsonRequest(String action, String json) {
        super(action, Json.jsonToHashMap(json));
    }

}
