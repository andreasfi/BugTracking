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
    private static final int DATABASE_VERSION = 1;
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
