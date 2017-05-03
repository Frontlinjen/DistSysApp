package com.example.nicki.distsysapp;

import com.example.nicki.distsysapp.Networking.AWSAuthenticator;
import com.example.nicki.distsysapp.Networking.AWSReauthenticator;
import com.example.nicki.distsysapp.Networking.AWSRequester;
import com.example.nicki.distsysapp.Networking.LoginClient;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjorthen on 4/29/17.
 */

public class AWSAuthenticatorTests {

    HttpTransport transport = new MockHttpTransport();
    HttpRequestFactory factory;

    String OAuthToken;
    @Before
    public void setup() throws IOException, Login.InternalServerException, Login.BadRequestException, Login.UnauthorizedException {
        final String username = "s153255";
        final String password = "kode";
        LoginClient lc = new LoginClient();
        factory = transport.createRequestFactory();
        OAuthToken = lc.login(username, password);
    }

    @Test
    public void login_getsOAuthToken() throws IOException {
        AWSReauthenticator reauthenticator = new AWSReauthenticator(OAuthToken, "s153255");
        AWSAuthenticator authenticator = new AWSAuthenticator(reauthenticator);
        HttpRequest mockRequest = factory.buildGetRequest(new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/tasks/26"));
        HttpHeaders headers = mockRequest.getHeaders();
        headers.clear();
        headers.setContentType("application/json");
        List<String> result = new ArrayList<String>();
        result.add("70r7hyxz72.execute-api.eu-west-1.amazonaws.com");
        headers.set("Host", result);
        authenticator.intercept(mockRequest);
        String out = headers.getAuthorization();
    }

}
