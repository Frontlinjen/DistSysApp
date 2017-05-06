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
 * Created by Thomas on 06-05-2017.
 */

public class HttpCreateTask extends AsyncTask<Task, Void, Boolean> {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(new AWSRequester(LoginClient.username, LoginClient.OAuthToken));
        @Override
        protected Boolean doInBackground(Task... tasks) {
            try {
                GenericUrl url = new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/tasks");
                ObjectMapper mapper = new ObjectMapper();
                String show = mapper.writeValueAsString(tasks[0]);
                HttpContent content = new ByteArrayContent(null, mapper.writeValueAsBytes(tasks[0]));
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                content.writeTo(stream);
                String contentAsString = new String(stream.toByteArray());
                System.out.println(contentAsString);
                HttpRequest httpRequest = requestFactory.buildPostRequest(url, content);
                HttpResponse httpResponse = httpRequest.execute();
                System.out.println("Content: " + httpResponse.parseAsString());
                if(httpResponse.getStatusCode() == 200){
                    return true;
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return false;
    }
}
