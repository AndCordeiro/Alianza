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
import android.widget.Toast;

import com.example.alianza.R;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;
import com.example.alianza.utils.DateUtils;
import com.example.alianza.utils.TimeUtils;

public class RegisterCreateMatchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
        , TimePickerDialog.OnTimeSetListener {

    EditText dateOfMatch;
    EditText hourOfMatch;
    EditText opponentTeam;
    EditText placeOfMatch;
    EditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_create_match);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dateOfMatch = (EditText) findViewById(R.id.editTextDateOfMatch);
        hourOfMatch = (EditText) findViewById(R.id.editTextHourOfMatch);
        opponentTeam = (EditText) findViewById(R.id.editTextOpponentTeam);
        placeOfMatch = (EditText) findViewById(R.id.editTextPlaceOfMatch);
        description = (EditText) findViewById(R.id.editTextDescriptionMatch);

    }


    public void showDatePickerDialog(View v) {

        DateUtils.createDateDialog(this, this).show();

    }

    public void showTimePickerDialog(View v) {

        TimeUtils.createTimeDialog(this, this).show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        dateOfMatch.setText(DateUtils.formatDatePicker(dayOfMonth, month, year));

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hourOfMatch.setText(TimeUtils.formatTimePicker(hourOfDay, minute));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.check);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //MatchDAO matchDAO = new MatchDAO(getBaseContext());


                String dateOfMatchString = dateOfMatch.getText().toString();
                String hourOfMatchString = hourOfMatch.getText().toString();
                String opponentTeamString = opponentTeam.getText().toString();
                String placeOfMatchString = placeOfMatch.getText().toString();
                String descriptionString = description.getText().toString();
                //String resultado;


                if (dateOfMatchString != null && !dateOfMatchString.isEmpty() && !dateOfMatchString.equals("") && hourOfMatchString != null && !hourOfMatchString.isEmpty() && !hourOfMatchString.equals("") && opponentTeamString != null && !opponentTeamString.isEmpty() && !opponentTeamString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && placeOfMatchString != null && !placeOfMatchString.isEmpty() && !placeOfMatchString.equals("")) {


                    //resultado = matchDAO.dataInsert(DateUtils.formatDate(dateOfMatchString, DateUtils.DATE_BR, DateUtils.DATE_DB), hourOfMatchString, Match.TEAM, opponentTeamString, descriptionString, placeOfMatchString);

                   // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                    Match match = new Match(dateOfMatchString, hourOfMatchString, opponentTeamString, placeOfMatchString, descriptionString);

                    FireBaseInsert f = new FireBaseInsert();
                    f.insertData(match);

                    finish();

                } else {

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.all_fields_mandatory), Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });


        return true;
    }



}
