package com.example.nicki.distsysapp.Networking;

import com.example.nicki.distsysapp.Types.Tag;
import com.example.nicki.distsysapp.Types.TagList;
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
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

/**
 * Created by Thomas on 20-04-2017.
 */

public class HttpCom {

    public TagList getTagList(HttpRequestFactory factory){
        try {
            HttpRequest httpRequest = factory.buildGetRequest(new GenericUrl("URL TIL TASKS"));
            HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                @Override
                public void intercept(HttpRequest request) throws IOException {
                    request.setHeaders(); //TODO Se hvordan headers bliver sat i DistCLI
                }
            };
            interceptor.intercept(httpRequest);
            HttpResponse httpResponse = httpRequest.execute();

            //TODO Parse response
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return;
    }

    public void getCommentList(){

    }

    public boolean CreateTask(JsonObject task, HttpRequestFactory factory){
        try {
            HttpRequest httpRequest = factory.buildPostRequest(new GenericUrl("URL TIL CREATETASK"), );
            HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                @Override
                public void intercept(HttpRequest request) throws IOException {
                    request.setHeaders(); //TODO Se hvordan headers bliver sat i DistCLI
                }
            };
            interceptor.intercept(httpRequest);
            HttpResponse httpResponse = httpRequest.execute();
            if(httpResponse.getStatusCode() == 200){
                return true;
            }
            else{
                return false;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

}