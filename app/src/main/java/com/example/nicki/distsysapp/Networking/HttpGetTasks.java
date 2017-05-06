package com.example.nicki.distsysapp.Networking;

import android.os.AsyncTask;

import com.example.nicki.distsysapp.Types.Tag;
import com.example.nicki.distsysapp.Types.Task;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 06-05-2017.
 */

public class HttpGetTasks extends AsyncTask<Tag, Void, List<Task>> {
    HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(new AWSRequester(LoginClient.username, LoginClient.OAuthToken));
    @Override
    protected List<Task> doInBackground(Tag... tags) {
        String tagString = "tags=";
        for(Tag t: tags){
            tagString += t.id + "+";
        }
        tagString = tagString.substring(0, tags.length-1);
        try {
            HttpRequest httpRequest = requestFactory.buildGetRequest(new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/tasks/" + tagString + "/"));
            HttpResponse httpResponse = httpRequest.execute();
            if(httpResponse.getStatusCode() == 200) {
                InputStream stream = httpResponse.getContent();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(stream);
                ArrayList<Task> tasks = mapper.treeToValue(node, ArrayList.class);
                return tasks;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

