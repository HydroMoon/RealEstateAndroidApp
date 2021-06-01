package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

    EditText phone, password;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        getCurrentUser();

        phone = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);


        login.setOnClickListener(v -> {
            loginUser();
        });

        register.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    public void loginUser() {
        ParseUser.logInInBackground(phone.getText().toString(), password.getText().toString(), (user, e) -> {
            if (user != null) {
                // Hooray! The user is logged in.
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                // Login failed. Look at the ParseException to see what happened.

            }
        });
    }

    public void getCurrentUser() {
        // After login, Parse will cache it on disk, so
        // we don't need to login every time we open this
        // application
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            // show the signup or login screen
        }
    }
}