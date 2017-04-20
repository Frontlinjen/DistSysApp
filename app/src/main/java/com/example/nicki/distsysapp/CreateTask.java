package com.example.nicki.distsysapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.nicki.distsysapp.Networking.AWSClient;
import com.example.nicki.distsysapp.Networking.URL;
import java.sql.Date;
import java.util.Calendar;

//import com.example.nicki.distsysapp.DatabaseController.TaskDTO;

public class CreateTask extends AppCompatActivity {

    EditText title, description, price, provider, urgency, adress;
    Button cancel, create;
    AWSClient awsClient = new AWSClient();


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

                JSONObject newTask = new JSONObject();
/*                String title, description, ECT, street, creatorid;
                float price;s
                int views, zipaddress;
                boolean urgent, supplies;
                int[] tags;
                System.out.println("Create new task:");
                boolean created = false;

*/
                try {
                    newTask.put("title", title);
                    newTask.put("description", description);
                    newTask.put("price", price);
                    newTask.put("ECT", "ok");
                    newTask.put("supplies", provider);
                    newTask.put("urgent", urgency);
                    newTask.put("street", adress);
                    newTask.put("zipaddress", 1212);
                    newTask.put("tags", 1);
                    System.out.println(newTask.toString());
                    awsClient.POST(newTask.toString());

                    toast.show();
                } catch (JSONException ex) {
                    System.out.println("Creation failed!");
                    Logger.getLogger(CreateTask.class.getName()).log(Level.SEVERE, null, ex);

                }
                toast.show();
            }
        });



    }
}


// TaskDTO task  = new TaskDTO("id", "title", "description", 1, 1, true, true, 1, "adress", 10, date, date, "id3");