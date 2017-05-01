package com.example.nicki.distsysapp.Networking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.out;

/**
 * Created by nicki on 5/1/17.
 */

public class LoginClient {

    public void login(String username, String password) throws IOException {

        HttpRequestFactory factory = new NetHttpTransport().createRequestFactory();
        GenericUrl url = new GenericUrl("https://dawa.aws.dk/login");

        HttpResponse addressLookupResponse = null;
        try {
            HttpRequest addressLookup = factory.buildGetRequest(url);
            addressLookupResponse = addressLookup.execute();
        } catch (Exception e) {
            e.printStackTrace();
            //Ignore the error and accept the address regardless
        }

        if(addressLookupResponse != null && addressLookupResponse.isSuccessStatusCode() && addressLookupResponse.getContent() != null){
            InputStream stream = null;

            try{
                stream = addressLookupResponse.getContent();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode n = mapper.readTree(stream);
                if(n.isArray() && !n.elements().hasNext()){ //If format changes and we do not recieve an array, then we shouldn't disallow the user to create the task
                    raiseError(out, 400, "Invalid address specified");
                    return;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally{
                if(stream != null)
                    stream.close();
            }

        }
    }
}
