package com.example.nicki.distsysapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicki.distsysapp.Networking.AWSRequester;
import com.example.nicki.distsysapp.Networking.HttpCreateTask;
import com.example.nicki.distsysapp.Networking.LoginClient;
import com.example.nicki.distsysapp.Types.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateTask extends AppCompatActivity {

    EditText title, description, price, provider, urgency, address, etc, tags, zip;
    Button cancel, create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        price = (EditText) findViewById(R.id.price);
        provider = (EditText) findViewById(R.id.materialProvider);
        urgency = (EditText) findViewById(R.id.urgency);
        address = (EditText) findViewById(R.id.address);
        etc = (EditText) findViewById(R.id.ect);
        zip = (EditText) findViewById(R.id.zip);
        tags = (EditText) findViewById(R.id.tags);


        create = (Button) findViewById(R.id.ctC);

        final Toast toast = Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Task newTask = new Task();
                newTask.setTitle(title.getText().toString());
                newTask.setDescription(description.getText().toString());
                newTask.setPrice(Integer.parseInt(price.getText().toString()));
                newTask.setSupplies(Integer.parseInt(provider.getText().toString()));
                newTask.setUrgent(Integer.parseInt(urgency.getText().toString()));
                newTask.setStreet(address.getText().toString());
                newTask.setZipaddress(Integer.parseInt(zip.getText().toString()));
                newTask.setETC(Integer.parseInt(etc.getText().toString()));
                List<Integer> a = new ArrayList<Integer>();
                a.add(Integer.parseInt(tags.getText().toString()));
                newTask.setTags(a);
                try {
                    if(new HttpCreateTask().execute(newTask).get()) {
                        toast.show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}