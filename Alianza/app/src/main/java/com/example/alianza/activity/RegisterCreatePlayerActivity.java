package com.example.alianza.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.alianza.R;
import com.example.alianza.utils.DateUtils;

public class RegisterCreatePlayerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText editTextBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_create_player);

        //ui control
        CollapsingToolbarLayout collapsingToolbarLayout;
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextBirth = (EditText) findViewById(R.id.editTextPlayerBirth);

    }



    public void showDatePickerDialog(View v) {
        DateUtils.createDateDialog(this,this).show();
    }

    public void addPhotoPlayer(View v) {

        System.out.println(" Button photo ");

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        editTextBirth.setText(DateUtils.formatDatePicker(dayOfMonth, month, year));


    }


}
