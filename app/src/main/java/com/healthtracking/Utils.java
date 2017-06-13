package com.healthtracking;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
    private static final SimpleDateFormat hoursFormat = new SimpleDateFormat("HH");
    private static final SimpleDateFormat minutesFormat = new SimpleDateFormat("mm");
    private static final SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy/M/d HH:mm");
    private static final String templateFullFormat = "%s/%s/%s %s:%s";

    public static String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd");
        return dateFormat.format(date);
    }

    public static String getFormattedTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        return dateFormat.format(date);
    }

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date initDateAndTimeFields(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Date currentDate;
        Long savedTime = sharedPref.getLong(activity.getString(R.string.current_time), 0);
        if (savedTime == 0) {
            currentDate = getCurrentDate();
            editor.putLong(activity.getString(R.string.current_time), currentDate.getTime());
            editor.commit();
        } else {
            Date savedDate = new Date();
            savedDate.setTime(savedTime);
            currentDate = savedDate;
        }
        TextView datePlaceholder = (TextView) activity.findViewById(R.id.date_placeholder);
        datePlaceholder.setText(getFormattedDate(currentDate));
        TextView timePlaceholder = (TextView) activity.findViewById(R.id.time_placeholder);
        timePlaceholder.setText(getFormattedTime(currentDate));

        return currentDate;
    }

    public static Date changeDateinDate(Date date, int year, int month, int day) {
        try {
            Date newDate = fullFormat.parse(String.format(
                    templateFullFormat,
                    Integer.toString(year),
                    Integer.toString(month),
                    Integer.toString(day),
                    hoursFormat.format(date),
                    minutesFormat.format(date)
            ));
            return newDate;
        } catch (ParseException ex) {
            //TODO
            return null;
        }
    }

    public static Date changeTimeinDate(Date date, int hours, int minutes) {
        try {
            Date newDate = fullFormat.parse(String.format(
                    templateFullFormat,
                    yearFormat.format(date),
                    monthFormat.format(date),
                    dateFormat.format(date),
                    Integer.toString(hours),
                    Integer.toString(minutes)
            ));
            return newDate;
        } catch (ParseException ex) {
            //TODO
            return null;
        }
    }
}
