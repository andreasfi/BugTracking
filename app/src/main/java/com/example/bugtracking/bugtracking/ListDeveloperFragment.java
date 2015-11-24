package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.internal.widget.DialogTitle;

import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.example.bugtracking.bugtracking.object.Developer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylvain on 23.11.2015.
 */
public class ListDeveloperFragment extends DialogFragment {

   //Interface à implementer par l'Activity qui appelle cet DialogFragment
    public interface DeveloperDialogListener{
        public void onDialogPositiveClick(List<String> usernameList);
        public void onDialogNegativeClick();
    }

    DeveloperDialogListener projectActivity;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        ProjectCrudActivity project=new ProjectCrudActivity();
        List<Developer> developers=project.getListdeveloper();
        final String arrayUsername[]=transformToArray(developers);
        final List<String> developerSelected=new ArrayList<>();

        builder.setTitle("Developers");
        builder.setMultiChoiceItems(transformToArray(developers), null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //Chaque nom séléctioner est ajouté à la liste
                if (isChecked) {
                    developerSelected.add(arrayUsername[which]);
                } else if (developerSelected.contains(which)) {
                    developerSelected.remove(arrayUsername[which]);
                }
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                projectActivity.onDialogPositiveClick(developerSelected);
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                projectActivity.onDialogNegativeClick();
            }
        });
        return builder.create();
                //super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            //Reçois l'activité qui appelle le Dialog
            projectActivity=(DeveloperDialogListener) activity;
        }
        catch (ClassCastException e){
            throw  new ClassCastException(activity.toString()
                    +" must implement DeveloperDialogListener");
        }
    }

    public String[] transformToArray(List<Developer> developers){
        String developersArray[]=new String[developers.size()];

        for(int i=0;i<developersArray.length;i++){
            developersArray[i]=developers.get(i).getUsername();
        }

        return developersArray;
    }
}
