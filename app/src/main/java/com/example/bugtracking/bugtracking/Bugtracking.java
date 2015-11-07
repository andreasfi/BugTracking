package com.example.bugtracking.bugtracking;

import android.provider.BaseColumns;

import com.example.bugtracking.bugtracking.object.Project;

/**
 * Created by Andreas on 07.11.2015.
 */
public final class Bugtracking {
    public Bugtracking() {
        // empty
    }

    public static abstract class Developer implements BaseColumns{
        // Table name
        public static final String TABLE_DEVELOPER = "developer";

        // Columns
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String LANGUAGE = "language";

        // Create statement
        public static final String CREATE_TABLE_DEVELOPER = "CREATE TABLE "
                + TABLE_DEVELOPER + "("
                + Developer.ID + " INTEGER PRIMARY KEY,"
                + Developer.USERNAME + " TEXT, "
                + Developer.PASSWORD + " TEXT, "
                + Developer.LANGUAGE + " TEXT "
                + ");";
    }
    public  static abstract class Project implements BaseColumns {
        // Table name
        public static final String TABLE_PROJECT = "project";

        // Columns
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String STARTDATE = "startdate";
        public static final String ENDDATE = "enddate";

        // Create statement
        public static final String CREATE_TABLE_PROJECT = "CREATE TABLE "
                + TABLE_PROJECT + "("
                + Project.ID + " INTEGER PRIMARY KEY,"
                + Project.NAME + " TEXT "
                + Project.DESCRIPTION + " TEXT "
                + Project.STARTDATE + " TEXT "
                + Project.ENDDATE + " TEXT "
                + ");";
    }
    public static abstract class ProjectDeveloper implements BaseColumns{
        // Table name
        public static final String TABLE_PROJECTDEVELOPER = "projectdeveloper";

        // Columns
        public static final String ID = "id";
        public static final String DEVID = "devid";
        public static final String PROID = "proid";
        public static final String ROLE = "role";

        // Create statement
        public static final String CREATE_TABLE_PROJECTDEVELOPER = "CREATE TABLE "
                + TABLE_PROJECTDEVELOPER + "("
                + ProjectDeveloper.ID + " INTEGER PRIMARY KEY,"
                + ProjectDeveloper.DEVID + " TEXT "
                + ProjectDeveloper.PROID + " TEXT "
                + ProjectDeveloper.ROLE + " TEXT "
                + ");";
    }

    public static abstract class Comment implements BaseColumns{
        //Table name
        public static final String TABLE_COMMENT="comments";

        //Columns
        public static final String ID= "id";
        public static final String COMMENT="comment";
        public static final String DEV_ID="devID";
        public static final String ISS_ID="issID";

        //Create statement
        public static final String CREATE_TABLE_COMMENT="CREATE TABLE "+
                TABLE_COMMENT+"("
                +Comment.ID+ "INTEGER PRIMARY KEY, "
                +Comment.COMMENT+"TEXT, "
                +Comment.DEV_ID+"INTEGER, "
                +Comment.ISS_ID+"INTEGER, "
                +");";
    }


    public static abstract class DevIss implements BaseColumns{

        public static final String TABLE_DEVISSUE="devIssue";

        //Columns
        public static final String ID="id";
        public static final String DEV_ID="devID";
        public static final String ISS_ID="issId";

        //Create statement
        public static final String CREATE_TABLE_DEVISSUE="CREATE TABLE "+TABLE_DEVISSUE+"("
                +DevIss.ID+"INTEGER PRIMARY KEY,"
                +DevIss.DEV_ID+"INTEGER, "
                +DevIss.ISS_ID+"INTEGER, "
                +"FOREIGN KEY ("+DEV_ID+") REFERENCES "+Developer.TABLE_DEVELOPER+" ("+ID+"), "
                +"FOREIGN KEY ("+ISS_ID+") REFERENCES "+Issue.TABLE_ISSUE+" ("+ID+"), "
                +");";
    }

    public static abstract class Issue implements BaseColumns{

        public static final String TABLE_ISSUE="issues";

        //Colums

        public static final String ID="id";
        public static final String TITLE="title";
        public static final String DESCRIPTION="description";
        public static final String REFERENCE="reference";
        public static final String CATEGORY="category";
        public static final String REPRODUCE="reproduce";
        public static final String EFFECT="effects";
        public static final String PRIORITY="priority";
        public static final String STATE="state";
        public static final String DATE="date";
        public static final String PROID="proId";
        public static final String DEVID="devId";


        //Create statements
        public static final String CREATE_TABLE_DEVISSUE="CREATE TABLE "+TABLE_ISSUE+"("
                +Issue.ID+"INTEGER PRIMARY KEY,"
                +Issue.TITLE+"TEXT,"
                +Issue.DESCRIPTION+"TEXT,"
                +Issue.REFERENCE+"TEXT,"
                +Issue.REFERENCE+"TEXT,"
                +Issue.CATEGORY+"TEXT,"
                +Issue.REPRODUCE+"TEXT,"
                +Issue.EFFECT+"TEXT,"
                +Issue.PRIORITY+"TEXT,"
                +Issue.STATE+"TEXT,"
                +Issue.DATE+"TEXT,"
                +Issue.PROID+"INTEGER,"
                +Issue.DEVID+"INTEGER,"
                +"FOREIGN KEY ("+PROID+") REFERENCES "+Project.TABLE_PROJECT+" ("+ID+"), "
                +"FOREIGN KEY ("+DEVID+") REFERENCES "+Developer.TABLE_DEVELOPER+" ("+ID+"), "
                +");";
    }

}
