package com.example.bugtracking.bugtracking;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ProjectMainActivity extends AppCompatActivity {

    TextView item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Récupération de username (login) + stock dans une variable pour l'affichage
        Intent intent=getIntent();
        String login=intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        TextView textLogin=new TextView(this);
        textLogin.setText("User : "+login);
        textLogin.setPadding(400, 16, 0, 16);


       // setContentView(R.layout.activity_project_main);



        /*Création d'un affichage dynamique fonctionne partiellement, probléme
            avec le one clic listener. Ca fonctionne que sur le dernière item.
         */
        //TODO Essayer d'intergrer le layout "activity_project_textview_layout
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

        ll.addView(textLogin);
        //Création des Items de manière dynamique
      /*  for(int i=0;i<15;i++){
             item=new TextView(this);
            item.setText("Item " + i);
            item.setPadding(16, 16, 16, 16);

            //Fonction utilisée lorsque l'on clic sur un élément
           item.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   item.setText("New text");
               }
           });

           // ll.addView(item);

        }*/
         ll.addView(addbutton);
        //Ici on ajoute le linearLayout dans le ScrollView
        scroll.addView(ll);
        setContentView(scroll);



        //TODO Essyayer de récupérer les objet LinearLayout et TextView depuis le fichier xml
        //tentative de récupérer les objets LinearLayout et TextView depuis le fichier xml. NE MARCHE PAS
       /* LinearLayout ll2=(LinearLayout)findViewById(R.id.linearLayoutProject);
        TextView item2=(TextView)findViewById(R.id.itemProject);
        ll2.addView(item2);
        setContentView(ll2);*/
    }

    //Methode "Listener" utilisée pas le fichier xml, non fonctionnel pour l'instant
    public void goTo_Issue(View view){


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

    }

    public void goTo_ProjectCRUD(View view){
        Intent intent=new Intent(this, ProjectCrudActivity.class);
        startActivity(intent);

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

    class onClicTextView {

    }
}
