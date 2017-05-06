package com.example.nicki.distsysapp;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicki.distsysapp.Networking.AWSRequester;
import com.example.nicki.distsysapp.Networking.LoginClient;
import com.example.nicki.distsysapp.Types.Task;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    String sUsername, sPassword;
    EditText username, password;
    Button submit;


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
        sUsername = username.getText().toString();
        sPassword = password.getText().toString();

        try {
            if( new LoginTask().execute().get()){
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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

    class LoginTask extends AsyncTask<Task, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Task... tasks) {
            try {
                System.out.println("Start login");
                if (LoginClient.login(sUsername, sPassword) != null) {
                    return true;
                }
            } catch (IOException | InternalServerException | UnauthorizedException | BadRequestException e) {
                e.printStackTrace();
                final Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                toast.show();

            } return false;
        }
    }
}