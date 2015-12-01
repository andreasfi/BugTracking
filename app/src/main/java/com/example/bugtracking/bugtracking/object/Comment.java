package com.example.bugtracking.bugtracking.object;

/**
 * Created by Sylvain on 07.11.2015.
 */
public class Comment {
    private int id;
    private String title;
    private String comment;
    private int devId;
    private int issueId;

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
