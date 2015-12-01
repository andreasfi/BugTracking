package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Bug;
import com.example.bugtracking.bugtracking.object.Project;
import com.example.bugtracking.bugtracking.object.ProjectDeveloper;

import java.util.List;

public class ProjectMainActivity extends BaseActivity {
    ArrayAdapter<Project> adapter;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_main);

        final Activity thisclass = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thisclass, ProjectCrudActivity.class);
                intent.putExtra("action", "add");
                startActivity(intent);
            }
        });


        final ListView projectview = (ListView) findViewById(R.id.listViewProjects);
        ProjectDataSource pds = new ProjectDataSource(this);
        final List<Project> projects = pds.getAllProjects();

        if(!projects.isEmpty()){

            adapter = new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projects);

            // Remplir la listview
            projectview.setAdapter(adapter);

            // gere si on click sur la listview
            projectview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Project project = (Project) parent.getAdapter().getItem(position);
                    Intent intent=new Intent(ProjectMainActivity.this, BugActivity.class);
                    Log.d("ID_PROJECT ",""+project.getId());
                    intent.putExtra("idProject", project.getId());
                    startActivity(intent);
                    /*
                    Project project = (Project) parent.getAdapter().getItem(position);
                    Intent intent = new Intent(ProjectMainActivity.this , ProjectCrudActivity.class);
                    intent.putExtra("update", true);
                    intent.putExtra("idproject", project.getId());
                    startActivity(intent);
                    */
                }
            });
            registerForContextMenu(projectview);
        }


        //TODO Essyayer de récupérer les objet LinearLayout et TextView depuis le fichier xml
        //tentative de récupérer les objets LinearLayout et TextView depuis le fichier xml. NE MARCHE PAS
       /* LinearLayout ll2=(LinearLayout)findViewById(R.id.linearLayoutProject);
        TextView item2=(TextView)findViewById(R.id.itemProject);
        ll2.addView(item2);
        setContentView(ll2);*/
    }

    public ArrayAdapter<Project> getAdapter() {
        return adapter;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listViewProjects) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;


            Adapter adapter = getAdapter();
            Project project= (Project)adapter.getItem(acmi.position);
            Log.d("aldskfjalösdkfj", project.getName());

            menu.setHeaderTitle(project.getName());
            menu.add("Edit");
            menu.add("Delete");
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Project project = getAdapter().getItem(info.position);
        Log.d("img it workeeeeees", project.getName());


        ListView lv = (ListView) findViewById(R.id.listViewIssues);
        //Log.d("help", "help me here2 " + lv.getSelectedItem() + " si " +item.getItemId() + " o " + item.getTitle());

        // item.getTitle()
        switch ((String)item.getTitle()){
            case "Edit":
                Intent intent = new Intent(this , ProjectCrudActivity.class);
                intent.putExtra("update", true);
                intent.putExtra("idproject", (long)project.getId());
                startActivity(intent);
                break;
            case "Delete":
                ProjectDataSource pds = new ProjectDataSource(this);
                pds.deleteProject(project.getId());

                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(this);
                sqlHelper.getWritableDatabase().close();

                Intent intent2 = new Intent(this, ProjectMainActivity.class);
                startActivity(intent2);
                break;

        }

        return true;
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


}
