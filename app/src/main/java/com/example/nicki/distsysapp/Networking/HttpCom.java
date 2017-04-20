package com.example.nicki.distsysapp.Networking;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.IOException;

/**
 * Created by Thomas on 20-04-2017.
 */

public class HttpCom {

    private HttpRequestFactory requestFactory = transport.createRequestFactory(
            new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                    request.setParser(new JsonObjectParser(new JacksonFactory()));
                }
            }

    );

    public HttpResponse sendRequest(HttpRequest request) {
        try {
            HttpResponse res = request.execute();
            HttpExecuteInterceptor interceptor = new HttpExecuteInterceptor() {
                @Override
                public void intercept(HttpRequest request) throws IOException {
                    request.setHeaders();
                }
            };
            interceptor.intercept(request);
            System.out.println(res.parseAsString());
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse get(){
        HttpRequest req = requestFactory.buildGetRequest("");
        return sendRequest(req);
    }

    public HttpResponse put(){
        HttpRequest req = requestFactory.buildPutRequest("");
        return sendRequest(req);
    }
    public HttpResponse post(){
        HttpRequest req = requestFactory.buildPostRequest("");
        return sendRequest(req);
    }
    public HttpResponse delete(){
        HttpRequest req = requestFactory.buildDeleteRequest("");
        return sendRequest(req);
    }
}
