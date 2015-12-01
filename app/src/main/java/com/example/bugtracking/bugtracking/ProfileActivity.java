package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Andreas on 26.11.2015.
 */
public class ProfileActivity extends AppCompatActivity {
    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button logout_button = (Button) findViewById(R.id.button_logout);
        final Activity thisclass = this;
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log User out
                LoginActivity.CONNECTED = false;
                Intent intent=new Intent(thisclass, MainActivity.class);
                intent.putExtra(EXTRA_MESSAGE,"");
                intent.putExtra("Password","");
                startActivity(intent);
                // Send back to main activity
            }
        });

        // Fill Spinner
        Spinner langspinner = (Spinner) findViewById(R.id.spinner_lang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item); // Create an ArrayAdapter using the string array and a default spinner layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        langspinner.setAdapter(adapter);// Apply the adapter to the spinner

        EditText current = (EditText) findViewById(R.id.current_pass);
        EditText newpass= (EditText) findViewById(R.id.new_pass);
        EditText newpassrepeat= (EditText) findViewById(R.id.new_pass_repeat);

        String errormsg="";

        if(current != null){
            // Check if current password true

            // Check if pas1 pas2 are the same
            if(newpass == newpassrepeat){
                // Update Db
            }
        } else {
            if(newpass != null){
                errormsg += "Current password is required";
            }
        }

        if(langspinner != null){
            // Update db
        }
    }
}
