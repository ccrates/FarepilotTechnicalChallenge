package com.conradcrates.farepilottechnicalchallenge.backend;

import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;
import com.conradcrates.farepilottechnicalchallenge.retrofit.Avatar;
import com.conradcrates.farepilottechnicalchallenge.retrofit.RetrofitFactory;
import com.conradcrates.farepilottechnicalchallenge.retrofit.ServerSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Conrad on 05/10/2017.
 */

public class RestClientHandler implements IRestClient {

    private String token;

    @Override
    public void newSession(String email, String password, final NetworkCallback callback) {
        RetrofitFactory.getInstance().getRestClient().newSession(email, password).enqueue(new Callback<ServerSession>() {
            @Override
            public void onResponse(Call<ServerSession> call, Response<ServerSession> response) {
                ServerSession sesh = response.body();
                token = sesh.getToken();
                if(callback != null){
                    NetworkResponse resp = new NetworkResponse();
                    resp.addNewResponse(NetworkResponseConstants.USER_ID, sesh.getUserid());
                    callback.onSuccess(resp);
                }
            }

            @Override
            public void onFailure(Call<ServerSession> call, Throwable t) {
                if(callback != null){
                    callback.onFailure(null);
                }
            }
        });
    }

    @Override
    public void getUserDetails(final NetworkCallback callback) {
        RetrofitFactory.getInstance().getRestClient().getUserDetails(token).enqueue(new Callback<Avatar>() {
            @Override
            public void onResponse(Call<Avatar> call, Response<Avatar> response) {
                Avatar avatar = response.body();
                if(callback != null){
                    NetworkResponse resp = new NetworkResponse();
                    resp.addNewResponse(NetworkResponseConstants.EMAIL, avatar.getEmail());
                    resp.addNewResponse(NetworkResponseConstants.AVATAR_URL, avatar.getAvatarUrl());
                    callback.onSuccess(resp);
                }
            }

            @Override
            public void onFailure(Call<Avatar> call, Throwable t) {
                if(callback != null){
                    callback.onFailure(null);
                }
            }
        });
    }

    @Override
    public void setUserAvatar(String base64Encoded, final NetworkCallback callback) {
        RetrofitFactory.getInstance().getRestClient().setAvatar(token, base64Encoded).enqueue(new Callback<Avatar>() {
            @Override
            public void onResponse(Call<Avatar> call, Response<Avatar> response) {
                Avatar avatar = response.body();
                if(callback != null){
                    NetworkResponse resp = new NetworkResponse();
                    resp.addNewResponse(NetworkResponseConstants.AVATAR_URL, avatar.getAvatarUrl());
                    callback.onSuccess(resp);
                }
            }

            @Override
            public void onFailure(Call<Avatar> call, Throwable t) {
                if(callback != null){
                    callback.onFailure(null);
                }
            }
        });
    }
}