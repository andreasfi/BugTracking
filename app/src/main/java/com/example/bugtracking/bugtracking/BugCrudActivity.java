package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
import com.example.bugtracking.bugtracking.adapter.BugDeveloperDataSource;
import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.adapter.ProjectDeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Bug;
import com.example.bugtracking.bugtracking.object.DevIssue;
import com.example.bugtracking.bugtracking.object.Developer;
import com.example.bugtracking.bugtracking.object.ProjectDeveloper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 25.10.2015.
 */
public class BugCrudActivity extends BaseActivity implements ListBugDeveloperFragment.DeveloperDialogListener {
    Button developer_add_button;
    Button bug_action_button;
    EditText titleView;
    EditText descriptionView;
    EditText reproductionView;
    EditText effectsView;
    Spinner priorityspinner;
    Spinner statespinner;
    long bugId;
    long proId;
    List<String>listDeveloperAssociate;

    public final  static String EXTRA_MESSAGE="com.example.bugtracking.bugtracking.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_crud);
        final Activity thisclass = this; // Get this

        // get intent info
        Intent intent=getIntent();
        final String action = intent.getStringExtra("action");
        final long bugid = intent.getLongExtra("id", 1L);
        final long proid2 = intent.getLongExtra("idpro", 1L);

        this.bugId=bugid;
        this.proId=proid2;
        titleView = (EditText) findViewById(R.id.title);
        descriptionView = (EditText) findViewById(R.id.description);
        reproductionView = (EditText) findViewById(R.id.reproduction);
        effectsView = (EditText) findViewById(R.id.effects);


