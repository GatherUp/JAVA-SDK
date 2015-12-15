package com.getfivestars.api;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

public class NetClient extends Client {

    public NetClient(Request request) {
        super(request);
    }

    // http://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
    public Response sendRequest() {
        try {
            URL url = new URL(this.URL + this.request.getAction());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = (new JSONObject(this.request.getParameters())).toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String json = "";
            while ((output = br.readLine()) != null) {
                json += output;
            }

            conn.disconnect();
            
            return new Response(Json.jsonToHashMap(json));
        } catch (Exception e) {
        }
        
        return new Response(new HashMap<String, String>());
    }
    
} 
