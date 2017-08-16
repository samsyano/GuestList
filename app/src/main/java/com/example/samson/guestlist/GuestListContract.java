package com.example.samson.guestlist;

import android.provider.BaseColumns;

/**
 * Created by SAMSON on 8/2/2017.
 */

public class GuestListContract {

    public class GuestEntry implements BaseColumns{

        public static final String TABLE_NAME = "Guest_table";

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "Guest_Name";
        public static final String COLUMN_PARTYSIZE = "Party_size";
        public static final String COLUMN_TIMESTAMP = "Guest_timestamp";
    }
}
