package com.example.bugtracking.bugtracking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.object.Bug;

import java.util.List;


public class BugCurrentFragment extends Fragment{

    public BugCurrentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the activity
        //Context context = getActivity();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_bug_current, container, false);
        BugActivity activity= (BugActivity) getActivity();
        // Get db items Get project id
        BugDataSource ids = new BugDataSource(activity);
        List<Bug> bugs = ids.getAllIssueByProject(activity.getProjectid()); // get only current!!! need to change
        // Put values in layout
        if (!bugs.isEmpty()){
            ArrayAdapter<Bug> adapter = new ArrayAdapter<Bug>(getActivity(), android.R.layout.simple_list_item_1, bugs);
            ListView issuesview = (ListView) rootview.findViewById(R.id.listViewIssues);
            issuesview.setAdapter(adapter);
        }
        return rootview;
    }

}