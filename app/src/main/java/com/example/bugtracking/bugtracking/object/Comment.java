package com.example.bugtracking.bugtracking.object;

/**
 * Created by Sylvain on 07.11.2015.
 */
public class Comment {
    private long id;
    private String title;
    private String comment;
    private long devId;
    private long issueId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public long getDevId() {
        return devId;
    }

    public void setDevId(long devId) {
        this.devId = devId;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
