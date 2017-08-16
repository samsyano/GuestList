package com.example.samson.guestlist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAMSON on 8/2/2017.
 */

public class TestUtil {

   public void insertFakeData(SQLiteDatabase db){

        if(db == null){
            return;
        }

        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(GuestListContract.GuestEntry.COLUMN_NAME, "John");
        cv.put(GuestListContract.GuestEntry.COLUMN_PARTYSIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GuestListContract.GuestEntry.COLUMN_NAME, "Tim");
        cv.put(GuestListContract.GuestEntry.COLUMN_PARTYSIZE, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GuestListContract.GuestEntry.COLUMN_NAME, "Jessica");
        cv.put(GuestListContract.GuestEntry.COLUMN_PARTYSIZE, 99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GuestListContract.GuestEntry.COLUMN_NAME, "Larry");
        cv.put(GuestListContract.GuestEntry.COLUMN_PARTYSIZE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GuestListContract.GuestEntry.COLUMN_NAME, "Kim");
        cv.put(GuestListContract.GuestEntry.COLUMN_PARTYSIZE, 45);
        list.add(cv);


//        try{
//            db.beginTransaction();
//

       if(db != null){
           db.delete(GuestListContract.GuestEntry.TABLE_NAME, null, null);
       }

            for(ContentValues c : list){
                db.insert(GuestListContract.GuestEntry.TABLE_NAME, null, c);
            }
//        }catch (SQLException e){
//
//        }finally {
//            db.endTransaction();
//        }

    }
}
