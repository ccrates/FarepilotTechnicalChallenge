package com.conradcrates.farepilottechnicalchallenge.profile;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Conrad on 08/10/2017.
 */

public class UserDetailsContainer implements Serializable {

    private Bitmap avatar;

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}