package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;

/**
 * Created by Andreas on 07.11.2015.
 */
public class ProjectDeveloperDataSource {
    private SQLiteDatabase db;
    private Context context;

    public ProjectDeveloperDataSource(Context context) {
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }
    public Context getContext() {
        return context;
    }

    public  long createProjectDeveloper(long developer_id, long project_id){
        ContentValues values = new ContentValues();
        values.put(Bugtracking.ProjectDeveloperEntry.DEVID, developer_id );
        values.put(Bugtracking.ProjectDeveloperEntry.PROID, project_id);

        return this.db.insert(Bugtracking.ProjectDeveloperEntry.TABLE_PROJECTDEVELOPER, null, values);
    }

}
