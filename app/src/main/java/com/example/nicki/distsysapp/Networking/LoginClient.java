package com.example.nicki.distsysapp.Networking;

import com.example.nicki.distsysapp.Login;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.lang.System.out;

/**
 * Created by nicki on 5/1/17.
 */

public class LoginClient {
    public static String OAuthToken, username;

    public static String login(String username, String password) throws IOException, Login.InternalServerException, Login.UnauthorizedException, Login.BadRequestException {

        HttpRequestFactory factory = new NetHttpTransport().createRequestFactory();
        GenericUrl url = new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/login");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("username", username);
        node.put("password", password);
        HttpContent body = new ByteArrayContent(null, mapper.writeValueAsBytes(node));
        HttpRequest request = factory.buildPostRequest(url, body);
        HttpHeaders headers = request.getHeaders();
        HttpResponse response = request.execute();
        switch (response.getStatusCode()){
            case 200:
            JsonNode responseNode = mapper.readTree(response.getContent());
            JsonNode OAuthTokenNode = responseNode.get("token");
            OAuthToken = OAuthTokenNode.asText();
                return OAuthToken;
            case 400:
                throw new Login.BadRequestException("Bad request");
            case 403:
                return null;
            case 500:
                throw new Login.InternalServerException("Something went wrong with the server");
        }
        return null;
    }
}
