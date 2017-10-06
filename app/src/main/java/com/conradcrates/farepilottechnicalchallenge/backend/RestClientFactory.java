package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

public class RestClientFactory {

    public IRestClient getRestClient(){
        return new StubbedRestClient();
    }
}