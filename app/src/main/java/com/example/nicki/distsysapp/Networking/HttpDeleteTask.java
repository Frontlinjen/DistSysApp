package com.example.nicki.distsysapp.Networking;

import android.os.AsyncTask;

import com.example.nicki.distsysapp.Types.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Thomas on 07-05-2017.
 */

public class HttpDeleteTask extends AsyncTask<Integer, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Integer... ids) {
        try {
            GenericUrl url = new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/tasks/" + ids[0]);

            HttpRequest httpRequest = LoginClient.requestFactory.buildDeleteRequest(url);
            HttpResponse httpResponse = httpRequest.execute();
            System.out.println("Content: " + httpResponse.parseAsString());
            if (httpResponse.getStatusCode() >= 200 && httpResponse.getStatusCode() < 300) {
                System.out.println("Deleted");
                return true;
            } else {
                System.out.println("Error: " + httpResponse.getStatusCode());
                System.out.println(httpResponse.getStatusMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
