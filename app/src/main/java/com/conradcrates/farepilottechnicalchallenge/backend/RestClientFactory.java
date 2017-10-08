package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

public class RestClientFactory {

    private final boolean STUBBED_CLIENT = true;

    private static RestClientFactory instance;

    public static RestClientFactory getInstance(){
        if(instance == null){
            instance = new RestClientFactory();
        }
        return instance;
    }


    private IRestClient restClient;

    private RestClientFactory(){
        if(STUBBED_CLIENT) {
            restClient = new StubbedRestClient();
        } else {
            restClient = new RestClient();
        }
    }

    public IRestClient getRestClient(){
        return restClient;
    }
}