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
<<<<<<< Updated upstream
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
=======
import com.google.api.client.http.javanet.NetHttpTransport;
>>>>>>> Stashed changes
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

/**
 * Created by Thomas on 20-04-2017.
 */

gitpublic class HttpCom {

<<<<<<< Updated upstream
    private HttpTransport transport = new NetHttpTransport();
    private HttpRequestFactory requestFactory = transport.createRequestFactory(
=======
    private HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(
>>>>>>> Stashed changes
            new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                    request.setResponseInterceptor(new Reauthenticator());
                    request.setInterceptor(new AWSAuthenticator());
                }
            }
    );

    public HttpResponse get(GenericUrl url){
        try {
            HttpRequest req = requestFactory.buildGetRequest(url);
            try {
                HttpResponse res = req.execute();
                HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                    @Override
                    public void intercept(HttpRequest request) throws IOException {
                        request.setHeaders(); //TODO Se hvordan headers bliver sat i DistCLI
                    }
                };
                interceptor.intercept(req);
                System.out.println(res.parseAsString());
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse put(GenericUrl url, HttpContent content){
        try {
            HttpRequest req = requestFactory.buildPutRequest(url, content);
            try {
                HttpResponse res = req.execute();
                HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                    @Override
                    public void intercept(HttpRequest request) throws IOException {
                        request.setHeaders(); //TODO Se hvordan headers bliver sat i DistCLI
                    }
                };
                interceptor.intercept(req);
                System.out.println(res.parseAsString());
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse post(GenericUrl url, HttpContent content){
        try {
            HttpRequest req = requestFactory.buildPostRequest(url, content);
            try {
                HttpResponse res = req.execute();
                HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                    @Override
                    public void intercept(HttpRequest request) throws IOException {
                        request.setHeaders(); //TODO Se hvordan headers bliver sat i DistCLI
                    }
                };
                interceptor.intercept(req);
                System.out.println(res.parseAsString());
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse delete(GenericUrl url){
        try {
            HttpRequest req = requestFactory.buildDeleteRequest(url);
            try {
                HttpResponse res = req.execute();
                HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                    @Override
                    public void intercept(HttpRequest request) throws IOException {
                        request.setHeaders(); //TODO Se hvordan headers bliver sat i DistCLI
                    }
                };
                interceptor.intercept(req);
                System.out.println(res.parseAsString());
                if(res.getStatusCode() == 200){
                    jsonWrangler(res);
                }
                else{
                    System.out.println("Did not get tags");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public TagList tagListJsonWrangler(){
        HttpResponse response = get(/*URL*/);
        TagList tagList = null;
        try {
            tagList = response.parseAs(TagList.class);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return  tagList;
    }

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