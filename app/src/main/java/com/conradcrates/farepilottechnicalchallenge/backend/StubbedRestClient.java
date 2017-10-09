package com.conradcrates.farepilottechnicalchallenge.backend;

import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;

/**
 * Created by Conrad on 06/10/2017.
 */

public class StubbedRestClient implements IRestClient {

    private String email;

    @Override
    public void newSession(String email, String password, NetworkCallback callback) {
        this.email = email;
        if(callback != null){
            NetworkResponse response = new NetworkResponse();
            response.addNewResponse(NetworkResponseConstants.USER_ID, "1");
            callback.onSuccess(response);
        }
    }

    @Override
    public void getUserDetails(NetworkCallback callback) {
        if(callback != null) {
            NetworkResponse response = new NetworkResponse();
            response.addNewResponse(NetworkResponseConstants.EMAIL, email);
            callback.onSuccess(response);
        }
    }

    @Override
    public void setUserAvatar(String encodedData, NetworkCallback callback) {
        if(callback != null){
            NetworkResponse response = new NetworkResponse();
            response.addNewResponse(NetworkResponseConstants.AVATAR_URL, "");
            callback.onSuccess(response);
        }
    }
}