        priorityspinner = (Spinner) findViewById(R.id.spinnerPriority); // Get the spinner element
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item); // Create an ArrayAdapter using the string array and a default spinner layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        priorityspinner.setAdapter(adapter);// Apply the adapterCurrent to the spinner

        statespinner = (Spinner) findViewById(R.id.spinnerState);
        ArrayAdapter<CharSequence> stateadapter = ArrayAdapter.createFromResource(this,
                R.array.state_array, android.R.layout.simple_spinner_item);
        stateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statespinner.setAdapter(stateadapter);

        switch (action){
            case "add":
                break;
            case "edit":
                // fill up xml
                BugDataSource bds = new BugDataSource(this);
                Bug bug = bds.getBugById(bugid);

                int priorityselection = 0;
                int stateselection = 0;
                titleView.setText(bug.getTitle());
                descriptionView.setText(bug.getDescription());
                reproductionView.setText(bug.getReproduce());
                effectsView.setText(bug.getEffects());
                switch (bug.getPriority()){
                    case "High":
                        priorityselection = 0;
                        break;
                    case "Medium":
                        priorityselection = 1;
                        break;
                    case "Low":
                        priorityselection = 2;
                        break;
                }
                priorityspinner.setSelection(priorityselection);
                switch (bug.getState()){
                    case "On Hold":
                        stateselection = 0;
                        break;
                    case "Current":
                        stateselection = 1;
                        break;
                    case "Solved":
                        stateselection = 2;
                        break;
                }
                statespinner.setSelection(stateselection);

                break;
        }


        developer_add_button = (Button)findViewById(R.id.assignedevelopers);
        developer_add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*FragmentManager manager = getFragmentManager();
                BugAssignDeveloperFragment dialog = new BugAssignDeveloperFragment();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList(BugAssignDeveloperFragment.DATA, getItems());     // Require ArrayList
                bundle.putInt(BugAssignDeveloperFragment.SELECTED, 0);
                dialog.setArguments(bundle);
                dialog.show(manager, "Dialog");*/
                DialogFragment dialog=new ListBugDeveloperFragment();
                dialog.show(getSupportFragmentManager(), "ChoiceDeveloper");
            }
        });

        bug_action_button = (Button) findViewById(R.id.createBug); // get action button
        bug_action_button.setText(action); // set button text from intent
        bug_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleView.getText().toString();
                String description = descriptionView.getText().toString();
                String reproduction = reproductionView.getText().toString();
                String effects = effectsView.getText().toString();
                String priority = priorityspinner.getSelectedItem().toString();
                String state = statespinner.getSelectedItem().toString();
                int devid = 0;
                int proid = (int)proid2;

                String category = "";
                String reference = "";
                String date = "";

                String errormsg = "";

                if (title.isEmpty()) {
                    errormsg = errormsg + "Title field is required. ";
                }
                if (description.isEmpty()) {
                    errormsg = errormsg + "Description field is required. ";
                }
                if (state.isEmpty()) {
                    errormsg = errormsg + "State field is required. ";
                }
                if (reproduction.isEmpty()) {
                    reproduction = "";
                }
                if (effects.isEmpty()) {
                    effects = "";
                }
                if (priority.isEmpty()) {
                    priority = "";
                }

                if(errormsg == ""){
                    switch (action) {
                        case "add":
                            BugDataSource bds = new BugDataSource(thisclass);
                            Bug bug = new Bug();
                            bug.setTitle(title);
                            bug.setDescription(description);
                            bug.setReference(reference);
                            bug.setCategory(category);
                            bug.setReproduce(reproduction);
                            bug.setEffects(effects);
                            bug.setPriority(priority);
                            bug.setState(state);
                            bug.setDate(date);
                            bug.setProjectId(proid);
                            bug.setDevId(devid);


                            bug.setId((int) bds.createIssue(bug));
                            if(listDeveloperAssociate!=null){

                                joinDeveloperToBug(listDeveloperAssociate, bug.getId());

                            }

                            SQLiteHelper sqlHelper = SQLiteHelper.getInstance(thisclass);
                            sqlHelper.getWritableDatabase().close();

                            Intent intent = new Intent(thisclass, BugActivity.class);
                            startActivity(intent);
                            break;
                        case "edit":
                            BugDataSource bds2 = new BugDataSource(thisclass);
                            Bug bugEdit = new Bug();
                            bugEdit.setId((int)bugid);
                            bugEdit.setTitle(title);
                            bugEdit.setDescription(description);
                            bugEdit.setReference(reference);
                            bugEdit.setCategory(category);
                            bugEdit.setReproduce(reproduction);
                            bugEdit.setEffects(effects);
                            bugEdit.setPriority(priority);
                            bugEdit.setState(state);
                            bugEdit.setDate(date);
                            bugEdit.setProjectId(proid);
                            bugEdit.setDevId(devid);

                            bds2.updateIssue(bugEdit);
                            if(listDeveloperAssociate!=null){

                                joinDeveloperToBug(listDeveloperAssociate, bugEdit.getId());

                            }
                            SQLiteHelper sqlHelper2 = SQLiteHelper.getInstance(thisclass);
                            sqlHelper2.getWritableDatabase().close();

                            Intent intent2 = new Intent(thisclass, BugActivity.class);
                            startActivity(intent2);
                            break;
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BugCrudActivity.this);
                    builder.setMessage(errormsg)
                            .setTitle("Error Message!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
    public void onPause() {
        super.onPause();  // Always call the superclass method first
    }
    private ArrayList<String> getItems()
    {
        ArrayList<String> ret_val = new ArrayList<String>();

        ret_val.add("Sylvain");
        ret_val.add("Andreas");

        return ret_val;
    }

    public List<Developer> getDeveloperFromProject(){
        //Get DeveloperId in link with the project
        List<ProjectDeveloper> listDev;
        ProjectDeveloperDataSource pdd=new ProjectDeveloperDataSource(this);
        listDev=pdd.getDevelopersProjectByIdDev(proId);
        Log.d("Project ID "," "+proId);


        //get developers with id
        List<Developer> developers=new ArrayList<>();
        DeveloperDataSource dds=new DeveloperDataSource(this);
        for(int i=0;i<listDev.size();i++){
            developers.add(dds.getDeveloperByID(listDev.get(i).getDevID()));
        }
        return developers;
    }


    @Override
    public void onDialogPositiveClick(List<String> usernameList) {
        this.listDeveloperAssociate=usernameList;

    }

    @Override
    public void onDialogNegativeClick() {

    }

    //Link beetween Developer and Issue
    public void joinDeveloperToBug(List<String> developers, long idBug){
        DeveloperDataSource dds=new DeveloperDataSource(this);

        BugDeveloperDataSource bdds=new BugDeveloperDataSource(this);
        Developer developer;
        for(int i=0;i<developers.size();i++){

            developer=dds.getDeveloperByUsername(developers.get(i));
            //Add developer to Issue
            bdds.createIssueDeveloper(developer.getId(), idBug);
        }
    }
}
