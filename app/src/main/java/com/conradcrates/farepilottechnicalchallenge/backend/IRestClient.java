package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

// TODO must have network responses
public interface IRestClient {

    // TODO password probably wants to be a different type
    void newSession(String email, String password);

    void getUserId();

    // TODO must be base 64 encoded data for avatar
    void setUserAvatar(String avatar);
}