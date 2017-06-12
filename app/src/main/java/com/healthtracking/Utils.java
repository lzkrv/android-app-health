package com.healthtracking;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by larisa on 6/11/17.
 */

public class Utils {
    public static String[] getCurrentDateAndTime() {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/YYYY");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        Calendar c = Calendar.getInstance();
        String[] dateAndTime = {date.format(c.getTime()), time.format(c.getTime())};
        return dateAndTime;
    }
}
