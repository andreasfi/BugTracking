package com.example.bugtracking.bugtracking;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bugtracking.bugtracking.adapter.CommentDataSource;
import com.example.bugtracking.bugtracking.object.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CommentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =inflater.inflate(R.layout.fragment_comment, container, false);

        final ListView commentView=(ListView) rootview.findViewById(R.id.listViewComment);

        final BugViewActivity activity=(BugViewActivity) getActivity();
        //Get comment from db
        CommentDataSource cds=new CommentDataSource(activity);
        List<Comment> comments = cds.getAllCommentByProjectID(activity.getIdBug());
        List<String> title=new ArrayList<>();

        for(int i=0;i<comments.size();i++){
            title.add(comments.get(i).getTitle());
        }


        //Put value in layout
        if(!comments.isEmpty()){
            ArrayAdapter<Comment> adapter;
            adapter = new ArrayAdapter<Comment>(getActivity(), android.R.layout.simple_list_item_1, comments);
            Log.d("Reception", "commentaire" + " " + comments.get(comments.size()-1).getTitle() + " " + comments.get(comments.size()-1).getComment());

            commentView.setAdapter(adapter);
            commentView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                //Display de comment in a AlertDialog
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Comment comment =(Comment)parent.getItemAtPosition(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage(comment.getComment())
                            .setTitle(comment.getTitle())
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        FloatingActionButton fab=(FloatingActionButton) rootview.findViewById(R.id.fabAddComment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, CommentEditActivity.class);
                intent.putExtra("BugId",activity.getIdBug());
                startActivity(intent);
            }
        });

        return rootview;
    }

}
