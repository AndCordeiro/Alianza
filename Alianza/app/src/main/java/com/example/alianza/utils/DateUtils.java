package com.example.alianza.utils;

import android.app.DatePickerDialog;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by andre on 11/10/16.
 */

public class DateUtils {

    public static final String DATE_BR = "dd/MM/yyyy";
    public static final String DATETIME_BR = DATE_BR + " HH:mm:ss";

    public static final String DATE_USA = "MM/dd/yyyy";
    public static final String DATETIME_USA = DATE_USA + " HH:mm:ss";

    public static final String DATE_DB = "yyyy-MM-dd";
    public static final String DATETIME_DB = DATE_DB + " HH:mm:ss";

    public static final Locale PT_BR = new Locale("pt", "BR");
    public static final Locale EN_US = new Locale("en", "US");


    public static DatePickerDialog createDateDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(context, onDateSetListener, year, month, day);
    }

    public static String formatDatePicker(int day, int month, int year) {


        String dayFormater = String.valueOf((day > 9) ? day : ("0" + day));
        String monthFormater = String.valueOf(((month + 1) > 9) ? (month + 1) : ("0" + (month + 1)));
        String yearFormater = String.valueOf(year);


        return dayFormater + "/" + monthFormater + "/" + yearFormater;
    }


    public static String getDate(String mask) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(mask);


        return dateFormat.format(calendar.getTime());

    }

    public static String formatDate(String stringDate, String maskFrom, String maskTo) {

        SimpleDateFormat sdf = new SimpleDateFormat(maskFrom);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(maskTo);

        return dateFormat.format(cal.getTime());
    }

    public static String formatDate(String stringDate, String maskFrom, String maskTo, String a) {

        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(maskTo);

        return dateFormat.format(cal.getTime());
    }


    public static String getAge(String dateBirth) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_BR);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar today = Calendar.getInstance();


        int age = today.get(Calendar.YEAR) - cal.get(Calendar.YEAR);

        cal.add(Calendar.YEAR, age);


        if (today.before(cal)) {

            age--;

        }

        return Integer.toString(age);
    }


}





