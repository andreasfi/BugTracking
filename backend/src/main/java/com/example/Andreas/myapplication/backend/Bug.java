package com.example.Andreas.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Sylvain on 07.11.2015.
 */
@Entity
public class Bug {
    @Id
    @GeneratedValue(strategy=	GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String reference;
    private String category;
    private String reproduce;
    private String effects;
    private String priority;
    private String state;
    private String date;
    private int projectId;
    private int devId;
    private List<Comment> commentList = new ArrayList<Comment>();

    public List<Comment> getCommentList() {
        return commentList;
    }
// test
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReproduce() {
        return reproduce;
    }

    public void setReproduce(String reproduce) {
        this.reproduce = reproduce;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.title;
    }
}
