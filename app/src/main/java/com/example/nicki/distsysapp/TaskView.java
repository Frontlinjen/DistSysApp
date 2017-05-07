package com.example.nicki.distsysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicki.distsysapp.Networking.HttpDeleteTask;

/**
 * Created by Thomas on 04-05-2017.
 */

public class TaskView extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView address;
    TextView price;
    TextView provider;
    Button deleteButton;
    Bundle b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_view);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        address = (TextView) findViewById(R.id.address);
        price = (TextView) findViewById(R.id.price);
        provider = (TextView) findViewById(R.id.provider);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        b = getIntent().getExtras();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpDeleteTask().execute(b.getInt("ID"));
                finish();
            }

        });


        title.setText(b.getInt("creatorID") + ": " + b.getString("title"));
        description.setText(b.getString("description"));
        address.setText(b.getString("address") + ", " + b.getString("zip"));
        price.setText("Price: " + b.getString("price"));
        provider.setText("Supplies: " + b.getString("provider"));
    }
}
