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
}
