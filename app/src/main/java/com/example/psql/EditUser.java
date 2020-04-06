package com.example.psql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    Button Edit;
    EditText textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Edit = findViewById(R.id.Edit);
        textViewName = findViewById(R.id.Name);

        Intent editIntent = getIntent();

        final Integer ID = editIntent.getIntExtra("editId", 0);
        final String Name = editIntent.getStringExtra("editName");

        textViewName.setText(Name);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(getBaseContext());
                SQLiteDatabase db = feedReaderDbHelper.getWritableDatabase();

// New value for one column

                ContentValues values = new ContentValues();
                values.put(FeedReaderDbHelper.COLUMN_NAME_NAME, textViewName.getText().toString());

// Which row to update, based on the title
                String selection = FeedReaderDbHelper.COLUMN_NAME_ID + " = ?";
                String[] selectionArgs = { ID.toString() };

                int count = db.update(
                        FeedReaderDbHelper.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                if (count == 1) {
                    Toast.makeText(EditUser.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditUser.this, ListActivity.class);
                    startActivity(intent);
                }else {

                    Toast.makeText(EditUser.this, "Updated Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
