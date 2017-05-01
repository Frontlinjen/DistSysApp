package com.example.nicki.distsysapp;

import com.example.nicki.distsysapp.Networking.AWSAuthenticator;
import com.example.nicki.distsysapp.Networking.AWSReauthenticator;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hjorthen on 4/29/17.
 */

public class AWSAuthenticatorTests {
    AWSAuthenticator awsAthenticator;

    @Before
    public void setup() throws IOException {
        awsAuthenticator = new AWSAuthenticator()
    }

    @Test
    public void login_getsOAuthToken() throws IOException {
        AWSReauthenticator.Login login = new AWSReauthenticator.Login();
        login.username = "s153255@student.dtu.dk";
        login.password = "kode";
        awsReauthenticator.login(login);
    }

}
