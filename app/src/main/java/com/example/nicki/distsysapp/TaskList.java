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
import com.example.nicki.distsysapp.Types.Task;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicki on 3/12/17.
 */

public class TaskList extends AppCompatActivity{

    ListView lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        lv =(ListView)findViewById(R.id.tasks);
        lv.setClickable(true);

        Bundle b = getIntent().getExtras();
        Tag tag = new Tag(b.getInt("id"), b.getString("name"));

        HttpCom com = new HttpCom();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        //TODO Get taskList based on tag
        List<Task> taskList = com.getTaskList(requestFactory, tag);

        lv.setAdapter(new ArrayAdapter(this, R.layout.tasks, taskList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
                Bundle b = new Bundle();
                Object o = arg0.getItemAtPosition(position);
                if(o instanceof Task) {
                    Task selectedTask = (Task) o;
                    b.putString("title", selectedTask.getTitle());
                    b.putString("description", selectedTask.getDescription());
                    b.putString("address", selectedTask.getAddress());
                    b.putInt("zip", selectedTask.getZipAddress());
                    b.putString("price", selectedTask.getPrice());
                    b.putString("provider", selectedTask.getProvider());
                    Intent i = new Intent(getApplicationContext(), TaskView.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }

        });
    }
}
