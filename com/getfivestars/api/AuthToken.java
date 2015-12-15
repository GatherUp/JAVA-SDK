package com.getfivestars.api;

import java.util.HashMap;
import java.util.Arrays;
import java.security.MessageDigest;

public class AuthToken {

    protected String clientId;
    protected String privateKey;

    public AuthToken(String clientId, String privateKey) {
        this.clientId = clientId;
        this.privateKey = privateKey;
    }

    public void signRequest(Request request) {
        request.set("clientId", this.clientId);
        request.set("hash", this.generateHash(this.privateKey, request.getParameters()));
    }

    // http://stackoverflow.com/questions/5531455/how-to-encode-some-string-with-sha256-in-java#answer-11009612
    protected static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    protected String generateHash(String privateKey, HashMap<String, String> parameters) {
        String token = "";
        Object[] keys = parameters.keySet().toArray();
        Arrays.sort(keys);
        
        for(Object key : keys) {
            token += key + parameters.get(key);
        }
        
        return this.sha256(privateKey + token);
    }

}
 