package com.example.nicki.distsysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicki.distsysapp.Networking.HttpDeleteTask;
import com.example.nicki.distsysapp.Types.Task;

import java.util.concurrent.ExecutionException;

/**
 * Created by Thomas on 04-05-2017.
 */

public class TaskView extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView address;
    TextView price;
    TextView provider;
    TextView urgent;
    Button deleteButton;
    Button updateButton;
    Bundle b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_view);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        address = (TextView) findViewById(R.id.address);
        price = (TextView) findViewById(R.id.price);
        provider = (TextView) findViewById(R.id.provider);
        urgent = (TextView) findViewById(R.id.urgent);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        updateButton = (Button) findViewById(R.id.updateButton);
        b = getIntent().getExtras();
         deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new HttpDeleteTask().execute(b.getInt("ID")).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                finish();
            }

        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdateTask.class);
                i.putExtras(b);
                startActivity(i);
            }

        });

        title.setText(b.getString("creatorID") + ": " + b.getString("title"));
        description.setText(b.getString("description"));
        address.setText(b.getString("address") + ", " + b.getInt("zip"));
        price.setText("Price: " + b.getInt("price"));
        String providerString;
        if(b.getInt("provider") == 1){
            providerString = "Provided";
        }
        else{
            providerString = "Not provided";
        }
        provider.setText("Supplies: " + providerString);
        String urgentString;
        if(b.getInt("urgent") == 1){
            urgentString = "Yes";
        }
        else{
            urgentString = "No";
        }
        urgent.setText("Urgent: " + urgentString);
    }
}
