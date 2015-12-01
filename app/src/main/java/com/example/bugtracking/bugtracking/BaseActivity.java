package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Andreas on 30.11.2015.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Code qui (ENFIN) affiche le menu crée dans /menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_profil:
                Intent intent=new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:
                Intent intent2=new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_settings:
                Intent intent3=new Intent(this, SettingsActivity.class);
                startActivity(intent3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
