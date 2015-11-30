package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Andreas on 26.11.2015.
 */
public class ProfileActivity extends BaseActivity {
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
    }
}
