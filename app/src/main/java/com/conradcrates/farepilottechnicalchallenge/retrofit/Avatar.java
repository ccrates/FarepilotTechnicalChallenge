package com.conradcrates.farepilottechnicalchallenge.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Conrad on 08/10/2017.
 */

public class Avatar {

    private String email;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}