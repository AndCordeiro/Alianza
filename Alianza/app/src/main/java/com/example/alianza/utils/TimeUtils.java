package com.example.alianza.utils;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by andre on 11/10/16.
 */

public class TimeUtils {

    private Context context;
    private TimePickerDialog.OnTimeSetListener onTimeSetListener;



    public TimeUtils(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener) {

        this.context = context;
        this.onTimeSetListener = onTimeSetListener;
    }

    public TimePickerDialog onCreateDialog() {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(context, onTimeSetListener, hour, minute,
                DateFormat.is24HourFormat(context));
    }


}
