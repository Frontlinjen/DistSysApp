package com.example.nicki.distsysapp;


import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        final Toast toast = Toast.makeText(getApplicationContext(), "Unable to login, check your connection and spelling", Toast.LENGTH_SHORT);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager accountManager = AccountManager.get(getApplicationContext());
<<<<<<< Updated upstream
                //accountManager.

=======
>>>>>>> Stashed changes
            /*try {
                    verified = awsClient.login(sn.getText().toString(), pw.getText().toString());
                } catch (WebApplicationException e) {
                    toast.show();
                    e.printStackTrace();
                }
                if (verified){
                    Intent i = new Intent(getApplicationContext(),MainMenu.class);
                    startActivity(i);
                }*/

            }
        });

    }

}