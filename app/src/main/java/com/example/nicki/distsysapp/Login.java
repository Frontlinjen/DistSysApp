package com.example.nicki.distsysapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicki.distsysapp.Networking.LoginClient;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText username, password;
    Button submit;
    LoginClient loginClient = new LoginClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.studienummer);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.Sign_in);

        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)  {
        try {
            if(loginClient.login(username.getText().toString(), password.getText().toString()) != null) {
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
            }
        } catch (IOException | InternalServerException | BadRequestException | UnauthorizedException e) {
            final Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }





    public static class InternalServerException extends Exception
    {
        public InternalServerException(String msg){
        }
    }

    public static class UnauthorizedException extends Exception
    {
        public UnauthorizedException(String msg){
        }
    }

    public static class BadRequestException extends Exception
    {
        public BadRequestException(String msg){
        }
    }
}