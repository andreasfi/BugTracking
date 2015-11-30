package com.example.bugtracking.bugtracking;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.object.Bug;

import java.util.List;


public class BugSolvedFragment extends Fragment{

    public BugSolvedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_bug_onhold, container, false);
        final ListView issuesview = (ListView) rootview.findViewById(R.id.listViewIssues);

        final BugActivity activity = (BugActivity) getActivity();
        // Get db items Get project id
        BugDataSource ids = new BugDataSource(activity);
        List<Bug> bugs = ids.getAllIssueByProjectState(activity.getProjectid(), "Solved"); // get only current!!! need to change

        // Put values in layout
        if (!bugs.isEmpty()){
            ArrayAdapter<Bug> adapter;
            adapter = new ArrayAdapter<Bug>(getActivity(), android.R.layout.simple_list_item_1, bugs);

            issuesview.setAdapter(adapter);

            issuesview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String issuetitle = (String) (issuesview.getItemAtPosition(position));
                    Bug bug = (Bug)parent.getAdapter().getItem(position);
                    Log.d("mymsg", "worked" + " " + bug.getId());

                    Intent intent = new Intent(getActivity(), BugCrudActivity.class);
                    intent.putExtra("action", "edit");
                    intent.putExtra("id", bug.getId());
                    startActivity(intent);
                }
            });
        }
        return rootview;
    }

}