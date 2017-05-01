package com.example.nicki.distsysapp.Networking;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

/**
 * Created by hjorthen on 4/29/17.
 */

public class AWSReauthenticator implements HttpResponseInterceptor {
    private HttpRequest authorizeRequest;
    final private static String roleArn = "arn:aws:iam::619517212226:role/Cognito_WEWOUsersAuth_Role";
    final private static String version = "2011-06-15";
    final private static String action = "AssumeRoleWithWebIdentity";
    final private static String stsUrl = "https://sts.amazonaws.com/";
    final private static String tmpAccessUrl = stsUrl + "?Action=" + action + "&RoleArn=" + roleArn + "&Version=" + version;

    public AWSReauthenticator(String OAuth_Token){
        HttpRequestFactory client = new NetHttpTransport().createRequestFactory();

    }
    @Override
    public void interceptResponse(HttpResponse response) throws IOException {
        if(response.getStatusCode() == 401){

        }
    }
}
