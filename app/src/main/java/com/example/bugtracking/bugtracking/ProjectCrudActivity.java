package com.example.bugtracking.bugtracking;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;
import com.example.bugtracking.bugtracking.object.Project;

import java.util.List;

public class ProjectCrudActivity extends BaseActivity implements ListDeveloperFragment.DeveloperDialogListener {

    private long idProject;
    private List<String> listdeveloperAssociate;
    private boolean update=false;
    private EditText title;
    private EditText description;
    private EditText startDate;
    private EditText endDate;
    private Button updateDeveloper;
    private Button deleteDeveloper;
    private Button addDeveloper;
    private Project project;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pour retourner à Login Activity si on est pas connecté
       /* if(LoginActivity.CONNECTED==false){
            LoginActivity.MESSAGE_ERROR=true;
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }*/

        Intent intent=getIntent();
        update=intent.getBooleanExtra("update", false);
        setContentView(R.layout.activity_project_crud);
        //Dans le cas ou on souhaite modifier le projet
        if(update==true){
            idProject=intent.getLongExtra("idProject", -1);

            title=(EditText)findViewById(R.id.proCrudTitle);
            description=(EditText)findViewById(R.id.proCrudDescription);
            startDate=(EditText)findViewById(R.id.proStartDate);
            endDate=(EditText) findViewById(R.id.proEndDate);
            updateDeveloper=(Button)findViewById(R.id.updateProjectCrud);
            updateDeveloper.setHeight(20);
            updateDeveloper.setVisibility(View.VISIBLE);

            deleteDeveloper=(Button)findViewById(R.id.deleteProjectCrud);
            deleteDeveloper.setHeight(20);
            deleteDeveloper.setVisibility(View.VISIBLE);

            addDeveloper=(Button)findViewById(R.id.AddProjectCrud);
            addDeveloper.setVisibility(View.INVISIBLE);
            addDeveloper.setHeight(0);

            project=searchProject(idProject);

            title.setText(project.getName());
            description.setText(project.getDescription());
            startDate.setText(project.getStartdate());
            endDate.setText(project.getEnddate());
            endDate.setText(project.getEnddate());
        }

        else{
            updateDeveloper=(Button)findViewById(R.id.updateProjectCrud);

            updateDeveloper.setVisibility(View.INVISIBLE);

            deleteDeveloper=(Button)findViewById(R.id.deleteProjectCrud);

            deleteDeveloper.setVisibility(View.INVISIBLE);
        }


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
         title=(EditText)findViewById(R.id.proCrudTitle);
         description=(EditText)findViewById(R.id.proCrudDescription);
         startDate=(EditText)findViewById(R.id.proStartDate);
         endDate=(EditText)findViewById(R.id.proEndDate);

        //Récupération des données inserées
        project=new Project();
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

    public void clicUpdateDeveloper(View view){
        ProjectDataSource pds=new ProjectDataSource(this);
        title=(EditText)findViewById(R.id.proCrudTitle);
        description=(EditText)findViewById(R.id.proCrudDescription);
        startDate=(EditText)findViewById(R.id.proStartDate);
        endDate=(EditText)findViewById(R.id.proEndDate);

        //Récupération des données inserées
        //Project project=new Project();
        project.setName(title.getText().toString());
        project.setDescription(description.getText().toString());
        project.setStartdate(startDate.getText().toString());
        project.setEnddate(endDate.getText().toString());

        pds.updateProject(project);

        SQLiteHelper sqlHelper=SQLiteHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();

        Intent intent = new Intent(this, ProjectMainActivity.class);
        startActivity(intent);
    }


    public void clicDeletProject(View view) {

        ProjectDataSource pds = new ProjectDataSource(this);
        pds.deleteProject(idProject);

        SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();

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

    public Project searchProject(long id){
        ProjectDataSource pds=new ProjectDataSource(this);
        return pds.getProjectByID(id);
    }
}
