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

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateCredentials()) {
                    RestClientFactory.getInstance().getRestClient().newSession(username.getText().toString(), password.getText().toString(), new NetworkCallback() {
                        @Override
                        public void onSuccess(NetworkResponse response) {
                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(NetworkResponse response) {

                        }
                    });
                }
            }
        });
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