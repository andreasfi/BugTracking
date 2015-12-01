package com.example.bugtracking.bugtracking.object;

/**
 * Created by Sylvain on 07.11.2015.
 */
public class DevIssue {


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
