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


public class BugCurrentFragment extends Fragment{
    long projectidCurrent;
    View rootviewCurrent;
    ArrayAdapter<Bug> adapterCurrent;
    public BugCurrentFragment() {
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
        rootviewCurrent = inflater.inflate(R.layout.fragment_bug_current, container, false);
        // Get the list
        final ListView issuesviewCurrent = (ListView) rootviewCurrent.findViewById(R.id.listViewIssues);

        final BugActivity activity = (BugActivity) getActivity();

        // Get the project id
        projectidCurrent = activity.getProjectid();
        // Get db items Get project id
        BugDataSource ids = new BugDataSource(activity);
        List<Bug> bugs = ids.getAllIssueByProjectState(projectidCurrent, "Current"); // Gets ** bugs

        // Put values in layout
        if (!bugs.isEmpty()){
            adapterCurrent = new ArrayAdapter<Bug>(getActivity(), android.R.layout.simple_list_item_1, bugs);

            issuesviewCurrent.setAdapter(adapterCurrent);

            issuesviewCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String issuetitle = (String) (issuesview.getItemAtPosition(position));
                    Bug bug = (Bug) parent.getAdapter().getItem(position);

                    Intent intent = new Intent(getActivity(), BugViewActivity.class);
                    intent.putExtra("BugId", (long) bug.getId());
                    intent.putExtra("idpro", projectidCurrent);
                    startActivity(intent);
                }
            });
            registerForContextMenu(issuesviewCurrent);
        }
        return rootviewCurrent;
    }
    public ArrayAdapter<Bug> getAdapterCurrent() {
        return adapterCurrent;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listViewIssues) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;


            Adapter adapter = getAdapterCurrent();
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
        Bug bugCurrent = getAdapterCurrent().getItem(info.position);

        int bugid = info.position;

        ListView lv = (ListView) rootviewCurrent.findViewById(R.id.listViewIssues);

        switch ((String)item.getTitle()){
            case "Edit":
                Intent intent = new Intent(getActivity(), BugCrudActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("id", (long) bugCurrent.getId());
                intent.putExtra("idpro", (long) bugCurrent.getProjectId());
                startActivity(intent);
                break;
            case "Delete":
                BugActivity activity = (BugActivity) getActivity();
                BugDataSource bds = new BugDataSource(activity);
                bds.deletEIssue(bugCurrent.getId());

                // DElete all comments

                SQLiteHelper sqlHelper = SQLiteHelper.getInstance(activity);
                sqlHelper.getWritableDatabase().close();

                Intent intent2 = new Intent(activity, BugActivity.class);
                startActivity(intent2);
                break;

        }

        return true;
    }


}