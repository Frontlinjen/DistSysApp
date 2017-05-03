package com.example.nicki.distsysapp.Networking;

import android.os.AsyncTask;

import com.example.nicki.distsysapp.Types.Tag;
import com.example.nicki.distsysapp.Types.Task;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 20-04-2017.
 */

public class HttpCom{

    public List<Tag> getTagList(HttpRequestFactory factory){
        try {
            HttpRequest httpRequest = factory.buildGetRequest(new GenericUrl("URL TIL TAGS"));
            HttpResponse httpResponse = httpRequest.execute();
            if(httpResponse.getStatusCode() == 200) {
                InputStream stream = httpResponse.getContent();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(stream);
                ArrayList<Tag> tags = mapper.treeToValue(node, ArrayList.class);
                return tags;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void getCommentList(){

    }

    public boolean CreateTask(Task task, HttpRequestFactory factory){
        try {
            GenericUrl url = new GenericUrl("https://70r7hyxz72.execute-api.eu-west-1.amazonaws.com/development/tasks");
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("title", task.getTitle());
            node.put("description", task.getDescription());
            node.put("price", task.getPrice());
            node.put("provider", task.getProvider());
            node.put("urgency", task.getUrgency());
            node.put("address", task.getAddress());
            node.put("ECT", task.getECT());
            node.put("zipAddress", task.getZipAddress());
            node.put("tags", task.getTags());
            HttpContent content = new ByteArrayContent(null, mapper.writeValueAsBytes(node));
            HttpRequest httpRequest = factory.buildPostRequest(url, content);
            /*HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                @Override
                public void intercept(HttpRequest request) throws IOException {
                    //request.setHeaders();
                }
            };
            interceptor.intercept(httpRequest);*/
            HttpResponse httpResponse = httpRequest.execute();
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