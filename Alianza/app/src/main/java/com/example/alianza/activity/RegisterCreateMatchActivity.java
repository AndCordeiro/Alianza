package com.example.alianza.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.alianza.R;
import com.example.alianza.utils.DateUtils;
import com.example.alianza.utils.TimeUtils;

public class RegisterCreateMatchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
                                                                    , TimePickerDialog.OnTimeSetListener{

    EditText editTextDate;
    EditText editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_create_match);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        editTextDate = (EditText) findViewById(R.id.editTextDateOfMatch);
        editTextTime = (EditText) findViewById(R.id.editTextHourOfMatch);

    }



    public void showDatePickerDialog(View v) {

        DateUtils.createDateDialog(this,this).show();

    }

    public void showTimePickerDialog(View v) {

        TimeUtils.createTimeDialog(this,this).show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        editTextDate.setText(DateUtils.formatDatePicker(dayOfMonth, month, year));

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        editTextTime.setText(TimeUtils.formatTimePicker(hourOfDay, minute));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        System.out.println("Resta");


        return true;
    }

}
