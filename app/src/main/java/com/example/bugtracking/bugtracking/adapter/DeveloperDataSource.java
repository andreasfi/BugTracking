package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.Developer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 07.11.2015.
 */
public class DeveloperDataSource {
    private SQLiteDatabase db;
    private Context context;

    public DeveloperDataSource(Context context) {
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    // INSERT DEVELOPER
    public long createDeveloper(Developer developer){
        long id;
        ContentValues values = new ContentValues();
        values.put(Bugtracking.DeveloperEntry.USERNAME, developer.getUsername());
        values.put(Bugtracking.DeveloperEntry.PASSWORD, developer.getPassword());
        values.put(Bugtracking.DeveloperEntry.LANGUAGE, developer.getLang());

        id = this.db.insert(Bugtracking.DeveloperEntry.TABLE_DEVELOPER, null, values);

        return id;
    }
    // FIND DEVELOPER BY IDDEVLOPER
    public Developer getDeveloperByID(long id){
        String sql = "SELECT * FROM " + Bugtracking.DeveloperEntry.TABLE_DEVELOPER +
                " WHERE " + Bugtracking.DeveloperEntry.IDDEVLOPER + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Developer developer = new Developer();
        developer.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.DeveloperEntry.IDDEVLOPER)));
        developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.USERNAME)));
        developer.setPassword(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.PASSWORD)));
        developer.setLang(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.LANGUAGE)));

        return developer;
    }
    public com.example.andreas.myapplication.backend.developerApi.model.Developer getDeveloperByIDBackend(long id){
        String sql = "SELECT * FROM " + Bugtracking.DeveloperEntry.TABLE_DEVELOPER +
                " WHERE " + Bugtracking.DeveloperEntry.IDDEVLOPER + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        com.example.andreas.myapplication.backend.developerApi.model.Developer developer = new com.example.andreas.myapplication.backend.developerApi.model.Developer();
        developer.setId(Long.valueOf(cursor.getInt(cursor.getColumnIndex(Bugtracking.DeveloperEntry.IDDEVLOPER))));
        developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.USERNAME)));
        developer.setPassword(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.PASSWORD)));
        developer.setLang(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.LANGUAGE)));

        return developer;
    }

    // FIND DEVELOPER BY Username
    public Developer getDeveloperByUsername(String username){
        String sql = "SELECT * FROM " + Bugtracking.DeveloperEntry.TABLE_DEVELOPER +
                " WHERE " + Bugtracking.DeveloperEntry.USERNAME+ " = '" +username+"'";

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Developer developer = new Developer();
        developer.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.DeveloperEntry.IDDEVLOPER)));
        developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.USERNAME)));
        developer.setPassword(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.PASSWORD)));
        developer.setLang(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.LANGUAGE)));

        return developer;
    }
    // GET ALL DEVELOPERS
    public List<Developer> getAllNotUpdated(){
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.DeveloperEntry.TABLE_DEVELOPER +
                " WHERE "+ Bugtracking.DeveloperEntry.UPDATED + " = 'false' ORDER BY " + Bugtracking.DeveloperEntry.USERNAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
                Developer developer = new Developer();
                developer.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.DeveloperEntry.IDDEVLOPER)));
                developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.USERNAME)));
                developer.setPassword(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.PASSWORD)));
                developer.setLang(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.LANGUAGE)));

                developers.add(developer);
                updateNotUpdated(developer);
            } while (cursor.moveToNext());
        }
        return developers;
    }
    public int updateNotUpdated(Developer developer){
        ContentValues values = new ContentValues();
        values.put(Bugtracking.DeveloperEntry.USERNAME, developer.getUsername());
        values.put(Bugtracking.DeveloperEntry.PASSWORD, developer.getPassword());
        values.put(Bugtracking.DeveloperEntry.LANGUAGE, developer.getLang());
        values.put(Bugtracking.DeveloperEntry.UPDATED, true);

        return this.db.update(Bugtracking.DeveloperEntry.TABLE_DEVELOPER, values, Bugtracking.DeveloperEntry.IDDEVLOPER + " = ?", new String[]{String.valueOf(developer.getId())});

    }
    public List<Developer> getAllDevelopers(){
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.DeveloperEntry.TABLE_DEVELOPER + " ORDER BY " + Bugtracking.DeveloperEntry.USERNAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
                Developer developer = new Developer();
                developer.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.DeveloperEntry.IDDEVLOPER)));
                developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.USERNAME)));
                developer.setPassword(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.PASSWORD)));
                developer.setLang(cursor.getString(cursor.getColumnIndex(Bugtracking.DeveloperEntry.LANGUAGE)));

                developers.add(developer);
            } while (cursor.moveToNext());
        }
        return developers;
    }
    // UPDATE DEVELOPER
    public int updateDeveloper(Developer developer){
        ContentValues values = new ContentValues();
        values.put(Bugtracking.DeveloperEntry.USERNAME, developer.getUsername());
        values.put(Bugtracking.DeveloperEntry.PASSWORD, developer.getPassword());
        values.put(Bugtracking.DeveloperEntry.LANGUAGE, developer.getLang());

        return this.db.update(Bugtracking.DeveloperEntry.TABLE_DEVELOPER, values, Bugtracking.DeveloperEntry.IDDEVLOPER + " = ?", new String[]{String.valueOf(developer.getId())});

    }
    // DELETE DEVELOPER
    public void deleteDeveloper(long id){

        //ProjectDeveloperDataSource pdds = new ProjectDeveloperDataSource(context);
        // List<Record> records = pra.getAllRecordsByPerson(id);
        /*
        for(Record record : records){
			pra.deleteRecord(record.getId());
		}
         */
        this.db.delete(Bugtracking.DeveloperEntry.TABLE_DEVELOPER, Bugtracking.ProjectDeveloperEntry.ID + " = ?", new String[]{ String.valueOf(id)});
    }
}
