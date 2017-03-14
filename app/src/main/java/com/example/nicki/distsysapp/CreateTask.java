package com.example.nicki.distsysapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import com.example.nicki.distsysapp.DatabaseController.TaskDTO;

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


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDTO task  = new TaskDTO("id", "title", "description", 1, 1, true, true, 1, "adress", 10, date, date, "id3");
                toast.show();
            }
        });



    }
}
