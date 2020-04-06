package com.example.psql;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {


    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);

        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( parent.getContext(),user.Name, Toast.LENGTH_SHORT).show();
                Intent userEdit = new Intent(getContext(), EditUser.class);
                userEdit.putExtra("editName", user.Name);
                userEdit.putExtra("editId", user.ID);
                getContext().startActivity(userEdit);
            }
        });

        // Populate the data into the template view using the data object
        tvName.setText(user.Name);
        tvId.setText(user.ID.toString() + " - ");

        // Return the completed view to render on screen
        return convertView;
    }


}