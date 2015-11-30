package com.example.bugtracking.bugtracking;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;

import java.util.Iterator;
import java.util.List;


public class LoginActivity extends BaseActivity {

    boolean isLogin=false;

    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    //public final  static String PASSWORD="";
    public final  static String ETAT="Etat";
    public static boolean CONNECTED=false;
    public static String TEST_LOGIN;
    public static long ID=-1;

    //Test message d'erreur utilisateur non connecté
    public static boolean MESSAGE_ERROR =false;
    public static boolean WRON_LOGIN =false;

    private TextView messageError;
    private TextView messageError_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public void clicLogin(View view){
        /*Code provisoir pour naviguer entre les activity
        il sera nécéssaire de faire un contrôle de mot de passe
        avec la bae de donnée dans le futur
         */

        //Controle dans la base de donnée

        //____________________________________________________
        isLogin=true;
        Intent intent=new Intent(this, ProjectMainActivity.class);

        //Récupération des données depuis les TextField
        EditText login=(EditText) findViewById(R.id.login);
        String _login=login.getText().toString();


        EditText password=(EditText)findViewById(R.id.password);
        String _password=password.getText().toString();

       /* intent.putExtra(EXTRA_MESSAGE,_login);
        intent.putExtra("Password",_password);*/
        //intent.putExtra(ETAT,isLogin);
        DeveloperDataSource dds =new DeveloperDataSource(this);

        List<Developer> developers;
        developers=dds.getAllDevelopers();
        findDevelopper(developers, _login, _password);
        if(CONNECTED==true){
            startActivity(intent);
        }
        else{
            if(WRON_LOGIN==true){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Your login or password are wrong !")
                        .setTitle("Error!")
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }

    //Renvoie juste à l'Activity pour créer un nouveau Login
    public void clicRegister(View view){
        Intent intent =new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //Vérifie la validité du login
    public void findDevelopper(List<Developer> developers, String userName, String password){
        Iterator iterator=developers.iterator();

        int i;
        long id=-1;
        boolean find=false;

        for(i=0;i<developers.size();i++){
            if(developers.get(i).getUsername().equals(userName)){
                if(developers.get(i).getPassword().equals(password)){
                    ID=developers.get(i).getId();
                    CONNECTED=true;
                }
                else{
                    WRON_LOGIN=true;
                }
            }
            else{
                WRON_LOGIN=true;
            }
        }
        //TODO Essayer de récupérer les donnée avec un Iterator
       /* while(iterator.hasNext())
        {
            if(developers.get(i).getUsername().equals(userName)){
                if(developers.get(i).getPassword().equals(password)){
                    id=developers.get(i).getId();
                    LoginActivity.CONNECTED=true;
                }
            }

            i++;
        }*/
    }

}
