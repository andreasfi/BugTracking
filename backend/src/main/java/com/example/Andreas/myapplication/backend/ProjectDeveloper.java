package com.example.Andreas.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Andreas on 07.11.2015.
 */
@Entity
public class ProjectDeveloper {
    @Id
    @GeneratedValue(strategy=	GenerationType.IDENTITY)
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
