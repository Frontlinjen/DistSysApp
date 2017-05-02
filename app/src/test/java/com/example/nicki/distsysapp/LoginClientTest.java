package com.example.nicki.distsysapp;

import com.example.nicki.distsysapp.Networking.LoginClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by nicki on 5/3/17.
 */

public class LoginClientTest {

    String username = "s153448";
    String password = "kode";

    @Test
    public void loginTest() throws Exception {
        LoginClient lc = new LoginClient();

        assertTrue(lc.login(username, password) == true);
    }
}
