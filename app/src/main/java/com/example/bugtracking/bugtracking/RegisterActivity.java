package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText usernameView = (EditText) findViewById(R.id.username);
        final EditText passwordView = (EditText) findViewById(R.id.password);
        final EditText passwordrepeatView = (EditText) findViewById(R.id.passwordrepeat);
        Button register_button = (Button) findViewById(R.id.register_button);

        // Pass this(context) to the inner class
        final Activity thisclass = this;

        // Inner class for button with listener
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get values
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();
                String passwordrepeat = passwordrepeatView.getText().toString();
                if (!username.isEmpty() || !password.isEmpty() || !passwordrepeat.isEmpty()) { // Check if a field is empty
                    if (password.equals(passwordrepeat)) { // check if password is the same

                        //DeveloperDataSource developerds = new DeveloperDataSource(thisclass);
                        //List<Developer> developers = developerds.getAllDevelopers();
                        //SQLiteHelper sqLiteHelper = new SQLiteHelper(thisclass);

                        DeveloperDataSource dds = new DeveloperDataSource(thisclass);
                        List<Developer> developers = dds.getAllDevelopers();
                        boolean developerExists = true;

                        for(int i = 0; i < developers.size(); i++){
                            if(developers.get(i).getUsername().toString().equals(username)){
                                developerExists = false;
                                Log.d("MyApp","OOOMMMMGGG it worked -----------------");
                            }
                        }
                        if (developerExists || developers.isEmpty()) { // check if username is unique
                            // Add a new user to the database
                            Developer developer = new Developer();

                            developer.setUsername(username);
                            developer.setPassword(password);
                            developer.setLang("en");

                            developer.setId((int) dds.createDeveloper(developer));

                            SQLiteHelper sqlHelper = SQLiteHelper.getInstance(thisclass);
                            sqlHelper.getWritableDatabase().close();
                            //@todo Set user to logged in
                            //@todo Go to project activity
                            Intent intent = new Intent(thisclass, ProjectMainActivity.class);
                            intent.putExtra(EXTRA_MESSAGE,username);
                            intent.putExtra("Password",password);
                            startActivity(intent);
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
