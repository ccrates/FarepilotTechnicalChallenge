package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

public interface IRestClient {

    void newSession(String email, String password, NetworkCallback callback);

    void getUserDetails(NetworkCallback callback);

    void setUserAvatar(String avatar, NetworkCallback callback);
}