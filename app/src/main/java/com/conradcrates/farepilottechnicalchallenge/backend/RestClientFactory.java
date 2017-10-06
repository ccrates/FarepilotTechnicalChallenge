package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

public class RestClientFactory {

    private static RestClientFactory instance;

    public static RestClientFactory getInstance(){
        if(instance == null){
            instance = new RestClientFactory();
        }
        return instance;
    }


    private IRestClient restClient;

    private RestClientFactory(){
        restClient = new StubbedRestClient();
    }

    public IRestClient getRestClient(){
        return restClient;
    }
}