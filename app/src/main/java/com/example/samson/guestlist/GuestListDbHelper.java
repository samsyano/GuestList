package com.example.samson.guestlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAMSON on 8/2/2017.
 */

public class GuestListDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "GuestList.db";
    public static final int DB_VERSION = 1;

    public GuestListDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_TABLE = "CREATE TABLE " + GuestListContract.GuestEntry.TABLE_NAME +
                " ( "+ GuestListContract.GuestEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GuestListContract.GuestEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                GuestListContract.GuestEntry.COLUMN_PARTYSIZE + " INTEGER NOT NULL, " +
                GuestListContract.GuestEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                " ); ";

        db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = "DROP TABLE IF EXISTS " + GuestListContract.GuestEntry.TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
