package com.example.bugtracking.bugtracking;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.object.Bug;

import java.util.List;


public class BugSolvedFragment extends Fragment{
    long projectidSolved;
    View rootviewSolved;
    ArrayAdapter<Bug> adapterSolved;
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
        rootviewSolved = inflater.inflate(R.layout.fragment_bug_solved, container, false);
        final ListView issuesviewSolved = (ListView) rootviewSolved.findViewById(R.id.listViewIssuesSolved);

        final BugActivity activity = (BugActivity) getActivity();

        projectidSolved = activity.getProjectid();
        // Get db items Get project id
        BugDataSource ids = new BugDataSource(activity);
        List<Bug> bugs = ids.getAllIssueByProjectState(projectidSolved, "Solved"); // get only !!! need to change

        // Put values in layout
        if (!bugs.isEmpty()){
            adapterSolved = new ArrayAdapter<Bug>(getActivity(), android.R.layout.simple_list_item_1, bugs);

            issuesviewSolved.setAdapter(adapterSolved);

            issuesviewSolved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String issuetitle = (String) (issuesview.getItemAtPosition(position));
                    Bug bug = (Bug) parent.getAdapter().getItem(position);

                    Intent intent = new Intent(getActivity(), BugViewActivity.class);
                    intent.putExtra("BugId", (long) bug.getId());
                    intent.putExtra("idpro", projectidSolved);
                    startActivity(intent);
                }
            });
            registerForContextMenu(issuesviewSolved);
        }
        return rootviewSolved;
    }
    public ArrayAdapter<Bug> getAdapterSolved() {
        return adapterSolved;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listViewIssuesSolved) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;


            Adapter adapter = getAdapterSolved();
            Bug bug = (Bug)adapter.getItem(acmi.position);

            menu.setHeaderTitle(bug.getTitle());
            menu.add("Edit");
            menu.add("Delete");
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if( getUserVisibleHint() == false )
        {
            return false;
        }

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Bug bug = getAdapterSolved().getItem(info.position);

        int bugid = info.position;

        ListView lv = (ListView) rootviewSolved.findViewById(R.id.listViewIssuesSolved);

        switch ((String)item.getTitle()){
            case "Edit":
                Intent intent = new Intent(getActivity(), BugCrudActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("id", (long) bug.getId());
                intent.putExtra("idPro", (long)bug.getProjectId());
                startActivity(intent);
                break;
            case "Delete":
                BugActivity activity = (BugActivity) getActivity();
                BugDataSource bds = new BugDataSource(activity);
                bds.deletEIssue(bug.getId());

                // DElete all comments

                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(activity);
                sqlHelper.getWritableDatabase().close();

                Intent intent2 = new Intent(activity, BugActivity.class);
                intent2.putExtra("idProject", (long) bug.getProjectId());
                startActivity(intent2);
                break;

        }

        return true;
    }
}