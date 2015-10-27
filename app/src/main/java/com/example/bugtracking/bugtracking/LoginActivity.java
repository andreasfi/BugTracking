package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";

    public void clicLogin(View view){
        /*Code provisoir pour naviguer entre les activity
        il sera nécéssaire de faire un contrôle de mot de passe
        avec la bae de donnée dans le futur
         */

        Intent intent=new Intent(this, ProjectMainActivity.class);//Il faut créer la classe "Projectactivity pour que 4a fonctionne
        EditText login=(EditText) findViewById(R.id.login);
        String _login=login.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,_login);
        startActivity(intent);
    }

    //Renvoie juste à l'Activity pour créer un nouveau Login
    public void clicRegister(View view){
        Intent intent =new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
