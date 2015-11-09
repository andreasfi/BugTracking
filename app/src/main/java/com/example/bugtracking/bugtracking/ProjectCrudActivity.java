package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ProjectCrudActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pour retourner à Login Activity si on est pas connecté
       /* if(LoginActivity.CONNECTED==false){
            LoginActivity.MESSAGE_ERROR=true;
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }*/

        setContentView(R.layout.activity_project_crud);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Action pour ajouter une date de début
    public void clicAddStartDate(View view){
        EditText text=(EditText)findViewById(R.id.proStartDate);

        MyDatePickerFragment datePicker=new MyDatePickerFragment();
        datePicker.setView(text);
        datePicker.show(getSupportFragmentManager(),"Date Picker");

    }

    //Action pour ajouter une date de fin
    public void clicAddEndDate(View view){
        EditText text=(EditText)findViewById(R.id.proEndDate);

        MyDatePickerFragment datePicker=new MyDatePickerFragment();
        datePicker.setView(text);
        datePicker.show(getSupportFragmentManager(),"Date Picker");

    }
}
