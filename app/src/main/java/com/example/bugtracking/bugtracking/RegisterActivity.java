package com.example.bugtracking.bugtracking;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText usernameView = (EditText) findViewById(R.id.username);
        final EditText passwordView = (EditText) findViewById(R.id.password);
        final EditText passwordrepeatView = (EditText) findViewById(R.id.passwordrepeat);
        Button register_button = (Button) findViewById(R.id.register_button);

        // Pass this(context) to the inner class
        final Context thisclass = this;

        // Inner class for button with listener
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get values
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();
                String passwordrepeat = passwordrepeatView.getText().toString();

                //ArrayList<Bugtracking.DeveloperEntry> = new List<Bugtracking.DeveloperEntry>();
                //DeveloperDataSource developers = new DeveloperDataSource();

                if(!username.isEmpty() || !password.isEmpty() || !passwordrepeat.isEmpty()){ // Check if a field is empty
                    if(password.equals(passwordrepeat)){ // check if password is the same

                        DeveloperDataSource developerds = new DeveloperDataSource(thisclass);

                        List<Developer> developers = developerds.getAllDevelopers();


                        if(!developers.contains(username)){ // check if username is unique
                            // Add a new user to the database
                            Developer developer = new Developer();

                            developer.setUsername(username);
                            developer.setPassword(password);
                            developer.setLang("en");

                            developer.setId((int) developerds.createDeveloper(developer));
                            // Go to project activity
                        } else {
                            // show error
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("This user already exists.")
                                    .setTitle("Error!")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    } else {
                        // show error
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Password does not match the Password repeat field.")
                                .setTitle("Error!")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                } else {
                    // show error -> need to fill out the fields first
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Please make sure you entered all the fields correctly.")
                            .setTitle("Not complete!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(thisclass);
                sqlHelper.getWritableDatabase().close();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



}
