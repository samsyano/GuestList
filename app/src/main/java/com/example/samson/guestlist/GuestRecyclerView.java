package com.example.samson.guestlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SAMSON on 8/2/2017.
 */

public class GuestRecyclerView extends RecyclerView.Adapter<GuestRecyclerView.GuestViewHolder> {

    Context context;
    Cursor cursor;

    public GuestRecyclerView(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }
    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);

        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        if (!cursor.moveToPosition(position))
            return;

            String name = cursor.getString(cursor.getColumnIndex(GuestListContract.GuestEntry.COLUMN_NAME));
            int number = cursor.getInt(cursor.getColumnIndex(GuestListContract.GuestEntry.COLUMN_PARTYSIZE));

            Long partyId = cursor.getLong(cursor.getColumnIndex(GuestListContract.GuestEntry._ID));

            holder.guestName.setText(name);
            holder.numView.setText(String.valueOf(number));

            /***This code won't display****/
            holder.itemView.setTag(partyId);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder {

        TextView numView;
        TextView guestName;

        public GuestViewHolder(View itemView) {
            super(itemView);
            numView = (TextView) itemView.findViewById(R.id.guest_number);
            guestName = (TextView) itemView.findViewById(R.id.name_guest);
        }
    }
}
