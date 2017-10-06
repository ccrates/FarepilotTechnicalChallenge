package com.conradcrates.farepilottechnicalchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.conradcrates.farepilottechnicalchallenge.backend.NetworkResponse;
import com.conradcrates.farepilottechnicalchallenge.backend.RestClientFactory;
import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;

public class ProfileActivity extends AppCompatActivity {

    private TextView username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

        NetworkResponse response = RestClientFactory.getInstance().getRestClient().getUserDetails();
        username.setText(response.getValue(NetworkResponseConstants.EMAIL));
    }

    private void initViews(){
        username = (TextView)findViewById(R.id.text_username);
        password = (TextView)findViewById(R.id.text_password);
    }
}