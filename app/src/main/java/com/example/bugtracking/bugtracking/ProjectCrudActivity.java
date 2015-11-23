package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.ProjectDataSource;
import com.example.bugtracking.bugtracking.object.Project;

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

    public void clicAddProject(View view){
        ProjectDataSource pds=new ProjectDataSource(this);
        EditText title=(EditText)findViewById(R.id.proCrudTitle);
        EditText description=(EditText)findViewById(R.id.proCrudDescription);
        EditText startDate=(EditText)findViewById(R.id.proStartDate);
        EditText endDate=(EditText)findViewById(R.id.proEndDate);

        Project project=new Project();
        project.setName(title.getText().toString());
        project.setDescription(description.getText().toString());
        project.setStartdate(startDate.getText().toString());
        project.setEnddate(endDate.getText().toString());

       /* TextView test=(TextView) findViewById(R.id.proCrudTest);
        test.setText(project.getName());*/
        pds.createProject(project);
        SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();

        Intent intent = new Intent(this, ProjectMainActivity.class);
        startActivity(intent);
    }
}
