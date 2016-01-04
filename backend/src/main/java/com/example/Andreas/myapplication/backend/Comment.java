package com.example.Andreas.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Sylvain on 07.11.2015.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy=	GenerationType.IDENTITY)
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
