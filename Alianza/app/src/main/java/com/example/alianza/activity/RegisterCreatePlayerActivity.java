package com.example.alianza.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabPlayer);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            addPhotoPlayer(v);

            }
        });


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
