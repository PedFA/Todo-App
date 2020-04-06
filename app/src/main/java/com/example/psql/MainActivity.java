package com.example.psql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button btnadd;
    Button showtxt;
    final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit_name);
        btnadd = findViewById(R.id.btn_add);
        showtxt = findViewById(R.id.btn_intent);


        showtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Gets the data repository in write mode
                SQLiteDatabase db = feedReaderDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(FeedReaderDbHelper.COLUMN_NAME_NAME, name.getText().toString());

// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(FeedReaderDbHelper.TABLE_NAME, null, values);

            }
        });
    }
}
