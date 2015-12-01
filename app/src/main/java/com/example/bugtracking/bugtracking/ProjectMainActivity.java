package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.ProjectDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Project;
import com.example.bugtracking.bugtracking.object.ProjectDeveloper;

import java.util.List;

public class ProjectMainActivity extends BaseActivity {

    TextView item;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_main);

        //Récupération de username (login) + stock dans une variable pour l'affichage
       // setContentView(R.layout.activity_project_main);

        //final List<Project> projects=findProject();
        //List<ProjectDeveloper> devPro=findDeveloperAssociate();



        /*Création d'un affichage dynamique fonctionne partiellement, probléme
            avec le one clic listener. Ca fonctionne que sur le dernière item.
         */
        final Activity thisclass = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open add bug activity

                Intent intent = new Intent(thisclass, ProjectCrudActivity.class);
                intent.putExtra("action", "add");
                //startActivity(intent);
            }
        });


        ListView projectview = (ListView) findViewById(R.id.listViewProjects);
        ProjectDataSource pds = new ProjectDataSource(this);
        List<Project> projects = pds.getAllProjects();

        if(!projects.isEmpty()){
            final ArrayAdapter<Project> adapter;
            adapter = new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projects);

            // Remplir la listview
            projectview.setAdapter(adapter);

            // gere si on click sur la listview
            projectview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Project project = (Project) adapter.getItem(position);
                    Intent intent = new Intent(ProjectMainActivity.this , ProjectCrudActivity.class);
                    intent.putExtra("action", "edit");
                    intent.putExtra("id", project.getId());
                    startActivity(intent);
                }
            });
        }


        //TODO Essyayer de récupérer les objet LinearLayout et TextView depuis le fichier xml
        //tentative de récupérer les objets LinearLayout et TextView depuis le fichier xml. NE MARCHE PAS
       /* LinearLayout ll2=(LinearLayout)findViewById(R.id.linearLayoutProject);
        TextView item2=(TextView)findViewById(R.id.itemProject);
        ll2.addView(item2);
        setContentView(ll2);*/
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListView projectview = (ListView) findViewById(R.id.listViewProjects);
        ProjectDataSource pds = new ProjectDataSource(this);
        List<Project> projects = pds.getAllProjects();

        if(!projects.isEmpty()){
            final ArrayAdapter<Project> adapter;
            adapter = new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projects);

            // Remplir la listview
            projectview.setAdapter(adapter);

            // gere si on click sur la listview
            projectview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Project project = (Project) adapter.getItem(position);
                    Intent intent = new Intent(ProjectMainActivity.this , ProjectCrudActivity.class);
                    intent.putExtra("action", "edit");
                    intent.putExtra("id", project.getId());
                    startActivity(intent);
                }
            });
        }
        return inflater.inflate(R.layout.activity_project_main, container, false);
    }

    public void goTo_ProjectCRUD(View view){
        Intent intent=new Intent(this, ProjectCrudActivity.class);
        startActivity(intent);

    }


    public List<Project> findProject(){
        ProjectDataSource pds=new ProjectDataSource(this);

        return pds.getAllProjects();
    }

    public List<ProjectDeveloper> findDeveloperAssociate(){
        ProjectDeveloperDataSource pdds =new ProjectDeveloperDataSource(this);

        return pdds.getAllDevelopersProject();
    }

    class onClicTextView {

    }


    public void clicFloat(View view){

    }

}
