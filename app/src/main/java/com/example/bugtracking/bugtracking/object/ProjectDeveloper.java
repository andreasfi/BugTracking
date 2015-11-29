package com.example.bugtracking.bugtracking.object;

/**
 * Created by Andreas on 07.11.2015.
 */
public class ProjectDeveloper {
    private int id;
    private int devID;
    private int proID;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevID() {
        return devID;
    }

    public void setDevID(int devID) {
        this.devID = devID;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
