package com.example.nicki.distsysapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicki.distsysapp.Networking.HttpCom;
import com.example.nicki.distsysapp.Types.Task;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;

//import com.example.nicki.distsysapp.DatabaseController.TaskDTO;

public class CreateTask extends AppCompatActivity {

    EditText title, description, price, provider, urgency, adress;
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
        adress = (EditText) findViewById(R.id.adress);

        cancel = (Button) findViewById(R.id.ctCancel);
        create = (Button) findViewById(R.id.CreateTask);

        final java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        final Toast toast = Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);

        final Toast toast2 = Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                String title, description, ECT, street, creatorid;
                float price;s
                int views, zipaddress;
                boolean urgent, supplies;
                int[] tags;
                System.out.println("Create new task:");
                boolean created = false;

*/
                HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) throws IOException {
                        request.setParser(new JsonObjectParser(new JacksonFactory()));
                    }
                });

                HttpCom httpCom = new HttpCom();
                Task newTask = new Task(
                        title.toString(),
                        description.toString(),
                        price.toString(),
                        provider.toString(),
                        urgency.toString(),
                        adress.toString(),
                        "ok",
                        8210,
                        1
                        );
                //Parse task to json
                //JsonObject newTask =

                    System.out.println(newTask.toString());
                    if(httpCom.CreateTask(, requestFactory)) {
                        toast.show();
                    }
                    else{
                        System.out.println("Error httpCom.CreateTask returned false");
                    }
            }
        });



    }
}