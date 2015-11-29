package com.example.bugtracking.bugtracking;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;
import com.example.bugtracking.bugtracking.object.Project;

import java.util.List;

public class ProjectCrudActivity extends AppCompatActivity implements ListDeveloperFragment.DeveloperDialogListener {

    long idProject;
    List<String> listdeveloperAssociate;
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

    //Fonction pour créer un nouveau Projet
    public void clicAddProject(View view){
        ProjectDataSource pds=new ProjectDataSource(this);
        EditText title=(EditText)findViewById(R.id.proCrudTitle);
        EditText description=(EditText)findViewById(R.id.proCrudDescription);
        EditText startDate=(EditText)findViewById(R.id.proStartDate);
        EditText endDate=(EditText)findViewById(R.id.proEndDate);

        //Récupération des données inserées
        Project project=new Project();
        project.setName(title.getText().toString());
        project.setDescription(description.getText().toString());
        project.setStartdate(startDate.getText().toString());
        project.setEnddate(endDate.getText().toString());

       /* TextView test=(TextView) findViewById(R.id.proCrudTest);
        test.setText(project.getName());*/
        //Création du projet
       idProject= pds.createProject(project);

        if(listdeveloperAssociate!=null){

            joinDeveloperToProject(listdeveloperAssociate, idProject);

        }

        SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();


        //Associe les developer choisis au projet.


        Intent intent = new Intent(this, ProjectMainActivity.class);
        startActivity(intent);
    }

    //Méthode utilisée par le DialogFragment
    public List<Developer> getListdeveloper(){
        DeveloperDataSource dds=new DeveloperDataSource(this);

        return dds.getAllDevelopers();
    }

    public void clicAddDeveloper(View view){
        showDialog();
    }

    //Aficche la liste de developpers dns un Dialog
    public void showDialog(){
        DialogFragment dialog=new ListDeveloperFragment();
        dialog.show(getSupportFragmentManager(), "ChoiceDeveloper");
    }
    //Ce qui se passe quand on clic sur ok
    @Override
    public void onDialogPositiveClick(List<String> usernameList) {
        TextView test=(TextView) findViewById(R.id.proCrudTest);
        test.setText(usernameList.get(0));
        listdeveloperAssociate=usernameList;
    }

    //Quand on clic sur "cancel"
    @Override
    public void onDialogNegativeClick() {

    }

    //Liaison entre le projet et les développeur séléctioné
    public void joinDeveloperToProject(List<String> developers, long idProject){
        DeveloperDataSource dds=new DeveloperDataSource(this);
        ProjectDeveloperDataSource pdds=new ProjectDeveloperDataSource(this);
        //Ajout du déveloper qui crée le projet
        pdds.createProjectDeveloper(LoginActivity.ID, idProject, "1");
        Developer developer;
        for(int i=0;i<developers.size();i++){

            developer=dds.getDeveloperByUsername(developers.get(i));
            //Ajout des developer associé
            pdds.createProjectDeveloper(developer.getId(), idProject, "0");
        }

    }
}
