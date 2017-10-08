package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

public interface IRestClient {

    // TODO password probably wants to be a different type
    void newSession(String email, String password, NetworkCallback callback);

    void getUserDetails(NetworkCallback callback);

    // TODO must be base 64 encoded data for avatar
    void setUserAvatar(String avatar, NetworkCallback callback);
}