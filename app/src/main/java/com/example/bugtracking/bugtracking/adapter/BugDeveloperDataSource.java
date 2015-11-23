package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.DevIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylvain on 17.11.2015.
 */
public class BugDeveloperDataSource {
    private SQLiteDatabase db;
    private Context context;

    public BugDeveloperDataSource(Context context){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context = context;
    }
    public Context getContext() { return context;}

    public long createIssueDeveloper(long developer_id, long issue_id){
        ContentValues values=new ContentValues();
        values.put(Bugtracking.DeveloperIssueEntry.DEV_ID, developer_id);
        values.put (Bugtracking.DeveloperIssueEntry.ISS_ID, issue_id);


        return this.db.insert(Bugtracking.DeveloperIssueEntry.TABLE_DEVELOPERISSUE, null, values);
    }

    //GET DEVELOPER(S) ISSUE
    public List<DevIssue> getAllDeveloperIssue(long id){

        List<DevIssue> idDevelopers = new ArrayList<>();
        //TODO Peut être qu'il faut selectionner que la colonne developer... à voir, à developer
        String sql = "SELECT * FROM "+ Bugtracking.DeveloperIssueEntry.TABLE_DEVELOPERISSUE +
                " WHERE "+Bugtracking.DeveloperIssueEntry.ISS_ID +" = "+id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                DevIssue devIssue = new DevIssue();
                devIssue.setDevId(cursor.getInt(cursor.getColumnIndex(Bugtracking.DeveloperIssueEntry.DEV_ID)));

                idDevelopers.add(devIssue);
            }while (cursor.moveToNext());
        }
        return idDevelopers;
    }
}
