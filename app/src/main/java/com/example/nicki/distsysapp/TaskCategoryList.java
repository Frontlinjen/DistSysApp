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

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nicki on 3/12/17.
 */

public class TaskCategoryList extends AppCompatActivity {
    ListView lv;
    String tag;
    HttpTransport transport = new NetHttpTransport();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lv = (ListView) findViewById(R.id.tasks);
        lv.setClickable(true);
        //Get all tag through Lambda function and display them in the ListView.
        HttpRequestFactory requestFactory = transport.createRequestFactory(
                new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) throws IOException {
                        request.setParser(new JsonObjectParser(new JacksonFactory()));
                    }
                }

        );

        HttpRequest req = requestFactory.buildGetRequest("");
        req.setHeaders(); //Sæt headers her
        try {
            HttpResponse res = req.execut;
            System.out.println(res.parseAsString());
        }
        catch (IOException e){
            e.printStackTrace();
        }

/*
        try {
            lv.setAdapter(new ArrayAdapter< String >(this, R.layout.tasks, taskDAO.getTaskList()));
        } catch (DALException e) {
            e.printStackTrace();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
                //Sets the tag, which determines what list will be shown in the next activity.
                setTag((String)arg0.getItemAtPosition(position));

                Intent i = new Intent(getApplicationContext(),TaskList.class);
                startActivity(i);

            }

        });

    }

    public String getTag() {
        return tag;
    }

    private void setTag(String tag) {
        this.tag = tag;
    }
*/
  /*  public void taskList1() throws DALException {
        for (int i = 0; i > taskDAO.getTaskList().size(); i++){
            ArrayList<String> list = null;

            list.add(taskDAO.getTask(Integer.toString(i)));
        }
    }
*/
    }
}
