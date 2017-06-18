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
import com.healthtracking.data.FakeSportProvider;
import com.healthtracking.data.Sport;

import java.util.List;

public class SportArrayAdapter extends ArrayAdapter<Sport> {
    private final Context context;
    private final List<Sport> values;

    public SportArrayAdapter(Context context, List<Sport> values) {
        super(context, R.layout.activity_sport_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_sport_item, parent, false);
        Sport sport = values.get(position);

        TextView textView = (TextView) rowView.findViewById(R.id.sport_item_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.sport_item_logo);
        ImageView deleteIcon = (ImageView) rowView.findViewById(R.id.sport_item_delete);
        TextView idView = (TextView) rowView.findViewById(R.id.sport_item_id);

        textView.setText(sport.getName());
        idView.setText("" + sport.getId());
        imageView.setImageResource(sport.getImageDrawableId());

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

        Intent intent = new Intent(view.getContext(), AddSportActivity.class);
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
        FakeSportProvider.getInstance().deleteSport(activityId);
        this.notifyDataSetChanged();
    }
}