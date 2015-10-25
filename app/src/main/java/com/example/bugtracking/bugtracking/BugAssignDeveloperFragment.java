package com.example.bugtracking.bugtracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Andreas on 25.10.2015.
 */
public class BugAssignDeveloperFragment extends DialogFragment {
    public static final String DATA = "items";
    public static final String SELECTED = "selected";
    private SelectionListener listener;

    public BugAssignDeveloperFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            this.listener = (SelectionListener)activity;
        }
        catch ( ClassCastException oops )
        {
            oops.printStackTrace();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("Please Select");
        dialog.setPositiveButton("Cancel", new PositiveButtonClickListener());

        List<String> list = (List<String>)bundle.get(DATA);
        int position = bundle.getInt(SELECTED);

        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        dialog.setSingleChoiceItems(cs, position, selectItemListener);

        return dialog.create();
    }
    class PositiveButtonClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            dialog.dismiss();
        }
    }

    DialogInterface.OnClickListener selectItemListener = new DialogInterface.OnClickListener()
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            // process
            //which means position
            dialog.dismiss();
        }

    };
    public interface SelectionListener
    {
        public void selectItem ( int position );
    }
}
