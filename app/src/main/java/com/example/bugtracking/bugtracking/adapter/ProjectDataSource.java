package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.Bug;
import com.example.bugtracking.bugtracking.object.Project;
import com.example.bugtracking.bugtracking.object.ProjectDeveloper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 07.11.2015.
 */
public class ProjectDataSource {
    private SQLiteDatabase db;
    private Context context;

    public ProjectDataSource(Context context) {
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    // INSERT PROJECT
    public long createProject(Project project){
        long id;
        ContentValues values = new ContentValues();
        values.put(Bugtracking.ProjectEntry.NAME, project.getName());
        values.put(Bugtracking.ProjectEntry.DESCRIPTION, project.getDescription());
        values.put(Bugtracking.ProjectEntry.STARTDATE, project.getStartdate());
        values.put(Bugtracking.ProjectEntry.ENDDATE, project.getEnddate());

        id = this.db.insert(Bugtracking.ProjectEntry.TABLE_PROJECT, null, values);

        return id;
    }

    // GET PROJECT
    public Project getProjectByID_Dev(long id){
        String sql = "SELECT * FROM "+ Bugtracking.ProjectEntry.TABLE_PROJECT +
                " WHERE " + Bugtracking.ProjectEntry.ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

                Project project = new Project();
                project.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.ProjectEntry.ID)));
                project.setName(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.NAME)));
                project.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.DESCRIPTION)));
                project.setStartdate(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.STARTDATE)));
                project.setEnddate(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.ENDDATE)));





        return project;
    }

    // GET PROJECT
    public Project getOneProjectByID(long id){
        String sql = "SELECT * FROM "+ Bugtracking.ProjectEntry.TABLE_PROJECT +
                " WHERE " + Bugtracking.ProjectEntry.ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Project project = new Project();
        project.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.ProjectEntry.ID)));
        project.setName(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.NAME)));
        project.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.DESCRIPTION)));
        project.setStartdate(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.STARTDATE)));
        project.setEnddate(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.ENDDATE)));


        return project;
    }

    // GET PROJECTS
    public List<Project> getAllProjects(){
        List<Project> projects = new ArrayList<Project>();
        String sql = "SELECT * FROM "+ Bugtracking.ProjectEntry.TABLE_PROJECT + " ORDER BY " + Bugtracking.ProjectEntry.NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Project project = new Project();
                project.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.ProjectEntry.ID)));
                project.setName(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.NAME)));
                project.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.DESCRIPTION)));
                project.setStartdate(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.STARTDATE)));
                project.setEnddate(cursor.getString(cursor.getColumnIndex(Bugtracking.ProjectEntry.ENDDATE)));

                projects.add(project);
            } while (cursor.moveToNext());
        }
        return projects;
    }

    // UPDATE PROJECT

    public int updateProject(Project project){
        ContentValues values = new ContentValues();

        values.put(Bugtracking.ProjectEntry.NAME, project.getName());
        values.put(Bugtracking.ProjectEntry.DESCRIPTION, project.getDescription());
        values.put(Bugtracking.ProjectEntry.STARTDATE, project.getStartdate());
        values.put(Bugtracking.ProjectEntry.ENDDATE, project.getEnddate());

        return this.db.update(Bugtracking.ProjectEntry.TABLE_PROJECT, values, Bugtracking.ProjectEntry.ID + " = ?", new String[]{String.valueOf(project.getId())});
    }

    public void deleteProject(long projectid){
        // Delete all Issues linked to projecct
        BugDataSource ids = new BugDataSource(context);
        List<Bug> bugs = ids.getAllIssueByProject(projectid);

        ProjectDeveloperDataSource pdds=new ProjectDeveloperDataSource(context);

        for (Bug bug : bugs){
            ids.deletEIssue(bug.getId());
        }
        pdds.deletProjectDeveloperIdProject(projectid);
        // Delete the project
        this.db.delete(Bugtracking.ProjectEntry.TABLE_PROJECT, Bugtracking.ProjectEntry.ID + " = ?", new String[] { String.valueOf(projectid)} );
    }
}
