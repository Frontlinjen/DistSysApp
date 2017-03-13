package com.example.nicki.distsysapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.duration;
import static com.example.nicki.distsysapp.R.id.text;

public class Login extends AppCompatActivity {

    boolean verified;
    EditText sn;
    EditText pw;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sn = (EditText) findViewById(R.id.studienummer);
        pw = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.Sign_in);
        final Toast toast = Toast.makeText(getApplicationContext(), "Invalid login information", Toast.LENGTH_SHORT);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verified == true){
                    Intent i = new Intent(getApplicationContext(),TaskCategoryList.class);
                    startActivity(i);
                }
                else
                    toast.show();
            }
        });

    }

    private void submitLogin(String studienummer, String password){
        studienummer = sn.getText().toString();
        password = pw.getText().toString();


    }



}