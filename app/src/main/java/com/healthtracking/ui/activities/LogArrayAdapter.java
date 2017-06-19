package com.healthtracking.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthtracking.App;
import com.healthtracking.R;
import com.healthtracking.data.HealthLevel;
import com.healthtracking.data.Log;
import com.healthtracking.data.LogDao;
import com.healthtracking.data.LogFood;
import com.healthtracking.data.LogHealth;
import com.healthtracking.data.LogHobby;
import com.healthtracking.data.LogMood;
import com.healthtracking.data.LogSport;
import com.healthtracking.data.MoodLevel;

import java.util.List;

public class LogArrayAdapter extends ArrayAdapter<Log> {
    private final Context context;
    private final List<Log> values;

    private LogDao logDao;

    public LogArrayAdapter(Context context, List<Log> values) {
        super(context, R.layout.log_item, values);
        this.context = context;
        this.values = values;

        logDao = ((App)((Activity)context).getApplication()).getDaoSession().getLogDao();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.log_item, parent, false);
        Log log = values.get(position);

        TextView textView = (TextView) rowView.findViewById(R.id.log_item_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.log_item_logo);
        ImageView deleteIcon = (ImageView) rowView.findViewById(R.id.log_item_delete);
        TextView idView = (TextView) rowView.findViewById(R.id.log_item_id);

        idView.setText(log.getId().toString());
        if (log.getLogFood() != null) {
            LogFood logFood = log.getLogFood();
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.food));
            textView.setText("" + logFood.getMealKind());
        } else if (log.getLogHealth() != null) {
            LogHealth logHealth = log.getLogHealth();
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.health));
            textView.setText(HealthLevel.values()[logHealth.getHealthLevel()].getDescription());
        } else if (log.getLogHobby() != null) {
            LogHobby logHobby = log.getLogHobby();
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.hobby));
            textView.setText(logHobby.getHobbyType().getName());
        } else if (log.getLogMood() != null) {
            LogMood logMood = log.getLogMood();
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.mood));
            textView.setText(MoodLevel.values()[logMood.getMoodLevel()].getDescription());
        } else if (log.getLogSport() != null) {
            LogSport logSport = log.getLogSport();
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.sport));
            textView.setText(logSport.getSportType().getName());
        }

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteActivity(view, position);
            }
        });

        return rowView;
    }

    private void deleteActivity(View view, int position) {
        Log log = values.get(position);
        logDao.delete(log);
        values.remove(position);
        this.notifyDataSetChanged();
    }
}