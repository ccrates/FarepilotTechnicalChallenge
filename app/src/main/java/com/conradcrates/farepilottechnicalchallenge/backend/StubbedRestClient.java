package com.conradcrates.farepilottechnicalchallenge.backend;

import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;

/**
 * Created by Conrad on 06/10/2017.
 */

public class StubbedRestClient implements IRestClient {

    @Override
    public void newSession(String email, String password, NetworkCallback callback) {
        if(callback != null){
            callback.onSuccess(null);
        }
    }

    @Override
    public void getUserDetails(NetworkCallback callback) {
        if(callback != null) {
            NetworkResponse response = new NetworkResponse();
            response.addNewResponse(NetworkResponseConstants.EMAIL, "conrad-crates@hotmail.co.uk");
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