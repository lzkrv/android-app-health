package com.healthtracking.ui.common;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.healthtracking.R;

import java.util.Calendar;
import java.util.Date;

import static com.healthtracking.Utils.changeDateinDate;
import static com.healthtracking.Utils.changeTimeinDate;
import static com.healthtracking.Utils.getFormattedDate;
import static com.healthtracking.Utils.getFormattedTime;

/**
 * Created by larisa on 6/10/17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Long oldTime = sharedPref.getLong(getString(R.string.current_time), 0);
        Date newDate = new Date();
        newDate.setTime(oldTime);
        newDate = changeDateinDate(newDate, year, (month + 1), day);

        TextView datePlaceholder = (TextView) getActivity().findViewById(R.id.date_placeholder);
        datePlaceholder.setText(getFormattedDate(newDate));

        editor.putLong(getString(R.string.current_time), newDate.getTime());
        editor.commit();
    }
}