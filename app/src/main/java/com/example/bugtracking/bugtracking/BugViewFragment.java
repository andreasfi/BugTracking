package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.object.Bug;

/**
 * Created by Andreas on 26.10.2015.
 */
public class BugViewFragment extends Fragment {

    BugViewActivity activity;
    long bugId;
    public BugViewFragment() {
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
        View rootView= inflater.inflate(R.layout.fragment_bug_view, container, false);
        TextView title=(TextView)rootView.findViewById(R.id.bugViewTitle);
        TextView description=(TextView)rootView.findViewById(R.id.bugViewDescription);
        TextView reference=(TextView)rootView.findViewById(R.id.bugViewReference);
        TextView category=(TextView)rootView.findViewById(R.id.bugViewCategory);
        TextView reproduce=(TextView)rootView.findViewById(R.id.bugViewReproduce);
        TextView effect=(TextView)rootView.findViewById(R.id.bugViewEffects);
        TextView priority=(TextView)rootView.findViewById(R.id.bugViewPriority);
        TextView state=(TextView)rootView.findViewById(R.id.bugViewState);
        TextView date=(TextView)rootView.findViewById(R.id.bugViewDate);

        Button editBug=(Button)rootView.findViewById(R.id.bugViewEditbugButton);
        editBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, BugCrudActivity.class);
                intent.putExtra("action","edit");
                intent.putExtra("idBug", activity.getIdBug());
                intent.putExtra("idPro",activity.getIdProject());
                startActivity(intent);
            }
        });

         activity=(BugViewActivity)getActivity();
        bugId=activity.getIdBug();
        BugDataSource bds = new BugDataSource(activity);
        Bug bug=bds.getBugById(bugId);

        //add Data in the TextView
        title.setText(bug.getTitle());
        description.setText(bug.getDescription());
        reference.setText(bug.getReference());
        category.setText(bug.getCategory());
        reproduce.setText(bug.getReproduce());
        effect.setText(bug.getEffects());
        priority.setText(bug.getPriority());
        state.setText(bug.getState());
        date.setText(bug.getDate());

        return rootView;

    }


}
