package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 06/10/2017.
 */

public interface IRestClient {

    // TODO password probably wants to be a different type
    NetworkResponse newSession(String email, String password);

    NetworkResponse getUserDetails();

    // TODO must be base 64 encoded data for avatar
    NetworkResponse setUserAvatar(String avatar);
}