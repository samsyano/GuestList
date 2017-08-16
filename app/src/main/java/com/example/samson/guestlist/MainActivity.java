package com.example.samson.guestlist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText guestName, guestNumber;
    Button addGuestButton;
    RecyclerView recyclerView;

    GuestRecyclerView guestListAdapter;
    Cursor cursor;
    GuestListDbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guestName = (EditText) findViewById(R.id.guest_name);
        guestNumber = (EditText) findViewById(R.id.gu_number);
        addGuestButton = (Button) findViewById(R.id.add_guest);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbHelper = new GuestListDbHelper(this);

        sqLiteDatabase = dbHelper.getWritableDatabase();
//        new TestUtil().insertFakeData(sqLiteDatabase);

        cursor = queryAllGuest();
        guestListAdapter = new GuestRecyclerView(this, cursor);

        recyclerView.setAdapter(guestListAdapter);

      new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
          @Override
          public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
              return false;
          }

          @Override
          public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

              Long id = (Long) viewHolder.itemView.getTag();

              removeGuest(id);
              guestListAdapter.swapCursor(queryAllGuest());
          }
      }).attachToRecyclerView(recyclerView);

    }

    public boolean removeGuest(Long id){
        return sqLiteDatabase.delete(GuestListContract.GuestEntry.TABLE_NAME,
                GuestListContract.GuestEntry._ID + "=" + id, null) > 0;
    }

    public void addToWaitList(View view){

        int number = 1;
        String numberString = guestNumber.getText().toString();
        String nameString = guestName.getText().toString();
        if(guestName.getText().length() == 0 || guestNumber.getText().length() == 0){
            return;
        }
         number = Integer.parseInt(numberString);

        addData(nameString, number);
       guestListAdapter.swapCursor(queryAllGuest());

        guestName.clearFocus();
        guestNumber.clearFocus();
        guestName.getText().clear();
        guestNumber.getText().clear();
    }

    Long addData(String name, int value){
        ContentValues cv = new ContentValues();

        cv.put(GuestListContract.GuestEntry.COLUMN_NAME, name);
        cv.put(GuestListContract.GuestEntry.COLUMN_PARTYSIZE, value);

      return   sqLiteDatabase.insert(GuestListContract.GuestEntry.TABLE_NAME, null, cv);
    }

    Cursor queryAllGuest(){
      return  sqLiteDatabase.query(GuestListContract.GuestEntry.TABLE_NAME,
                null,
                null,
                null,
                null, null,
                GuestListContract.GuestEntry.COLUMN_TIMESTAMP);
    }
}
