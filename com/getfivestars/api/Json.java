package com.getfivestars.api;

import java.util.Iterator;
import java.util.HashMap;
import org.json.JSONObject;

public class Json {

    // http://stackoverflow.com/questions/21720759/convert-a-json-string-to-a-hashmap#answer-24012023
    public static HashMap<String, String> jsonToHashMap(String json) {
        JSONObject object = new JSONObject(json);
        HashMap<String, String> map = new HashMap<String, String>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            String value = object.get(key).toString();
            map.put(key, value);
        }
        
        return map;
    }

}
