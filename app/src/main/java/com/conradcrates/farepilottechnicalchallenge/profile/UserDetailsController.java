package com.conradcrates.farepilottechnicalchallenge.profile;

/**
 * Created by Conrad on 06/10/2017.
 */

public class UserDetailsController {

    private static UserDetailsContainer instance;

    public static UserDetailsContainer getUserDetails(){
        if(instance == null){
            instance = new UserDetailsContainer();
        }
        return instance;
    }



    private UserDetailsController(){}
}