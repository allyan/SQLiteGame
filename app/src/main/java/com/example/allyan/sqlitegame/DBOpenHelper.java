package com.example.allyan.sqlitegame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hackeru on 4/26/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "alyan.db";   // must end with .db!!!
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "gamers";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_TIME = "time";
    public static final String COL_CLICKS = "clicks";

    private static final String CREATE_COMMAND = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " +
            COL_TIME + " TEXT, " +
            COL_CLICKS + " INTEGER)";

    public DBOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the tables if NOT exists...
        db.execSQL(CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // handle versions data transfer.
    }
}
