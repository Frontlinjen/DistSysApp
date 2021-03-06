package com.example.nicki.distsysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nicki.distsysapp.Networking.HttpGetTasks;
import com.example.nicki.distsysapp.Types.Tag;
import com.example.nicki.distsysapp.Types.Task;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by nicki on 3/12/17.
 */

public class TaskList extends AppCompatActivity{

    ListView lv;
    List<Task> taskList = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lv = (ListView) findViewById(R.id.tasks);
        lv.setClickable(true);

        Bundle b = getIntent().getExtras();
        Tag tag = new Tag(b.getInt("id"), b.getString("name"));

        try {
            taskList = new HttpGetTasks().execute(tag).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            final Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        } catch (ExecutionException e) {
            e.printStackTrace();
            final Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
        if (taskList != null) {
            List<String> categoryNameList = new ArrayList<String>();
            for(int i = 0; i < taskList.size(); i++){
                categoryNameList.add(taskList.get(i).getTitle());
            }
            lv.setAdapter(new ArrayAdapter(this, R.layout.list, categoryNameList));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    Bundle b = new Bundle();
                    Task selectedTask = taskList.get(position);
                    b.putString("title", selectedTask.getTitle());
                    b.putString("description", selectedTask.getDescription());
                    b.putString("address", selectedTask.getStreet());
                    b.putInt("urgent", selectedTask.getUrgent());
                    b.putInt("zip", selectedTask.getZipaddress());
                    b.putInt("price", selectedTask.getPrice());
                    b.putInt("provider", selectedTask.getSupplies());
                    b.putInt("ID", selectedTask.getID());
                    b.putInt("views", selectedTask.getViews());
                    b.putString("creatorID", selectedTask.getCreatorid());
                        Intent i = new Intent(getApplicationContext(), TaskView.class);
                        i.putExtras(b);
                        startActivity(i);
                }

            });
        }
    }
}
