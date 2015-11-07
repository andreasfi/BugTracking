package com.example.bugtracking.bugtracking.object;

import java.text.SimpleDateFormat;

/**
 * Created by Andreas on 07.11.2015.
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private SimpleDateFormat startdate;
    private SimpleDateFormat enddate;
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
    public SimpleDateFormat getStartdate() {
        return startdate;
    }
    public void setStartdate(SimpleDateFormat startdate) {
        this.startdate = startdate;
    }
    public SimpleDateFormat getEnddate() {
        return enddate;
    }
    public void setEnddate(SimpleDateFormat enddate) {
        this.enddate = enddate;
    }
}
