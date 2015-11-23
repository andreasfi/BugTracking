package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.object.Bug;

import java.util.ArrayList;

/**
 * Created by Andreas on 25.10.2015.
 */
public class BugCrudActivity extends AppCompatActivity implements BugAssignDeveloperFragment.SelectionListener {
    Button openButton;
    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Test si l'utilisateur est connecté
       /* if(LoginActivity.CONNECTED==false){
            LoginActivity.MESSAGE_ERROR=true;
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }*/


        setContentView(R.layout.activity_bug_crud);

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerPriority);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner statespinner = (Spinner) findViewById(R.id.spinnerState);
        ArrayAdapter<CharSequence> stateadapter = ArrayAdapter.createFromResource(this,
                R.array.state_array, android.R.layout.simple_spinner_item);
        stateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statespinner.setAdapter(stateadapter);
        // get action button
        final Activity thisclass = this;
        final Button create_button = (Button) findViewById(R.id.createBug);

        // get intent info
        Intent intent=getIntent();
        final String action = intent.getStringExtra("action");
        // set button text
        create_button.setText(action);

        openButton = (Button)findViewById(R.id.assignedevelopers);
        openButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                BugAssignDeveloperFragment dialog = new BugAssignDeveloperFragment();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList(BugAssignDeveloperFragment.DATA, getItems());     // Require ArrayList
                bundle.putInt(BugAssignDeveloperFragment.SELECTED, 0);
                dialog.setArguments(bundle);
                dialog.show(manager, "Dialog");
            }
        });

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText titleView = (EditText) findViewById(R.id.title);
                String title = titleView.getText().toString();
                final EditText descriptionView = (EditText) findViewById(R.id.description);
                String description = descriptionView.getText().toString();
                final EditText reproductionView = (EditText) findViewById(R.id.reproduction);
                String reproduction = reproductionView.getText().toString();
                final EditText effectsView = (EditText) findViewById(R.id.effects);
                String effects = effectsView.getText().toString();
                Spinner prioritySpinner = (Spinner) findViewById(R.id.spinnerPriority);
                String priority = prioritySpinner.getSelectedItem().toString();
                Spinner stateSpinner = (Spinner) findViewById(R.id.spinnerState);
                String state = stateSpinner.getSelectedItem().toString();
                switch (action){
                    case "add":
                        if (!title.isEmpty()) {
                            if (!description.isEmpty()) {
                                if (!state.isEmpty()) {
                                    if (reproduction.isEmpty()) {
                                        reproduction = "";
                                    }
                                    if (effects.isEmpty()) {
                                        effects = "";
                                    }
                                    if (priority.isEmpty()) {
                                        priority = "";
                                    }
                                    BugDataSource bds = new BugDataSource(thisclass);
                                    Bug bug = new Bug();
                                    bug.setTitle(title);
                                    bug.setDescription(description);
                                    bug.setReproduce(reproduction);
                                    bug.setEffects(effects);
                                    bug.setPriority(priority);
                                    bug.setState(state);
                                    bug.setId((int) bds.createIssue(bug));

                                    SQLiteHelper sqlHelper = SQLiteHelper.getInstance(thisclass);
                                    sqlHelper.getWritableDatabase().close();
                                    Intent intent = new Intent(thisclass, BugActivity.class);
                                    //intent.putExtra(EXTRA_MESSAGE, username);
                                    //intent.putExtra("Password", password);
                                    startActivity(intent);


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(BugCrudActivity.this);
                                    builder.setMessage("State field is required.")
                                            .setTitle("Required!")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BugCrudActivity.this);
                                builder.setMessage("Description field is required.")
                                        .setTitle("Required!")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(BugCrudActivity.this);
                            builder.setMessage("Title field is required..")
                                    .setTitle("Required!")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        break;
                    case "edit":

                        break;
                }
            }
        });
    }
    private ArrayList<String> getItems()
    {
        ArrayList<String> ret_val = new ArrayList<String>();

        ret_val.add("Sylvain");
        ret_val.add("Andreas");

        return ret_val;
    }
    public void createBug(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void selectItem(int position) {
        Log.d("Favorites", getItems().get(position));
    }
}
