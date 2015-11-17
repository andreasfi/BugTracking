package com.example.bugtracking.bugtracking.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bugtracking.bugtracking.Bugtracking;
import com.example.bugtracking.bugtracking.SQLiteHelper;
import com.example.bugtracking.bugtracking.object.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylvain on 16.11.2015.
 */
public class CommentDataSource {
    private SQLiteDatabase db;
    private Context context;

    //Constructeur
    public CommentDataSource(Context context){
        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(context);
        db= sqLiteHelper.getWritableDatabase();
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

    //INSERT COMMENT
    public long addComment (Comment comment){
        long id;
        ContentValues values = new ContentValues();
        values.put(Bugtracking.CommentEntry.COMMENT, comment.getComment());
        values.put(Bugtracking.CommentEntry.DEV_ID, comment.getDevId());
        values.put(Bugtracking.CommentEntry.ISS_ID, comment.getIssueId());

        id = this.db.insert(Bugtracking.CommentEntry.TABLE_COMMENT, null, values);

        return id;
    }

    //GET COMMENT
    public List<Comment> getAllCommentByID(long id){
        //Je recherche par rapport à l'ID de l'Issue selectionnée
        List<Comment> comments=new ArrayList<>();
        String sql = "SELECT * FROM "+ Bugtracking.CommentEntry.TABLE_COMMENT +
                " WHERE "+ Bugtracking.CommentEntry.ISS_ID+ " = "+id;

        Cursor cursor = this.db.rawQuery(sql, null);

       /* if (cursor != null){
            cursor.moveToFirst();
        }*/

        if(cursor.moveToFirst()){
            do{
                Comment comment = new Comment();
                comment.setComment(cursor.getString(cursor.getColumnIndex(Bugtracking.CommentEntry.COMMENT)));

                comments.add(comment);
            }while (cursor.moveToNext());
        }

        return comments;
    }

    //DELETE COMMENT
    public void deleteComment(long id){
        //IL NE RECONNAIS PAS CERTAINE CLASSES
        /*RecordDataSource pra = new RecordDataSource(context);
        List<Record> records = pra.getAllRecordsByPerson(id);*/
        /*for (Record record : records){
            pra.deleteRecord(record.getId())
            }
         */
        this.db.delete(Bugtracking.CommentEntry.TABLE_COMMENT, Bugtracking.CommentEntry.ID+ " = ?", new String[] {String.valueOf(id)});
    }
}
