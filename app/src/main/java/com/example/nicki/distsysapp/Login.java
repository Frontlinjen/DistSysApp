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
import com.example.nicki.distsysapp.Networking.HttpCom;
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
    LoginClient loginClient = new LoginClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.studienummer);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.Sign_in);

        submit.setOnClickListener(this);
        sUsername = username.getText().toString();
        sPassword = password.getText().toString();


    }

    @Override
    public void onClick(View v)  {
        try {
         //   if(loginClient.login(username.getText().toString(), password.getText().toString()) != null) {
            if( new LoginTask().execute().get()){
                Intent i = new Intent(getApplicationContext(), MainMenu.class);
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
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(new AWSRequester(LoginClient.username, LoginClient.OAuthToken));
        HttpCom httpCom = new HttpCom();

        @Override
        protected Boolean doInBackground(Task... tasks) {
            try {
                if (LoginClient.login(sUsername, sPassword) != null) {
                    return true;
                }
            } catch (IOException | InternalServerException | UnauthorizedException | BadRequestException e) {
                e.getMessage();
                final Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                toast.show();

            } return false;
        }
    }
}