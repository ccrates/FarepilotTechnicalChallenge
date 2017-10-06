package com.conradcrates.farepilottechnicalchallenge.backend;

import java.util.HashMap;

/**
 * Created by Conrad on 06/10/2017.
 */

public class NetworkResponse {

    private HashMap<String, String> responses = new HashMap<>();

    public void addNewResponse(String key, String value){
        responses.put(key, value);
    }

    public String getValue(String key){
        return responses.get(key);
    }
}