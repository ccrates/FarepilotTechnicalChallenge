package com.conradcrates.farepilottechnicalchallenge.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Conrad on 08/10/2017.
 */

public interface RetrofitClient {

    @POST("/sessions/new")
    Call<ServerSession> newSession(@Query("email") String email, @Query("password") String password);

    @GET("/users/:userid")
    Call<Avatar> getUserDetails(@Header("token") String token);

    @POST("users/:userid/avatar")
    Call<Avatar> setAvatar(@Header("token") String token, @Query("avatar") String base64Encoded);
}