package com.conradcrates.farepilottechnicalchallenge.backend;

/**
 * Created by Conrad on 08/10/2017.
 */

public interface NetworkCallback {

    void onSuccess(NetworkResponse response);

    void onFailure(NetworkResponse response);
}
