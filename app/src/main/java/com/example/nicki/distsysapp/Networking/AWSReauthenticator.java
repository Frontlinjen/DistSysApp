package com.example.nicki.distsysapp.Networking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hjorthen on 4/29/17.
 */
class Credentials{
    public String secret;
    public String accessKey;
    public String token;
}

class CryptoInformation{
    public String identity;
    public String token;
}

public class AWSReauthenticator implements HttpResponseInterceptor {
    public static class Login{
        public String username;
        public String password;
    }


    private HttpRequest authorizeRequest;
    private Credentials credentials; //TODO: Figure out where to store this

    final static String authpoint = "https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/login";
    final private static String roleArn = "arn:aws:iam::619517212226:role/Cognito_WEWOUsersAuth_Role";
    final private static String version = "2011-06-15";
    final private static String action = "AssumeRoleWithWebIdentity";
    final private static String stsUrl = "https://sts.amazonaws.com/";
    private HttpRequest tempAuthRequest;
    private HttpRequestFactory client;

    public AWSReauthenticator(String OAuth_Token, String username) throws IOException {
        client = new NetHttpTransport().createRequestFactory();
        final String tmpAccessUrl = stsUrl + "?Action=" + action + "&RoleArn=" + roleArn + "&Version=" + version;
        final GenericUrl url = new GenericUrl(tmpAccessUrl + "&RoleSessionName=" + username + "&WebIdentityToken=" + OAuth_Token);
        tempAuthRequest = client.buildGetRequest(url);
    }
    public void login(Login login) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest loginRequest =  client.buildGetRequest(new GenericUrl(authpoint));
        HttpContent content = new ByteArrayContent("application/json", mapper.writeValueAsBytes(login));
        loginRequest.setContent(content);
        HttpResponse auth = loginRequest.execute();
        InputStream responseData = null;
        if((responseData = auth.getContent()) != null){
           CryptoInformation cinfo = mapper.readValue(responseData, CryptoInformation.class);
        }
    }

    public void requestTemporaryAccessToken() throws IOException {
        HttpResponse response = tempAuthRequest.execute();
        ObjectMapper mapper = new ObjectMapper();
        if(response.getStatusCode() == 200){
            InputStream instream = null;
            try{
                instream = response.getContent();
                JsonNode node = mapper.readTree(instream);
                node = node.findValue("Credentials");
                if(node != null){
                    credentials.secret = node.get("SecretAccessKey").asText();
                    credentials.accessKey = node.get("AccessKeyId").asText();
                    credentials.token = node.get("SessionToken").asText();
                }
            }
            catch(Exception e){
                //Do something...
            }
            finally {
                if(instream != null)
                {
                    instream.close();
                }
            }
        }

    }

    @Override
    public void interceptResponse(HttpResponse response) throws IOException {
        if(response.getStatusCode() == 401){
            requestTemporaryAccessToken();
        }
    }
}
