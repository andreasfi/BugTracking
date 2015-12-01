package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;
import com.example.bugtracking.bugtracking.object.Project;

import java.util.Locale;

/**
 * Created by Andreas on 30.11.2015.
 */
public class SettingsActivity extends AppCompatActivity {
    Button confirm_action;
    Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Activity thisclass = this;
        final Spinner langspinner = (Spinner) findViewById(R.id.spinner_lang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lang_array, android.R.layout.simple_spinner_item); // Create an ArrayAdapter using the string array and a default spinner layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        langspinner.setAdapter(adapter);// Apply the adapterCurrent to the spinner

        DeveloperDataSource dds =new DeveloperDataSource(thisclass);

        Developer developer = dds.getDeveloperByID(LoginActivity.ID);

        // Put the lang in an int
        int langselection = 0;
        Log.d("string", developer.getLang() + " " + LoginActivity.ID + " " + developer.getUsername());
        switch (developer.getLang()){
            case "EN":
                langselection = 0;
                break;
            case "FR":
                langselection = 1;
                break;
            default:
                langselection = 0;
        }
        // Set the spinner to the developers lang
        langspinner.setSelection(langselection);
        confirm_action = (Button) findViewById(R.id.button_action_confirm);
        confirm_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errormsg="";
                DeveloperDataSource dds =new DeveloperDataSource(thisclass);

                Developer developer;
                developer=dds.getDeveloperByID(LoginActivity.ID);

                // Update Db
                String spinnerlang = langspinner.getSelectedItem().toString();
                developer.setLang(spinnerlang);
                dds.updateDeveloper(developer);
                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(thisclass);
                sqlHelper.getWritableDatabase().close();

                setLocale(spinnerlang.toLowerCase());

                Intent intent = new Intent(thisclass, ProjectMainActivity.class);
                startActivity(intent);


                if(errormsg != ""){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                    builder.setMessage(errormsg)
                            .setTitle("Error Message!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }
    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, SettingsActivity.class);
        startActivity(refresh);
    }
}
