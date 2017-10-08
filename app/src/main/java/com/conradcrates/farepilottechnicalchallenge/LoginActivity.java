package com.conradcrates.farepilottechnicalchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.conradcrates.farepilottechnicalchallenge.backend.NetworkCallback;
import com.conradcrates.farepilottechnicalchallenge.backend.NetworkResponse;
import com.conradcrates.farepilottechnicalchallenge.backend.RestClientFactory;
import com.conradcrates.farepilottechnicalchallenge.constants.NetworkResponseConstants;
import com.conradcrates.farepilottechnicalchallenge.profile.UserDetailsController;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String userId = UserDetailsController.getUserDetails().getUserId();
        if(userId != null && !userId.isEmpty()){
            goToProfileActivity();
            return;
        }

        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateCredentials()) {
                    RestClientFactory.getInstance().getRestClient().newSession(username.getText().toString(), password.getText().toString(), new NetworkCallback() {
                        @Override
                        public void onSuccess(NetworkResponse response) {
                            String userId = response.getValue(NetworkResponseConstants.USER_ID);

                            if(userId != null && !userId.isEmpty()){
                                UserDetailsController.getUserDetails().setUserId(userId);
                            }
                            goToProfileActivity();
                        }

                        @Override
                        public void onFailure(NetworkResponse response) {

                        }
                    });
                }
            }
        });
    }

    private void goToProfileActivity(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews(){
        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.button_login);
    }

    private boolean validateCredentials(){
        if(username.getText().toString().isEmpty()){
            return false;
        }
        if(password.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}