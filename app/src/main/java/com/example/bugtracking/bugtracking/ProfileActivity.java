package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Andreas on 26.11.2015.
 */
public class ProfileActivity extends AppCompatActivity {
    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    Button confirm_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Button logout_button = (Button) findViewById(R.id.button_logout);
        final Activity thisclass = this;
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log User out
                logout(thisclass);
                // Send back to main activity
            }
        });

        DeveloperDataSource dds =new DeveloperDataSource(thisclass);

        Developer developer = dds.getDeveloperByID(LoginActivity.ID);

        confirm_action = (Button) findViewById(R.id.button_action_confirm);
        confirm_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText currentField = (EditText) findViewById(R.id.current_pass);
                EditText newpassField= (EditText) findViewById(R.id.new_pass);
                EditText newpassrepeatField= (EditText) findViewById(R.id.new_pass_repeat);

                String current = currentField.getText().toString();
                String newpass = newpassField.getText().toString();
                String newpassrepeat = newpassrepeatField.getText().toString();

                String errormsg="";

                if(current != ""){
                    // Check if current password true
                    DeveloperDataSource dds =new DeveloperDataSource(thisclass);

                    Developer developer;
                    developer=dds.getDeveloperByID(LoginActivity.ID);


                    String errormsgpass ="";
                    // Check if current password is ok
                    if(LoginActivity.USER_PASSWORD.equals(current)){
                        // Check if passfield1 and passfield2 are the same
                        if(newpass.equals(newpassrepeat)){
                            // Update Dbg
                            developer.setPassword(newpass);
                            LoginActivity.USER_PASSWORD = newpass;
                            logout(thisclass);
                            dds.updateDeveloper(developer);
                            SQLiteHelper sqlHelper = SQLiteHelper.getInstance(thisclass);
                            sqlHelper.getWritableDatabase().close();

                            Intent intent = new Intent(thisclass, MainActivity.class);
                            startActivity(intent);
                        }else {
                            errormsg = "The new password doesn't match the control field";
                        }
                    } else {
                        errormsg = "The current password does not match";
                    }
                } else {
                    if(newpass != null){
                        errormsg = "Current password is required";
                    }
                }

                if(errormsg != ""){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setMessage(errormsg)
                            .setTitle("Error Message!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


    }
    public void logout(Activity thisclass){
        LoginActivity.CONNECTED = false;
        Intent intent = new Intent(thisclass, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        intent.putExtra("Password", "");
        startActivity(intent);
    }
    public void updateCloud(){
        List<com.example.andreas.myapplication.backend.developerApi.model.Developer> developers = new DeveloperDataSource(this).getAllNotUpdated();
        new EndpointsAsyncTask(developers);


    }
}
