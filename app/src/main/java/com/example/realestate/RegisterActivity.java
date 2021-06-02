package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class RegisterActivity extends AppCompatActivity {

    EditText name, phone, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone_numberr);
        password = findViewById(R.id.passwordr);

        register = findViewById(R.id.loginr);

        register.setOnClickListener(v -> {
            createUser();
        });

    }

    public void createUser() {
        ParseUser user = new ParseUser();
        user.setUsername(phone.getText().toString());
        user.setPassword(password.getText().toString());
        user.setEmail(phone.getText().toString() + "@email.com");
        user.put("name", name.getText().toString());
        user.put("phone", phone.getText().toString());
        user.put("admim", 0);

        // Other fields can be set just like any other ParseObject,
        // using the "put" method, like this: user.put("attribute", "its value");
        // If this field does not exists, it will be automatically created

        user.signUpInBackground(e -> {
            if (e == null) {
                // Hooray! Let them use the app now.
                Log.d("USR", "REGISTRATION SUCCESSFUL!");
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                Log.d("ERR", "REGISTRATION FAILED!");
            }
        });
    }
}