package com.example.hussein_pc.d_movie_progect.Film_DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dody-PC on 25-Oct-16.
 */
public class DataBase_Helper extends SQLiteOpenHelper {

    //Database
    //name //version
    private static final String  DATABASE_NAME = "MovieFilms";
    private static final int DATABASE_VERSION = 1;

    //TABLE
    public static final String TABLE_NAME = "MovieData";
    public static final String Movie_ROWID    = "_id";
    public static final String Movie_Title    = "title";
    public static final String Movie_Date    = "data";
    public static final String Movie_Votes     = "vote";
    public static final String Movie_OVERVIEW = "overview";
    public static final String Movie_POSTER   = "poster";
    public static final String MOVIEID  = "movie_id";


    ////Create Table
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    Movie_ROWID + " integer PRIMARY KEY autoincrement," +
                    Movie_Title + " TEXT," +
                    Movie_Date + " TEXT," +
                    Movie_Votes + " TEXT," +
                    Movie_OVERVIEW + " TEXT," +
                    Movie_POSTER + " TEXT,"+
                    MOVIEID+" TEXT);";


    public DataBase_Helper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF Exists "+TABLE_NAME);
        onCreate(db);
    }
}
