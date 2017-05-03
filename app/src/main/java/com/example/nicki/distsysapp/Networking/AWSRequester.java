package com.example.nicki.distsysapp.Networking;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

import java.io.IOException;

/**
 * Created by hjorthen on 4/29/17.
 */

public class AWSRequester implements HttpRequestInitializer {

    public void login(String username, String password){

    }

    @Override
    public void initialize(HttpRequest request) throws IOException {
        //request.setInterceptor(new AWSAuthenticator());

    }
}
