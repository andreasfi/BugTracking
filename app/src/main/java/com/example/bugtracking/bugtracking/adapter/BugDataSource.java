package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.Bug;
import com.example.bugtracking.bugtracking.object.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylvain on 17.11.2015.
 */
public class BugDataSource {
    private SQLiteDatabase db;
    private Context context;

    public BugDataSource(Context context){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

    //INSERT ISSUE
    public long createIssue(Bug bug){
        long id;
        ContentValues values = new ContentValues();
        values.put(Bugtracking.IssueEntry.TITLE, bug.getTitle());
        values.put(Bugtracking.IssueEntry.CATEGORY, bug.getCategory());
        values.put(Bugtracking.IssueEntry.DATE, bug.getDate());
        values.put(Bugtracking.IssueEntry.DESCRIPTION, bug.getDescription());
        values.put(Bugtracking.IssueEntry.DEVID, bug.getDevId());
        values.put(Bugtracking.IssueEntry.EFFECT, bug.getEffects());
        values.put(Bugtracking.IssueEntry.PRIORITY, bug.getPriority());
        values.put(Bugtracking.IssueEntry.PROID, bug.getProjectId());
        values.put(Bugtracking.IssueEntry.REFERENCE, bug.getReference());
        values.put(Bugtracking.IssueEntry.REPRODUCE, bug.getReproduce());
        values.put(Bugtracking.IssueEntry.STATE, bug.getState());

        id = this.db.insert(Bugtracking.IssueEntry.TABLE_ISSUE,null, values);

        return id;
    }
    // get issues by project
    public List<Bug> getAllIssueByProject(long projectid){
        List<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.IssueEntry.TABLE_ISSUE +
                " WHERE "+ Bugtracking.IssueEntry.PROID+ " = "+ projectid;

        Cursor cursor = this.db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Bug bug = new Bug();
                bug.setDevId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.DEVID)));
                bug.setTitle(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.TITLE)));
                bug.setCategory(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.CATEGORY)));
                bug.setDate(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DATE)));
                bug.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DESCRIPTION)));
                bug.setEffects(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.EFFECT)));
                bug.setPriority(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.PRIORITY)));
                bug.setProjectId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.PROID)));
                bug.setReference(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REFERENCE)));
                bug.setReproduce(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REPRODUCE)));
                bug.setState(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.STATE)));
                bugs.add(bug);
            }while(cursor.moveToNext());
        }
        return bugs;
    }
    //GET ISSUE
    public List<Bug> getAllIssueByID(long id){

        List<Bug> bugs =new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.IssueEntry.TABLE_ISSUE +
                " WHERE "+ Bugtracking.IssueEntry.ID+ " = "+id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Bug bug = new Bug();
                bug.setDevId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.DEVID)));
                bug.setTitle(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.TITLE)));
                bug.setCategory(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.CATEGORY)));
                bug.setDate(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DATE)));
                bug.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DESCRIPTION)));
                bug.setEffects(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.EFFECT)));
                bug.setPriority(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.PRIORITY)));
                bug.setProjectId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.PROID)));
                bug.setReference(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REFERENCE)));
                bug.setReproduce(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REPRODUCE)));
                bug.setState(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.STATE)));

                bugs.add(bug);
            }while(cursor.moveToNext());
        }

        return bugs;
    }

    //UPDATE ISSUE
    public  int updateIssue(Bug bug){
        ContentValues values = new ContentValues();

        values.put(Bugtracking.IssueEntry.TITLE, bug.getTitle());
        values.put(Bugtracking.IssueEntry.CATEGORY, bug.getCategory());
        values.put(Bugtracking.IssueEntry.DATE, bug.getDate());
        values.put(Bugtracking.IssueEntry.DESCRIPTION, bug.getDescription());
        values.put(Bugtracking.IssueEntry.DEVID, bug.getDevId());
        values.put(Bugtracking.IssueEntry.EFFECT, bug.getEffects());
        values.put(Bugtracking.IssueEntry.PRIORITY, bug.getPriority());
        values.put(Bugtracking.IssueEntry.PROID, bug.getProjectId());
        values.put(Bugtracking.IssueEntry.REFERENCE, bug.getReference());
        values.put(Bugtracking.IssueEntry.REPRODUCE, bug.getReproduce());
        values.put(Bugtracking.IssueEntry.STATE, bug.getState());

        return this.db.update(Bugtracking.IssueEntry.TABLE_ISSUE, values,Bugtracking.IssueEntry.ID+ " = ?", new String[]{String.valueOf(bug.getId())});
    }

    //DELETE ISSUE
    public void deletEIssue(long id){
        CommentDataSource cds = new CommentDataSource(context);
        List<Comment> comments = cds.getAllCommentByID(id);
        for (Comment comment: comments){
            cds.deleteComment(comment.getId());
        }

        this.db.delete(Bugtracking.IssueEntry.TABLE_ISSUE, Bugtracking.CommentEntry.ID+ " = ?", new String[] {String.valueOf(id)});
    }

}
