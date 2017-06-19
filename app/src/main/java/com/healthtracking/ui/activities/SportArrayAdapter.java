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
import android.widget.TextView;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.Sport;
import com.healthtracking.data.SportDao;

import java.util.List;

public class SportArrayAdapter extends ArrayAdapter<Sport> {
    private final Context context;
    private final List<Sport> values;
    private SportDao sportDao;

    public SportArrayAdapter(Context context, List<Sport> values) {
        super(context, R.layout.activity_sport_list, values);
        this.context = context;
        this.values = values;

        sportDao = ((App)((Activity)context).getApplication()).getDaoSession().getSportDao();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_sport_item, parent, false);
        Sport sport = values.get(position);

        TextView textView = (TextView) rowView.findViewById(R.id.sport_item_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.sport_item_logo);
        ImageView deleteIcon = (ImageView) rowView.findViewById(R.id.sport_item_delete);
        TextView idView = (TextView) rowView.findViewById(R.id.sport_item_id);

        textView.setText(sport.getName());
        idView.setText(sport.getId().toString());
        imageView.setImageResource(sport.getImageDrawableId());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseActivity(position);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseActivity(position);
            }
        });
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteActivity(position);
            }
        });

        return rowView;
    }

    public void chooseActivity(int position) {
        Sport sport = values.get(position);

        sport.setSelectedTimes(sport.getSelectedTimes() + 1);
        sportDao.insertOrReplace(sport);

        Intent intent = new Intent(context, AddSportActivity.class);
        Bundle b = new Bundle();
        b.putLong("SELECTED_ACTIVITY_ID", sport.getId());
        b.putString("SELECTED_ACTIVITY_NAME", sport.getName());
        intent.putExtras(b);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    private void deleteActivity(int position) {
        Sport sport = values.get(position);
        sport.setIsVisible(false);
        sportDao.insertOrReplace(sport);
        values.remove(position);
        this.notifyDataSetChanged();
    }
}