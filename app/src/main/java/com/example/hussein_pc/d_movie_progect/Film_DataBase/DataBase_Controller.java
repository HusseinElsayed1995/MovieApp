package com.example.hussein_pc.d_movie_progect.Film_DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Dody-PC on 25-Oct-16.
 */
public class DataBase_Controller {
    DataBase_Helper helper;
    SQLiteDatabase database;

    public DataBase_Controller(Context c){
        helper = new DataBase_Helper(c);

    }

    private void Open(){
        database = helper.getWritableDatabase();

    }

    private void Close(){
        helper.close();

    }


                            //Insert Data In DataBase///
    public long Add_Data(String MovieTitle, String MovieDate, String MovieVote, String MovieOverview, String MoviePoster, String MovieId){
        Open();
        ContentValues values = new ContentValues();
        values.put(DataBase_Helper.Movie_Title,MovieTitle);
        values.put(DataBase_Helper.Movie_Date,MovieDate);
        values.put(DataBase_Helper.Movie_Votes,MovieVote);
        values.put(DataBase_Helper.Movie_POSTER,MoviePoster);
        values.put(DataBase_Helper.MOVIEID,MovieId);
        values.put(DataBase_Helper.Movie_OVERVIEW,MovieOverview);
        database.insert(DataBase_Helper.TABLE_NAME,null,values);
        Close();
        return 0;
    }


    public void Update(String MovieTitle,String MovieDate,String MovieVote,String MovieOverview,String MoviePoster,String MovieId,String id){
        Open();
        ContentValues values = new ContentValues();
        values.put(DataBase_Helper.Movie_Title,MovieTitle);
        values.put(DataBase_Helper.Movie_Date,MovieDate);
        values.put(DataBase_Helper.Movie_Votes, MovieOverview);
        values.put(DataBase_Helper.Movie_POSTER,MoviePoster);
        values.put(DataBase_Helper.MOVIEID,MovieId);

        database.update(DataBase_Helper.TABLE_NAME, values,
                DataBase_Helper.Movie_ROWID + " = " + id, null);
        Close();

    }

    public void Delete(String id){
        Open();
        String[] ARname = {id};
        database.delete(DataBase_Helper.TABLE_NAME, DataBase_Helper.Movie_Title + "=?", ARname);
        Close();
    }


    public Cursor GetAllData(){

        Open();
        String[] column = {DataBase_Helper.Movie_ROWID, DataBase_Helper.Movie_Title, DataBase_Helper.Movie_Date, DataBase_Helper.Movie_OVERVIEW
                , DataBase_Helper.Movie_POSTER, DataBase_Helper.Movie_Votes, DataBase_Helper.MOVIEID};

        Cursor c = database.query(DataBase_Helper.TABLE_NAME, column, null, null, null, null, null);
        return c;
    }

}
