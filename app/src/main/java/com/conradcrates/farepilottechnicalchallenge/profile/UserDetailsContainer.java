package com.conradcrates.farepilottechnicalchallenge.profile;

import java.io.Serializable;

/**
 * Created by Conrad on 08/10/2017.
 */

public class UserDetailsContainer implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}