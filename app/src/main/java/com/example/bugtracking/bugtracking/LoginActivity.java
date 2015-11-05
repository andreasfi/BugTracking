package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    boolean isLogin=false;

    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    //public final  static String PASSWORD="";
    public final  static String ETAT="Etat";
    public static boolean CONNECTED=false;
    public static String TEST_LOGIN;

    //Test message d'erreur utilisateur non connecté
    public static boolean MESSAGE_ERROR =false;
    public static boolean WRON_LOGIN =false;

    public void clicLogin(View view){
        /*Code provisoir pour naviguer entre les activity
        il sera nécéssaire de faire un contrôle de mot de passe
        avec la bae de donnée dans le futur
         */
        isLogin=true;
        Intent intent=new Intent(this, ProjectMainActivity.class);
        EditText login=(EditText) findViewById(R.id.login);
        String _login=login.getText().toString();
        TEST_LOGIN=_login;

        EditText password=(EditText)findViewById(R.id.password);
        String _password=password.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,_login);
        intent.putExtra("Password",_password);
        //intent.putExtra(ETAT,isLogin);
        CONNECTED=true;
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
        TextView messageError;
        TextView messageError_login;
        setContentView(R.layout.activity_login);

        //Display the messages error if the password is wrong or if the user is not connected
        messageError=(TextView) findViewById(R.id.login_messageError);
        if(MESSAGE_ERROR==true){
            messageError.setVisibility(View.VISIBLE);
        }

        messageError_login=(TextView) findViewById(R.id.login_messageError2);
        if(WRON_LOGIN==true){
            messageError_login.setVisibility(View.VISIBLE);
        }

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
