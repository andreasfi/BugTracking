package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.Comment;
import com.example.bugtracking.bugtracking.object.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylvain on 17.11.2015.
 */
public class IssueDataSource {
    private SQLiteDatabase db;
    private Context context;

    public IssueDataSource(Context context){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db = sqLiteHelper.getWritableDatabase();
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

    //INSERT ISSUE
    public long createIssue(Issue issue){
        long id;
        ContentValues values = new ContentValues();
        values.put(Bugtracking.IssueEntry.TITLE, issue.getTitle());
        values.put(Bugtracking.IssueEntry.CATEGORY, issue.getCategory());
        values.put(Bugtracking.IssueEntry.DATE, issue.getDate());
        values.put(Bugtracking.IssueEntry.DESCRIPTION, issue.getDescription());
        values.put(Bugtracking.IssueEntry.DEVID, issue.getDevId());
        values.put(Bugtracking.IssueEntry.EFFECT, issue.getEffects());
        values.put(Bugtracking.IssueEntry.PRIORITY, issue.getPriority());
        values.put(Bugtracking.IssueEntry.PROID, issue.getProjectId());
        values.put(Bugtracking.IssueEntry.REFERENCE, issue.getReference());
        values.put(Bugtracking.IssueEntry.REPRODUCE, issue.getReproduce());
        values.put(Bugtracking.IssueEntry.STATE, issue.getState());

        id = this.db.insert(Bugtracking.IssueEntry.TABLE_ISSUE,null, values);

        return id;
    }
    // get issues by project
    public List<Issue> getAllIssueByProject(long projectid){
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM"+ Bugtracking.IssueEntry.TABLE_ISSUE +
                " WHERE"+ Bugtracking.IssueEntry.PROID+ " = "+ projectid;

        Cursor cursor = this.db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Issue issue= new Issue();
                issue.setDevId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.DEVID)));
                issue.setTitle(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.TITLE)));
                issue.setCategory(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.CATEGORY)));
                issue.setDate(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DATE)));
                issue.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DESCRIPTION)));
                issue.setEffects(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.EFFECT)));
                issue.setPriority(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.PRIORITY)));
                issue.setProjectId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.PROID)));
                issue.setReference(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REFERENCE)));
                issue.setReproduce(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REPRODUCE)));
                issue.setState(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.STATE)));
                issues.add(issue);
            }while(cursor.moveToNext());
        }
        return issues;
    }
    //GET ISSUE
    public List<Issue> getAllIssueByID(long id){

        List<Issue> issues=new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.IssueEntry.TABLE_ISSUE +
                " WHERE "+ Bugtracking.IssueEntry.ID+ " = "+id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Issue issue= new Issue();
                issue.setDevId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.DEVID)));
                issue.setTitle(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.TITLE)));
                issue.setCategory(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.CATEGORY)));
                issue.setDate(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DATE)));
                issue.setDescription(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.DESCRIPTION)));
                issue.setEffects(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.EFFECT)));
                issue.setPriority(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.PRIORITY)));
                issue.setProjectId(cursor.getInt(cursor.getColumnIndex(Bugtracking.IssueEntry.PROID)));
                issue.setReference(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REFERENCE)));
                issue.setReproduce(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.REPRODUCE)));
                issue.setState(cursor.getString(cursor.getColumnIndex(Bugtracking.IssueEntry.STATE)));

                issues.add(issue);
            }while(cursor.moveToNext());
        }

        return issues;
    }

    //UPDATE ISSUE
    public  int updateIssue(Issue issue){
        ContentValues values = new ContentValues();

        values.put(Bugtracking.IssueEntry.TITLE, issue.getTitle());
        values.put(Bugtracking.IssueEntry.CATEGORY, issue.getCategory());
        values.put(Bugtracking.IssueEntry.DATE, issue.getDate());
        values.put(Bugtracking.IssueEntry.DESCRIPTION, issue.getDescription());
        values.put(Bugtracking.IssueEntry.DEVID, issue.getDevId());
        values.put(Bugtracking.IssueEntry.EFFECT, issue.getEffects());
        values.put(Bugtracking.IssueEntry.PRIORITY, issue.getPriority());
        values.put(Bugtracking.IssueEntry.PROID, issue.getProjectId());
        values.put(Bugtracking.IssueEntry.REFERENCE, issue.getReference());
        values.put(Bugtracking.IssueEntry.REPRODUCE, issue.getReproduce());
        values.put(Bugtracking.IssueEntry.STATE, issue.getState());

        return this.db.update(Bugtracking.IssueEntry.TABLE_ISSUE, values,Bugtracking.IssueEntry.ID+ " = ?", new String[]{String.valueOf(issue.getId())});
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
