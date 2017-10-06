package com.conradcrates.farepilottechnicalchallenge.backend;

import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;

/**
 * Created by Conrad on 06/10/2017.
 */

public class StubbedRestClient implements IRestClient {

    @Override
    public NetworkResponse newSession(String email, String password) {
        return null;
    }

    @Override
    public NetworkResponse getUserDetails() {
        NetworkResponse response = new NetworkResponse();
        response.addNewResponse(NetworkResponseConstants.EMAIL, "conrad-crates@hotmail.co.uk");
        return response;
    }

    @Override
    public NetworkResponse setUserAvatar(String avatar) {
        return null;
    }
}