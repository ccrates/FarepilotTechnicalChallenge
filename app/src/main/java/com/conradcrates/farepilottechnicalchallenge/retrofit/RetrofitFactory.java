package com.conradcrates.farepilottechnicalchallenge.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Conrad on 08/10/2017.
 */

public class RetrofitFactory {

    private final String SERVER_URL = "";

    private static RetrofitFactory instance;

    public static RetrofitFactory getInstance(){
        if(instance == null){
            instance = new RetrofitFactory();
        }
        return instance;
    }

    private RetrofitFactory(){}


    private RetrofitClient client;

    public RetrofitClient getRestClient(){
        if(client != null){
            return client;
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        client = retrofit.create(RetrofitClient.class);
        return client;
    }
}