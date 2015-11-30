package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bugtracking.bugtracking.adapter.CommentDataSource;
import com.example.bugtracking.bugtracking.object.Comment;

import java.util.List;


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
        List<Comment> comments = cds.getAllComment();

        //Put value in layout
        if(!comments.isEmpty()){
            ArrayAdapter<Comment> adapter;
            adapter = new ArrayAdapter<Comment>(getActivity(), android.R.layout.simple_list_item_1, comments);

            commentView.setAdapter(adapter);
           /* commentView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });*/
        }


        return rootview;
    }

    //Methode appelée dans le Fragment "CommentEntry"
    public void clicComment(View view){
        CommentToast toast=new CommentToast();
        toast.runToast("MON COMMENTAIRE");

    }


    class CommentToast extends AppCompatActivity{

        public void runToast(String comment){
            LayoutInflater inflater=getLayoutInflater();
            View layout =inflater.inflate(R.layout.toast_comment_layout,
                    (ViewGroup) findViewById(R.id.toastComment_layout));

            TextView comment2=(TextView) findViewById(R.id.commentInToast);
            comment2.setText(comment);

            Toast toast=new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }


}
