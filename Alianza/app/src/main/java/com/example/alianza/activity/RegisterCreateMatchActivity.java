package com.example.alianza.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.example.alianza.utils.DateUtils;
import com.example.alianza.utils.TimeUtils;

public class RegisterCreateMatchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
        , TimePickerDialog.OnTimeSetListener {

    private EditText dateOfMatch;
    private EditText hourOfMatch;
    private EditText opponentTeam;
    private EditText placeOfMatch;
    private EditText description;
    private int id = 0;
    Match matchVisualization;

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


        Intent intent = getIntent();
        matchVisualization = (Match) intent.getSerializableExtra(VisualizationMatchActivity.MATCH);
        if (matchVisualization != null) {

            id = 1;

            setMatch(matchVisualization);
        }

    }


    public void showDatePickerDialog(View v) {

        DateUtils.createDateDialog(this, this).show();

    }

    public void showTimePickerDialog(View v) {

        TimeUtils.createTimeDialog(this, this).show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        dateOfMatch.setText(DateUtils.formatDatePicker(dayOfMonth, month, year, this.getResources().getConfiguration().locale.getLanguage()));

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

                String dateOfMatchString = dateOfMatch.getText().toString();
                String hourOfMatchString = hourOfMatch.getText().toString();
                String opponentTeamString = opponentTeam.getText().toString();
                String placeOfMatchString = placeOfMatch.getText().toString();
                String descriptionString = description.getText().toString();



                if (id != 1) {

                    if (dateOfMatchString != null && !dateOfMatchString.isEmpty() && !dateOfMatchString.equals("") && hourOfMatchString != null && !hourOfMatchString.isEmpty() && !hourOfMatchString.equals("") && opponentTeamString != null && !opponentTeamString.isEmpty() && !opponentTeamString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && placeOfMatchString != null && !placeOfMatchString.isEmpty() && !placeOfMatchString.equals("")) {

                        if(!getResources().getConfiguration().locale.getLanguage().equals("pt")){

                            dateOfMatchString = DateUtils.formatDate(dateOfMatchString, DateUtils.DATE_USA, DateUtils.DATE_BR);

                        }

                        Match match = new Match(dateOfMatchString, hourOfMatchString, opponentTeamString, placeOfMatchString, descriptionString);

                        FireBaseInsert f = new FireBaseInsert();
                        f.insertData(match);

                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.all_fields_mandatory), Toast.LENGTH_LONG).show();
                    }

                } else {


                    if (dateOfMatchString != null && !dateOfMatchString.isEmpty() && !dateOfMatchString.equals("") && hourOfMatchString != null && !hourOfMatchString.isEmpty() && !hourOfMatchString.equals("") && opponentTeamString != null && !opponentTeamString.isEmpty() && !opponentTeamString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && placeOfMatchString != null && !placeOfMatchString.isEmpty() && !placeOfMatchString.equals("")) {

                        if(!getResources().getConfiguration().locale.getLanguage().equals("pt")){

                            dateOfMatchString = DateUtils.formatDate(dateOfMatchString, DateUtils.DATE_USA, DateUtils.DATE_BR);

                        }

                        Match match = new Match(dateOfMatchString, hourOfMatchString, opponentTeamString, placeOfMatchString, descriptionString);


                        match.setId(matchVisualization.getId());

                        FireBaseInsert f = new FireBaseInsert();
                        f.updateData(match);


                        finish();


                    } else {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.all_fields_mandatory), Toast.LENGTH_LONG).show();
                    }


                }

                return false;
            }
        });


        return true;
    }


    public void setMatch(Match match) {

        dateOfMatch.setText(match.getDateOfMatch());
        hourOfMatch.setText(match.getHourOfMatch());
        opponentTeam.setText(match.getOpponentTeam());
        placeOfMatch.setText(match.getPlaceOfMatch());
        description.setText(match.getDescription());


    }
}
