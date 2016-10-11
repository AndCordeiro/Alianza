package com.example.alianza.utils;

import android.app.DatePickerDialog;
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

public class DateUtils {

    public static DatePickerDialog createDateDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(context, onDateSetListener, year, month, day);
    }

    public static String formatDatePicker(int day, int month, int year){

        String dayFormater = String.valueOf((day>9)?day:("0" + day));
        String monthFormater = String.valueOf((month>9)?month:("0" + month));
        String yearFormater = String.valueOf(year);


        return dayFormater + "/" + monthFormater + "/" + yearFormater;
    }

    public static String formatDatePicker(int day, int month, int year, String mask){

        String dayFormater = String.valueOf((day>9)?day:("0" + day));
        String monthFormater = String.valueOf((month>9)?month:("0" + month));
        String yearFormater = String.valueOf(year);


        return dayFormater + mask + monthFormater + mask + yearFormater;
    }

}





