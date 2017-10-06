package com.conradcrates.farepilottechnicalchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.conradcrates.farepilottechnicalchallenge.backend.NetworkResponse;
import com.conradcrates.farepilottechnicalchallenge.backend.RestClientFactory;
import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;
import com.conradcrates.farepilottechnicalchallenge.gravatar.Gravatar;

public class ProfileActivity extends AppCompatActivity {

    private TextView username, password;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

        NetworkResponse response = RestClientFactory.getInstance().getRestClient().getUserDetails();
        String email = response.getValue(NetworkResponseConstants.EMAIL);

        username.setText(email);
        String url = Gravatar.createGravatarUrl(email);

        Glide.with(getApplicationContext()).load(url).into(avatar);
    }

    private void initViews(){
        username = findViewById(R.id.text_username);
        password = findViewById(R.id.text_password);
        avatar = findViewById(R.id.image_avatar);
    }
}