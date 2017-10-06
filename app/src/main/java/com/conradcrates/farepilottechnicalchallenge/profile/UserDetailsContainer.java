package com.conradcrates.farepilottechnicalchallenge.profile;

/**
 * Created by Conrad on 06/10/2017.
 */

public class UserDetailsContainer {

    private static UserDetailsContainer instance;

    public static UserDetailsContainer getInstance(){
        if(instance == null){
            instance = new UserDetailsContainer();
        }
        return instance;
    }



    private UserDetailsContainer(){

    }
}
