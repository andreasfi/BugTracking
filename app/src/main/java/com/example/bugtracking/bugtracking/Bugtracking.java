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

    public static abstract class DeveloperEntry implements BaseColumns{
        // Table name
        public static final String TABLE_DEVELOPER = "developer";

        // Columns
        public static final String ID = "iddeveloper";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String LANGUAGE = "language";

        // Create statement
        public static final String CREATE_TABLE_DEVELOPER = "CREATE TABLE "
                + TABLE_DEVELOPER + "("
                + DeveloperEntry.ID + " INTEGER PRIMARY KEY,"
                + DeveloperEntry.USERNAME + " TEXT, "
                + DeveloperEntry.PASSWORD + " TEXT, "
                + DeveloperEntry.LANGUAGE + " TEXT "
                + ");";
    }
    public  static abstract class ProjectEntry implements BaseColumns {
        // Table name
        public static final String TABLE_PROJECT = "project";

        // Columns
        public static final String ID = "idproject";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String STARTDATE = "startdate";
        public static final String ENDDATE = "enddate";

        // Create statement
        public static final String CREATE_TABLE_PROJECT = "CREATE TABLE "
                + TABLE_PROJECT + "("
                + ProjectEntry.ID + " INTEGER PRIMARY KEY,"
                + ProjectEntry.NAME + " TEXT, "
                + ProjectEntry.DESCRIPTION + " TEXT, "
                + ProjectEntry.STARTDATE + " TEXT, "
                + ProjectEntry.ENDDATE + " TEXT "
                + ");";
    }
    public static abstract class ProjectDeveloperEntry implements BaseColumns{
        // Table name
        public static final String TABLE_PROJECTDEVELOPER = "projectdeveloper";

        // Columns
        public static final String ID = "idprodev";
        public static final String DEVID = "devid";
        public static final String PROID = "proid";
        public static final String ROLE = "role";

        // Create statement
        public static final String CREATE_TABLE_PROJECTDEVELOPER = "CREATE TABLE "
                + TABLE_PROJECTDEVELOPER + "("
                + ProjectDeveloperEntry.ID + " INTEGER PRIMARY KEY,"
                + ProjectDeveloperEntry.DEVID + " INTEGER, "
                + ProjectDeveloperEntry.PROID+" INTEGER,"
                + ProjectDeveloperEntry.ROLE + " TEXT, "
                + " FOREIGN KEY (" +PROID+") REFERENCES "+ ProjectEntry.TABLE_PROJECT+" ("+ID+"), "
                + " FOREIGN KEY (" +DEVID+") REFERENCES "+ DeveloperEntry.TABLE_DEVELOPER+" ("+ID+") "
                + ");";
        // +" FOREIGN KEY ("+PROID+") REFERENCES "+ProjectEntry.TABLE_PROJECT+" ("+ID+"), "
    }

    public static abstract class CommentEntry implements BaseColumns{
        //Table name
        public static final String TABLE_COMMENT="comments";

        //Columns
        public static final String ID= "idcomment";
        public static final String COMMENT="comment";
        public static final String DEV_ID="devID";
        public static final String ISS_ID="issID";

        //Create statement
        public static final String CREATE_TABLE_COMMENT="CREATE TABLE "+
                TABLE_COMMENT+"("
                + CommentEntry.ID+ " INTEGER PRIMARY KEY, "
                + CommentEntry.COMMENT+" TEXT, "
                + CommentEntry.DEV_ID+" INTEGER, "
                + CommentEntry.ISS_ID+" INTEGER, "
                + " FOREIGN KEY (" +DEV_ID+") REFERENCES "+ DeveloperEntry.TABLE_DEVELOPER+" ("+ID+"), "
                + " FOREIGN KEY (" +ISS_ID+") REFERENCES "+ IssueEntry.TABLE_ISSUE+" ("+ID+") "
                +");";
    }


    public static abstract class DeveloperIssueEntry implements BaseColumns{

        public static final String TABLE_DEVELOPERISSUE ="developerissue";

        //Columns
        public static final String ID="iddeviss";
        public static final String DEV_ID="devID";
        public static final String ISS_ID="issId";

        //Create statement
        public static final String CREATE_TABLE_DEVELOPERISSUE ="CREATE TABLE "+ TABLE_DEVELOPERISSUE +"("
                + DeveloperIssueEntry.ID+" INTEGER PRIMARY KEY,"
                + DeveloperIssueEntry.DEV_ID+" INTEGER, "
                + DeveloperIssueEntry.ISS_ID+" INTEGER, "
                +" FOREIGN KEY ("+DEV_ID+") REFERENCES "+ DeveloperEntry.TABLE_DEVELOPER+" ("+ID+"), "
                +" FOREIGN KEY ("+ISS_ID+") REFERENCES "+ IssueEntry.TABLE_ISSUE+" ("+ID+") "
                +");";
    }

    public static abstract class IssueEntry implements BaseColumns{

        public static final String TABLE_ISSUE="issue";
        //Colums
        public static final String ID="idissue";
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
        public static final String CREATE_TABLE_ISSUE="CREATE TABLE "+TABLE_ISSUE+"("
                + IssueEntry.ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + IssueEntry.TITLE+" TEXT,"
                + IssueEntry.DESCRIPTION+" TEXT,"
                + IssueEntry.REFERENCE+" TEXT,"
                + IssueEntry.CATEGORY+" TEXT,"
                + IssueEntry.REPRODUCE+" TEXT,"
                + IssueEntry.EFFECT+" TEXT,"
                + IssueEntry.PRIORITY+" TEXT,"
                + IssueEntry.STATE+" TEXT,"
                + IssueEntry.DATE+" TEXT,"
                + IssueEntry.PROID+" INTEGER,"
                + IssueEntry.DEVID+" INTEGER,"
                +" FOREIGN KEY ("+PROID+") REFERENCES "+ProjectEntry.TABLE_PROJECT+" ("+ID+"), "
                +" FOREIGN KEY ("+DEVID+") REFERENCES "+ DeveloperEntry.TABLE_DEVELOPER+" ("+ID+") "
                +");";
    }

}
