package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.Developer;
import com.example.bugtracking.bugtracking.object.ProjectDeveloper;

import java.util.ArrayList;
import java.util.List;

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

    public  long createProjectDeveloper(long developer_id, long project_id, String role){
        ContentValues values = new ContentValues();
        values.put(Bugtracking.ProjectDeveloperEntry.DEVID, developer_id );
        values.put(Bugtracking.ProjectDeveloperEntry.PROID, project_id);
        values.put(Bugtracking.ProjectDeveloperEntry.ROLE, role);

        return this.db.insert(Bugtracking.ProjectDeveloperEntry.TABLE_PROJECTDEVELOPER, null, values);
    }

    // GET ALL DEVELOPERS PROJECT
    //TODO Problème, les colonnes son sous format text et l'objet récupère des int + Voir pour les rôles.
    public List<ProjectDeveloper> getAllDeveloperspROJECT(){
        List<ProjectDeveloper> developersProject = new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.ProjectDeveloperEntry.TABLE_PROJECTDEVELOPER;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
                ProjectDeveloper developerProject = new ProjectDeveloper();
                developerProject.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.ProjectDeveloperEntry.ID)));
                developerProject.setDevID(cursor.getInt(cursor.getColumnIndex(Bugtracking.ProjectDeveloperEntry.DEVID)));
                developerProject.setProID(cursor.getInt(cursor.getColumnIndex(Bugtracking.ProjectDeveloperEntry.PROID)));
                developerProject.setRole(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectDeveloperEntry.ROLE)));

                developersProject.add(developerProject);
            } while (cursor.moveToNext());
        }
        return developersProject;
    }

}
