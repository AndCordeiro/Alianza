package com.example.alianza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alianza.R;

public class VisualizationMatchActivity extends AppCompatActivity {


    TextView textViewDateOfMatch;
    TextView textViewHourOfMatch;
    TextView textViewOpponentTeam;
    TextView textViewPlaceOfMatch;
    TextView textViewDescriptionMatch;


    public static final String ID = "match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualization_match);

        // Adding Toolbar to screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textViewDateOfMatch = (TextView) findViewById(R.id.textViewDateOfMatch);
        textViewHourOfMatch = (TextView) findViewById(R.id.textViewHourOfMatch);
        textViewOpponentTeam = (TextView) findViewById(R.id.textViewOpponentTeam);
        textViewPlaceOfMatch = (TextView) findViewById(R.id.textViewPlaceOfMatch);
        textViewDescriptionMatch = (TextView) findViewById(R.id.textViewDescriptionMatch);


        setMatch(getIntent().getIntExtra(ID, 0));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.edit);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {



                System.out.println("Teste hahaha");

                return false;
            }
        });


        return true;
    }


    public void setMatch(int id){
/*

        MatchDAO matchDAO = new MatchDAO(getBaseContext());
        List<Match> match = matchDAO.newsLoadByID(id);;

        textViewDateOfMatch.setText(DateUtils.formatDate(match.get(0).getDateOfMatch(),DateUtils.DATE_DB, DateUtils.DATE_BR));
        textViewHourOfMatch.setText(match.get(0).getHourOfMatch());
        textViewOpponentTeam.setText(match.get(0).getOpponentTeam());
        textViewPlaceOfMatch.setText(match.get(0).getPlaceOfMatch());
        textViewDescriptionMatch.setText(match.get(0).getDescription());
*/
    }

}
