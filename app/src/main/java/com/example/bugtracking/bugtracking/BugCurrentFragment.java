package com.example.bugtracking.bugtracking;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugtracking.bugtracking.adapter.IssueDataSource;
import com.example.bugtracking.bugtracking.object.Issue;

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
        BugActivity activity= (BugActivity) getActivity();

        // Get db items
        IssueDataSource ids = new IssueDataSource(activity);
        List<Issue> issues = ids.getAllIssueByProject(activity.getProjectid());

        // Put values in layout


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bug_current, container, false);
    }

}