package com.example.nicki.distsysapp.Networking;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

import java.io.IOException;

/**
 * Created by hjorthen on 4/29/17.
 */

public class AWSRequester implements HttpRequestInitializer {
    String username;
    String OAuth;
    public AWSRequester(String username, String OAuth){
        this.username = username;
        this.OAuth = OAuth;
    }
    @Override
    public void initialize(HttpRequest request) throws IOException {
        AWSReauthenticator  tempToken = new AWSReauthenticator(OAuth, username);
        AWSAuthenticator signer = new AWSAuthenticator(tempToken);
        request.setInterceptor(signer);
        request.setResponseInterceptor(tempToken);
    }
}
