package com.example.alianza.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by andre on 11/10/16.
 */

public class TimeUtils {



    public static TimePickerDialog createTimeDialog(Context context,TimePickerDialog.OnTimeSetListener onTimeSetListener ) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(context, onTimeSetListener, hour, minute,
                DateFormat.is24HourFormat(context));
    }

    public static String formatTimePicker(int hour, int minute){

        String hourFormater = String.valueOf((hour>9)?hour:("0" + hour));
        String minuteFormater = String.valueOf((minute>9)?minute:("0" + minute));


        return hourFormater + ":" + minuteFormater;
    }

    public static String formatTimePicker(int hour, int minute, String mask){

        String hourFormater = String.valueOf((hour>9)?hour:("0" + hour));
        String minuteFormater = String.valueOf((minute>9)?minute:("0" + minute));


        return hourFormater + mask + minuteFormater;
    }


}
