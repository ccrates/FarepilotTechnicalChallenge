package com.conradcrates.farepilottechnicalchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.conradcrates.farepilottechnicalchallenge.backend.RestClient;
import com.conradcrates.farepilottechnicalchallenge.backend.RestClientFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getViewReferences();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateCredentials()) {
                    RestClientFactory.getInstance().getRestClient().newSession("", "");

                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getViewReferences(){
        username = (EditText)findViewById(R.id.edit_username);
        password = (EditText)findViewById(R.id.edit_password);
        login = (Button)findViewById(R.id.button_login);
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