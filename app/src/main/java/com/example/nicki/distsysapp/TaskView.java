package com.example.nicki.distsysapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Thomas on 04-05-2017.
 */

public class TaskView extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView address;
    TextView price;
    TextView provider;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_view);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        address = (TextView) findViewById(R.id.address);
        price = (TextView) findViewById(R.id.price);
        provider = (TextView) findViewById(R.id.provider);

        Bundle b = getIntent().getExtras();

        title.setText(b.getString("title"));
        description.setText(b.getString("description"));
        address.setText(b.getString("address") + ", " + b.getString("zip"));
        price.setText(b.getString("price"));
        provider.setText(b.getString("provider"));
    }
}
