package com.example.nicki.distsysapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nicki.distsysapp.R;

import javax.ws.rs.WebApplicationException;

public class MainMenu extends AppCompatActivity {

    Button bC;
    Button bS;
    Button bE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bC= (Button) findViewById(R.id.CreateTask);
        bS= (Button) findViewById(R.id.ShowTaskList);
        bE= (Button) findViewById(R.id.Edit_or_delete_task);
        final Toast toast = Toast.makeText(getApplicationContext(), "Not yet Implemented", Toast.LENGTH_SHORT);

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),CreateTask.class);
                    startActivity(i);
                }

            });

        bS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();
            }
        });

        bE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();
            }
        });

    }
}
