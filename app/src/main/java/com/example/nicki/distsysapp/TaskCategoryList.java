package com.example.nicki.distsysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
import com.example.nicki.distsysapp.DatabaseController.DALException;
import com.example.nicki.distsysapp.DatabaseController.MySQLTaskDAO;
import com.example.nicki.distsysapp.DatabaseController.TaskDTO;
*/

import com.example.nicki.distsysapp.Networking.HttpCom;
import com.example.nicki.distsysapp.Types.Tag;
import com.example.nicki.distsysapp.Types.TagList;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicki on 3/12/17.
 */

public class TaskCategoryList extends AppCompatActivity {
    /*ListView lv;
    String tag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lv = (ListView) findViewById(R.id.tasks);
        lv.setClickable(true);
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                request.setParser(new JsonObjectParser(new JacksonFactory()));
            }
        });
        HttpCom httpCom = new HttpCom();
        List<Tag> taglist = httpCom.getTagList(requestFactory).getList();


        try {
            List<Integer> tags = (List<Integer>) tagsResponse.parseAs(List.class);
            lv.setAdapter(new ArrayAdapter(this, R.layout.tasks, tags));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    //Sets the tag, which determines what list will be shown in the next activity.
                    tag = arg0.getItemAtPosition(position).toString();
                    Intent i = new Intent(getApplicationContext(), TaskList.class);
                    startActivity(i);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}