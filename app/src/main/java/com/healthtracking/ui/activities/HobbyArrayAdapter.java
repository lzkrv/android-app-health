package com.healthtracking.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthtracking.R;
import com.healthtracking.data.FakeHobbyProvider;
import com.healthtracking.data.Hobby;

import java.util.List;

public class HobbyArrayAdapter extends ArrayAdapter<Hobby> {
    private final Context context;
    private final List<Hobby> values;

    public HobbyArrayAdapter(Context context, List<Hobby> values) {
        super(context, R.layout.activity_hobby_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_hobby_item, parent, false);
        Hobby hobby = values.get(position);

        TextView textView = (TextView) rowView.findViewById(R.id.hobby_item_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.hobby_item_logo);
        ImageView deleteIcon = (ImageView) rowView.findViewById(R.id.hobby_item_delete);
        TextView idView = (TextView) rowView.findViewById(R.id.hobby_item_id);

        textView.setText(hobby.getName());
        idView.setText("" + hobby.getId());
        imageView.setImageResource(hobby.getImageDrawableId());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseActivity(view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseActivity(view);
            }
        });
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteActivity(view);
            }
        });

        return rowView;
    }

    public void chooseActivity(View view) {
        RelativeLayout parent = (RelativeLayout) view.getParent();
        int activityId = Integer.parseInt((String)((TextView) parent.getChildAt(3)).getText());
        String activityName = (String) ((TextView) parent.getChildAt(1)).getText();

        Intent intent = new Intent(view.getContext(), AddHobbyActivity.class);
        Bundle b = new Bundle();
        b.putInt("SELECTED_ACTIVITY_ID", activityId);
        b.putString("SELECTED_ACTIVITY_NAME", activityName);
        intent.putExtras(b);
        getContext().startActivity(intent);
        ((Activity)context).finish();
    }

    private void deleteActivity(View view) {
        RelativeLayout parent = (RelativeLayout) view.getParent();
        int activityId = Integer.parseInt((String)((TextView) parent.getChildAt(3)).getText());
        FakeHobbyProvider.getInstance().deleteHobby(activityId);
        this.notifyDataSetChanged();
    }
}