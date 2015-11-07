package com.example.bugtracking.bugtracking.object;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;

/**
 * Created by Andreas on 07.11.2015.
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private String startdate;
    private String enddate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStartdate() {
        return startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
