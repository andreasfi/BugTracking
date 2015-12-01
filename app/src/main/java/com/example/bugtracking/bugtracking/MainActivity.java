package com.example.bugtracking.bugtracking;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Mon comentaire test
    // Commentaire test 2

    //@// TODO: 03.12.2015 Implementation swipe calendrier -> Sylvain
    //@// TODO: 03.12.2015 Login authentification -> Sylvain ------OK-------
    //@// TODO: 03.12.2015 Assign developer -> Andreas
    //@// TODO: 03.12.2015 Structure activites -> Andreas
    //@// TODO: 03.12.2015 Créer faux Data + affichage -> Andreas & Sylvain
    //@// TODO: 03.12.2015 Code java pour les activités -> Andreas & Sylvain
    //@// TODO: 03.12.2015 Button plus (materialize) -> ..
    //@// TODO: 03.12.2015 Design -> Andreas
    //@// TODO: 03.12.2015 Add profile crud Activity -> ...
    //@// TODO: 03.12.2015 Check dimensions -> Andreas
    //@// TODO: 03.12.2015 Create comment system -> Andreas & Sylvain

    //@// TODO: 03.12.2015 Adapt Menu (developer image) -> ...
    //@// TODO: 03.12.2015 Give a comment a CommentEntry Ref -> ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

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
        String errormsg = "";
        switch (id){
            case R.id.action_profil:
                // Set error
                errormsg = "You need to be logged in to acces this page.";
                break;
            case R.id.action_about:
                Intent intent2=new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_settings:
                errormsg = "You need to be logged in to acces this page.";
                break;
        }

        // Show error
        if(errormsg != ""){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(errormsg)
                    .setTitle("Error!")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToLogIn(View view){
        // Renvoye vers le login
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void goToRegister(View view){
        // Renvoye vers le register
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
