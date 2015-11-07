package com.example.bugtracking.bugtracking.object;

/**
 * Created by Sylvain on 07.11.2015.
 */
public class DevIssue {
    private int id;
    private int devId;
    private int issId;

    public int getId() {
        return id;
    }


    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getIssId() {
        return issId;
    }

    public void setIssId(int issId) {
        this.issId = issId;
    }


}
