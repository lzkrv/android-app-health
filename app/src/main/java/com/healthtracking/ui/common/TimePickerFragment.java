package com.healthtracking.ui.common;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.healthtracking.R;

import java.util.Calendar;
import java.util.Date;

import static com.healthtracking.Utils.changeTimeinDate;
import static com.healthtracking.Utils.getFormattedTime;


public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Long oldTime = sharedPref.getLong(getString(R.string.current_time), 0);
        Date newDate = new Date();
        newDate.setTime(oldTime);
        newDate = changeTimeinDate(newDate, hourOfDay, minute);

        TextView timePlaceholder = (TextView) getActivity().findViewById(R.id.time_placeholder);
        timePlaceholder.setText(getFormattedTime(newDate));

        editor.putLong(getString(R.string.current_time), newDate.getTime());
        editor.commit();
    }
}