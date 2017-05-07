package com.example.nicki.distsysapp.Networking;

import android.os.AsyncTask;

import com.example.nicki.distsysapp.Login;
import com.example.nicki.distsysapp.Types.Tag;
import com.example.nicki.distsysapp.Types.Task;
import com.fasterxml.jackson.databind.JsonNode;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 06-05-2017.
 */

public class HttpGetTags extends AsyncTask<Void, Void, List<Tag>> {

    @Override
        protected List<Tag> doInBackground(Void... voids) {
            try {
                HttpRequest httpRequest = LoginClient.requestFactory.buildGetRequest(new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/tags"));
                HttpResponse httpResponse = httpRequest.execute();
                if(httpResponse.getStatusCode() >= 200 && httpResponse.getStatusCode() < 300) {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode node = mapper.readTree(httpResponse.parseAsString());
                    node = node.get("Tags");
                    HashMap<Integer, String> a = mapper.treeToValue(node, HashMap.class);
                    Iterator iter = a.entrySet().iterator();
                    ArrayList<Tag> tags = new ArrayList<>();
                    while(iter.hasNext()){
                        Map.Entry pair = (Map.Entry) iter.next();
                        tags.add(new Tag((int) pair.getValue(),(String) pair.getKey()));
                    }
                    return tags;
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
    }
}
