package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.object.Bug;
import com.example.bugtracking.bugtracking.object.Developer;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;


public class BugCurrentFragment extends Fragment{
    long projectid;
    View rootview;
    ArrayAdapter<Bug> adapter;
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
        rootview = inflater.inflate(R.layout.fragment_bug_current, container, false);
        final ListView issuesview = (ListView) rootview.findViewById(R.id.listViewIssues);

        final BugActivity activity = (BugActivity) getActivity();

        projectid = activity.getProjectid();
        Log.d("test", projectid+"");

        // Get db items Get project id
        BugDataSource ids = new BugDataSource(activity);
        List<Bug> bugs = ids.getAllIssueByProjectState(projectid, "Current"); // get only current!!! need to change



        // Put values in layout
        if (!bugs.isEmpty()){

            adapter = new ArrayAdapter<Bug>(getActivity(), android.R.layout.simple_list_item_1, bugs);

            issuesview.setAdapter(adapter);

            issuesview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String issuetitle = (String) (issuesview.getItemAtPosition(position));
                    Bug bug = (Bug) parent.getAdapter().getItem(position);
                    //Log.d("mymsg", "worked" + " " + bug.getId()+" "+bug.getTitle());

                    Intent intent = new Intent(getActivity(), BugViewActivity.class);
                    intent.putExtra("action", "edit");
                    intent.putExtra("id", (long) bug.getId());
                    intent.putExtra("idpro", projectid);
                    startActivity(intent);
                }
            });
            registerForContextMenu(issuesview);

        }
        return rootview;
    }

    public ArrayAdapter<Bug> getAdapter() {
        return adapter;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listViewIssues) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;


            Adapter adapter = getAdapter();
            Bug bug = (Bug)adapter.getItem(acmi.position);
            Log.d("aldskfjalösdkfj",bug.getTitle());

            menu.setHeaderTitle(bug.getTitle());
            menu.add("Edit");
            menu.add("Delete");
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Bug bug = getAdapter().getItem(info.position);
        Log.d("img it workeeeeees", bug.getTitle());


        int bugid = info.position;
        Log.d("bugid", "and "+bugid);

        ListView lv = (ListView) rootview.findViewById(R.id.listViewIssues);
        //Log.d("help", "help me here2 " + lv.getSelectedItem() + " si " +item.getItemId() + " o " + item.getTitle());

        // item.getTitle()
        switch ((String)item.getTitle()){
            case "Edit":
                Intent intent = new Intent(getActivity(), BugCrudActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("id", (long) bug.getId());
                intent.putExtra("idpro", (long)bug.getProjectId());
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
                startActivity(intent2);
                break;

        }

        return true;
    }

    public String[] transformToArray(List<Bug> bugs){
        String developersArray[]=new String[bugs.size()];

        for(int i=0;i<developersArray.length;i++){
            developersArray[i]=bugs.get(i).getTitle();
        }

        return developersArray;
    }

}