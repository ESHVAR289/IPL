package com.ipl.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ipl.viewmodel.TeamViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 17/05/16.
 */
public class DBHandler extends SQLiteOpenHelper {
    //--Variables for database Name,Version and Table names
    Context context;
    Cursor cursor;

    SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "IPL_Database";
    public static final String DB_TABLE_NAME = "team_view_table";

    //--Table column names
    public static final String COLUMN_TEAM_ID = "_id";
    public static final String COLUMN_TEAM_NAME = "team_name";
    public static final String COLUMN_TEAM_OWNER = "team_owner";
    public static final String COLUMN_TEAM_CAPTAIN = "team_captain";
    public static final String COLUMN_TEAM_COACH = "team_coach";
    public static final String COLUMN_TEAM_HOME_VENUE = "team_home_venue";
    public static final String COLUMN_TEAM_IMAGE_DATA = "team_image_data";

    public static final String DB_TAG = "DBHandler";

    //--Query for table creation
    public static final String CREATE_TABLE_TEAM_VIEW =
            "create table " + DB_TABLE_NAME +
                    "(" + COLUMN_TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + COLUMN_TEAM_NAME + " VARCHAR(45) NOT NULL ,"
                    + COLUMN_TEAM_OWNER + " VARCHAR(45) NOT NULL ,"
                    + COLUMN_TEAM_CAPTAIN + " VARCHAR(15) NOT NULL ,"
                    + COLUMN_TEAM_COACH + " VARCHAR(15) NOT NULL ,"
                    + COLUMN_TEAM_HOME_VENUE + " VARCHAR(45) NOT NULL ,"
                    + COLUMN_TEAM_IMAGE_DATA + " BLOB" +
                    ");";

    //--Constructor to create table
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_TEAM_VIEW);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DB_TAG, "Upgrading database from version" + oldVersion + " to " + newVersion +
                " which will destroy all the old data");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }

    //--Open the database
    public DBHandler open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    //--Close the database
    public void close() {
        close();
    }

    //--Team data insert into database
    public boolean insertTeamDataIntoDB(String team_name, String team_owner,
                                        String team_captain, String team_coach,
                                        String team_home_venue, byte[] team_image_data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEAM_NAME, team_name);
        contentValues.put(COLUMN_TEAM_OWNER, team_owner);
        contentValues.put(COLUMN_TEAM_CAPTAIN, team_captain);
        contentValues.put(COLUMN_TEAM_COACH, team_coach);
        contentValues.put(COLUMN_TEAM_HOME_VENUE, team_home_venue);
        contentValues.put(COLUMN_TEAM_IMAGE_DATA, team_image_data);

        long result = db.insert(DB_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //--Retrieve a team information
    public Cursor getTeamData() {
        Cursor mCursor = db.query(DB_TABLE_NAME,
                new String[]{COLUMN_TEAM_ID, COLUMN_TEAM_NAME, COLUMN_TEAM_OWNER,
                        COLUMN_TEAM_CAPTAIN, COLUMN_TEAM_COACH, COLUMN_TEAM_HOME_VENUE,
                        COLUMN_TEAM_IMAGE_DATA},
                null, null, null, null, null);

        return mCursor;
    }

    //--deleting all the information from the database
    public void deleteDB(){
        onUpgrade(db,DATABASE_VERSION,DATABASE_VERSION);
    }

    //--Retrieve a particular team information
    public Cursor getParticularTeamData(long teamId) throws SQLException {
        Cursor mCursor = db.query(true, DB_TABLE_NAME,
                new String[]{COLUMN_TEAM_ID, COLUMN_TEAM_NAME, COLUMN_TEAM_OWNER,
                        COLUMN_TEAM_CAPTAIN, COLUMN_TEAM_COACH, COLUMN_TEAM_HOME_VENUE,
                        COLUMN_TEAM_IMAGE_DATA},
                COLUMN_TEAM_ID + "=" + teamId, null, null, null, null, null
        );
        if (mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }

    public boolean dataCheckFromDB() {
        Cursor cursor;
        try {
            cursor = getTeamData();
        } catch (Exception e) {
            return false;
        }
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    //collecting teamInfo from database
    public JSONArray getTeamInfoDatabase() {
        open();
        Cursor teamInfoData = getTeamData();
        JSONArray jsonArray = convertToJsonArray(teamInfoData);
        teamInfoData.close();
        return jsonArray;
    }

    //converting cursor into the ArrayList<TeamViewModel>
    public ArrayList<TeamViewModel> convertToArrayList(Cursor cursor) {
        ArrayList<TeamViewModel> list = new ArrayList<>();
        TeamViewModel teamViewModel = new TeamViewModel();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int totalColumn = cursor.getColumnCount();
            for (int i = 0; i < totalColumn; i++) {
                //  list.add(cursor.getColumnIndex(i));
            }
        }
        return list;
    }

    //converting Cursor data into Json Array
    public JSONArray convertToJsonArray(Cursor cursor) {
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        rowObject.put(cursor.getColumnName(i),
                                cursor.getString(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        return resultSet;
    }
}
