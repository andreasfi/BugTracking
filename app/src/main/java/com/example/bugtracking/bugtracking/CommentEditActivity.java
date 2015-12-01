package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.bugtracking.bugtracking.adapter.CommentDataSource;
import com.example.bugtracking.bugtracking.object.Comment;

public class CommentEditActivity extends BaseActivity {

    private EditText title;
    private EditText commentText;
    private long idBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent=getIntent();
        idBug=intent.getLongExtra("BugId", 1L);
    }


    public void clicAddComment(View view){

        title=(EditText)findViewById(R.id.commentTitleText);
        commentText=(EditText)findViewById(R.id.commentText);

        Comment comment=new Comment();
        comment.setTitle(title.getText().toString());
        comment.setComment(commentText.getText().toString());
        comment.setDevId(1);
        comment.setIssueId(1);
        CommentDataSource cds=new CommentDataSource(this);
        cds.addComment(comment);

        Log.d("Comment", "worked" + " " + comment.getTitle() + " " + comment.getComment());

        SQLiteHelper sqlHelper=SQLiteHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();

        Intent intent=new Intent(CommentEditActivity.this, BugViewActivity.class);
        intent.putExtra("BugId", idBug);
        startActivity(intent);

    }
}
