package com.example.psql;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        SQLiteDatabase db = feedReaderDbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderDbHelper.COLUMN_NAME_ID,
                FeedReaderDbHelper.COLUMN_NAME_NAME,
        };


// Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderDbHelper.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { "Pedram" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderDbHelper.COLUMN_NAME_ID + " ASC";

        Cursor cursor = db.query(
                FeedReaderDbHelper.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Create the adapter to convert the array to views
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        while(cursor.moveToNext()) {

            String itemName = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderDbHelper.COLUMN_NAME_NAME)
            );

            Integer itemID = cursor.getInt(
                    cursor.getColumnIndexOrThrow(FeedReaderDbHelper.COLUMN_NAME_ID)
            );
            // Add item to adapter
            User newUser = new User(itemName, itemID);
            adapter.add(newUser);

        }
        cursor.close();



    }




}
