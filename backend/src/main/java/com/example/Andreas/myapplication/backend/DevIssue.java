package com.example.Andreas.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Sylvain on 07.11.2015.
 */
@Entity
public class DevIssue {

    @Id
    @GeneratedValue(strategy=	GenerationType.IDENTITY)
    private long id;
    private long devId;
    private long issId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getDevId() {
        return devId;
    }

    public void setDevId(long devId) {
        this.devId = devId;
    }

    public long getIssId() {
        return issId;
    }

    public void setIssId(long issId) {
        this.issId = issId;
    }


}
