package com.example.bugtracking.bugtracking;

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
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Andreas on 25.10.2015.
 */
public class BugCrudActivity extends AppCompatActivity implements BugAssignDeveloperFragment.SelectionListener {
    Button openButton;
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

        Spinner spinner = (Spinner) findViewById(R.id.spinnerPriority);
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
    }
    private ArrayList<String> getItems()
    {
        ArrayList<String> ret_val = new ArrayList<String>();

        ret_val.add("Sylvain");
        ret_val.add("Andreas");

        return ret_val;
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
