package com.example.bugtracking.bugtracking;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Sylvain on 09.11.2015.
 */

//Class pour afficher un calendrier
public class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView text;

    //Création du calendrier
    @Override
    public Dialog onCreateDialog(Bundle saveInstancestate){
        Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, year,month,day);
    }

    //Affichage de la date séléctionnée
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        text.setText(""+dayOfMonth+" "+monthOfYear+" "+ year);

    }

    //Pour passer l'Edit Text qui contiendra une date
    public void setView(TextView text){
        this.text=text;
    }

}
