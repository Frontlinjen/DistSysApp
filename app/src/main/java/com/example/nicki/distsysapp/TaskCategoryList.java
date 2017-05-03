package com.example.nicki.distsysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.nicki.distsysapp.Networking.HttpCom;
import com.example.nicki.distsysapp.Types.Tag;
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
    ListView lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lv = (ListView) findViewById(R.id.tasks);
        lv.setClickable(true);
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

        HttpCom httpCom = new HttpCom();
        List<Tag> taglist = httpCom.getTagList(requestFactory);
        if (taglist != null) {
            lv.setAdapter(new ArrayAdapter(this, R.layout.tasks, taglist));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    //Sets the tag, which determines what list will be shown in the next activity.
                    Bundle b = new Bundle();
                    Object o = arg0.getItemAtPosition(position);
                    if(o instanceof Tag){
                        Tag selectedTag = (Tag)o;
                        b.putInt("id", selectedTag.id);
                        b.putString("name", selectedTag.name);
                        Intent i = new Intent(getApplicationContext(), TaskList.class);
                        i.putExtras(b);
                        startActivity(i);
                    }
                    else{
                        System.out.println("Object was not tag");
                    }
                }
            });
        }
    }
}