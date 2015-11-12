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
        // Une fois que le helper est crée enlever le commentaire
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
        values.put(Bugtracking.Developer.USERNAME, developer.getUsername());
        values.put(Bugtracking.Developer.PASSWORD, developer.getPassword());
        values.put(Bugtracking.Developer.LANGUAGE, developer.getLang());

        id = this.db.insert(Bugtracking.Developer.TABLE_DEVELOPER, null, values);

        return id;
    }
    // FIND DEVELOPER BY ID
    public Developer getDeveloperByID(long id){
        String sql = "SELECT * FROM " + Bugtracking.Developer.TABLE_DEVELOPER +
                " WHERE " + Bugtracking.Developer.ID+ " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Developer developer = new Developer();
        developer.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.Developer.ID)));
        developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.Developer.USERNAME)));
        developer.setPassword(cursor.getColumnName(cursor.getColumnIndex(Bugtracking.Developer.PASSWORD)));
        developer.setLang(cursor.getColumnName(cursor.getColumnIndex(Bugtracking.Developer.LANGUAGE)));

        return developer;
    }
    // GET ALL DEVELOPERS
    public List<Developer> getAllDevelopers(){
        List<Developer> developers = new ArrayList<Developer>();
        String sql = "SELECT * FROM "+ Bugtracking.Developer.TABLE_DEVELOPER + " ORDER BY " + Bugtracking.Developer.USERNAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
                Developer developer = new Developer();
                developer.setId(cursor.getInt(cursor.getColumnIndex(Bugtracking.Developer.ID)));
                developer.setUsername(cursor.getString(cursor.getColumnIndex(Bugtracking.Developer.USERNAME)));
                developer.setPassword(cursor.getString(cursor.getColumnIndex(Bugtracking.Developer.PASSWORD)));
                developer.setLang(cursor.getString(cursor.getColumnIndex(Bugtracking.Developer.LANGUAGE)));

                developers.add(developer);
            } while (cursor.moveToNext());
        }
        return developers;
    }
    // UPDATE DEVELOPER
    public int updateDeveloper(Developer developer){
        ContentValues values = new ContentValues();
        values.put(Bugtracking.Developer.USERNAME, developer.getUsername());
        values.put(Bugtracking.Developer.PASSWORD, developer.getPassword());
        values.put(Bugtracking.Developer.LANGUAGE, developer.getLang());

        return this.db.update(Bugtracking.Developer.TABLE_DEVELOPER, values, Bugtracking.Developer.ID + " = ?", new String[]{String.valueOf(developer.getId())});

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
        this.db.delete(Bugtracking.Developer.TABLE_DEVELOPER, Bugtracking.ProjectDeveloper.ID + " = ?", new String[]{ String.valueOf(id)});
    }
}
