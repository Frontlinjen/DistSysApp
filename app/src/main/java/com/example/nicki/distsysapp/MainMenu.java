package com.example.nicki.distsysapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button createTaskButton;
    Button listTagsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        createTaskButton = (Button) findViewById(R.id.CreateTask);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),CreateTask.class);
                    startActivity(i);
                }

            });

        listTagsButton =(Button) findViewById(R.id.ShowTaskList);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TaskCategoryList.class);
                startActivity(i);
            }

        });

    }
}
