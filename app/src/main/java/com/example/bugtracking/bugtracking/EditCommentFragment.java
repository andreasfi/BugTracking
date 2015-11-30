package com.example.bugtracking.bugtracking;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Sylvain on 30.11.2015.
 */
public class EditCommentFragment extends DialogFragment {

    public interface EditCommentDialogListener{
        void onFinishEdithDialog(String inputText);
    }

    private EditText editComment;

    //Constructor
    public EditCommentFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_comment_layout, container);
        editComment=(EditText) view.findViewById(R.id.addComment);


        return view;
    }

    public void AddComment(View view){

        EditCommentDialogListener activity = (EditCommentDialogListener) getActivity();
        activity.onFinishEdithDialog(editComment.getText().toString());
        this.dismiss();
    }




}
