package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

       /* if(LoginActivity.CONNECTED==false){
            LoginActivity.MESSAGE_ERROR=true;
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }*/
        //Récupération de username (login) + stock dans une variable pour l'affichage
       // setContentView(R.layout.activity_project_main);

        final List<Project> projects=findProject();
        List<ProjectDeveloper> devPro=findDeveloperAssociate();
       // setContentView(R.layout.activity_project_main);

        /*Création d'un affichage dynamique fonctionne partiellement, probléme
            avec le one clic listener. Ca fonctionne que sur le dernière item.
         */
        //TODO Essayer d'intergrer le layout "activity_project_textview_layout
        FloatingActionButton addProjectFloatButton=new FloatingActionButton(this);
       // addProjectFloatButton=(FloatingActionButton)findViewById(R.id.fab2);
        addProjectFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProjectMainActivity.this, ProjectCrudActivity.class);
                startActivity(intent);
            }

        });

        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(16, 16, 16, 16);
        //Création du boutton
        Button addbutton=new Button(this);
        addbutton.setText("Add");
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProjectMainActivity.this, ProjectCrudActivity.class);
                startActivity(intent);
            }
        });


        //Création de la scrollbar
        ScrollView scroll=new ScrollView(this);
        //Création des Items de manière dynamique
        for(int i=0;i<projects.size();i++){
            item=new TextView(this);
            item.setText(projects.get(i).getName());
            final long idProject=projects.get(i).getId();
            item.setPadding(16, 16, 16, 16);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ProjectMainActivity.this, BugActivity.class);

                    intent.putExtra("idProject", idProject);
                    startActivity(intent);
                }
            });
            ll.addView(item);
        }

        for(int i=0;i<devPro.size();i++){
            item=new TextView(this);
            item.setText("ID "+devPro.get(i).getId()+" DEV_ID "+devPro.get(i).getDevID()+
            " PRO_ID "+devPro.get(i).getProID()+" ROLE "+devPro.get(i).getRole());
            item.setPadding(16,16,16,16);
            ll.addView(item);
        }
      //  ll.addView(addProjectFloatButton);
        ll.addView(addbutton);

        //Ici on ajoute le linearLayout dans le ScrollView
        scroll.addView(ll);
       // scroll.addView(addProjectFloatButton);

        setContentView(scroll);
        //setContentView(R.layout.transparent_layout);




        //TODO Essyayer de récupérer les objet LinearLayout et TextView depuis le fichier xml
        //tentative de récupérer les objets LinearLayout et TextView depuis le fichier xml. NE MARCHE PAS
       /* LinearLayout ll2=(LinearLayout)findViewById(R.id.linearLayoutProject);
        TextView item2=(TextView)findViewById(R.id.itemProject);
        ll2.addView(item2);
        setContentView(ll2);*/
    }

    //Methode "Listener" utilisée pas le fichier xml, non fonctionnel pour l'instant
   /* public void goTo_Issue(View view){


        TextView items[] =new TextView[5];
        items[0]=(TextView)findViewById(R.id.itemProject);
        items[1]=(TextView)findViewById(R.id.itemProject2);
        items[2]=(TextView)findViewById(R.id.itemProject3);
        items[3]=(TextView)findViewById(R.id.itemProject4);
        items[4]=(TextView)findViewById(R.id.itemProject5);

        if(items[0].isClickable()){
            items[0].setText("Selected0");
        }

        if(items[1].isFocused()){
            items[1].setText("Selected1");
        }
        if(items[3].isFocused()){
            items[3].setText("Selected3");
        }
        if(items[4].isClickable()){
            items[4].setText("Selected4");
        }
        if(items[2].isClickable()) {
            items[2].setText("Selected2");
        }

    }*/

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
