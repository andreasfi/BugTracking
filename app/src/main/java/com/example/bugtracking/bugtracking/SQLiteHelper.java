package com.example.bugtracking.bugtracking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andreas on 07.11.2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    //Infos about database
    private static final String DATABASE_NAME = "bugtracking";
    private static final int DATABASE_VERSION = 4;
    private static SQLiteHelper instance;

    private SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public static SQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new SQLiteHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Bugtracking.DeveloperEntry.CREATE_TABLE_DEVELOPER);
        db.execSQL(Bugtracking.ProjectEntry.CREATE_TABLE_PROJECT);
        db.execSQL(Bugtracking.ProjectDeveloperEntry.CREATE_TABLE_PROJECTDEVELOPER);
        db.execSQL(Bugtracking.IssueEntry.CREATE_TABLE_ISSUE);
        db.execSQL(Bugtracking.DeveloperIssueEntry.CREATE_TABLE_DEVELOPERISSUE);
        db.execSQL(Bugtracking.CommentEntry.CREATE_TABLE_COMMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.DeveloperEntry.TABLE_DEVELOPER);
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.ProjectEntry.TABLE_PROJECT);
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.ProjectDeveloperEntry.TABLE_PROJECTDEVELOPER);
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.IssueEntry.TABLE_ISSUE);
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.DeveloperIssueEntry.TABLE_DEVELOPERISSUE);
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.CommentEntry.TABLE_COMMENT);
        db.execSQL("DROP TABLE IF EXISTS " + Bugtracking.CommentEntry.TABLE_COMMENT);

        // create new tables
        onCreate(db);
    }

}
