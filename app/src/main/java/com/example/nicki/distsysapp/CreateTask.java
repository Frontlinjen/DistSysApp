package com.example.nicki.distsysapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nicki.distsysapp.Networking.HttpCom;
import com.example.nicki.distsysapp.Types.Task;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

//import com.example.nicki.distsysapp.DatabaseController.TaskDTO;

public class CreateTask extends AppCompatActivity {

    EditText title, description, price, provider, urgency, address, etc, tags;
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
        address = (EditText) findViewById(R.id.adress);
        //etc = (EditText) findViewById(R.id.etc);
        //tags = (EditText) findViewById(R.id.tags);


        cancel = (Button) findViewById(R.id.ctCancel);
        create = (Button) findViewById(R.id.ctC);

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
                Task newTask = new Task();
                newTask.setTitle(title.toString());
                newTask.setDescription(description.toString());
                newTask.setPrice(Integer.parseInt(price.toString()));
                newTask.setETC(Integer.parseInt(etc.toString()));
                newTask.setSupplies(Integer.parseInt(provider.toString()));
                newTask.setUrgent(Integer.parseInt(urgency.toString()));
                newTask.setStreet(address.toString());
                //newTask.setTags();

                    System.out.println(newTask.toString());
                try {
                    if(new CreateTaskTask().execute(newTask).get()) {
                        toast.show();
                    }
                    else{
                        System.out.println("Error httpCom.CreateTask returned false");
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

class CreateTaskTask extends AsyncTask<Task, Void, Boolean>{
    HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
    HttpCom httpCom = new HttpCom();

    @Override
    protected Boolean doInBackground(Task... tasks) {
        return httpCom.CreateTask(tasks[0], requestFactory);
    }
}