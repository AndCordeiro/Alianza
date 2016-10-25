package com.example.alianza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.Match;

public class VisualizationMatchActivity extends AppCompatActivity {


    TextView textViewDateOfMatch;
    TextView textViewHourOfMatch;
    TextView textViewOpponentTeam;
    TextView textViewPlaceOfMatch;
    TextView textViewDescriptionMatch;
    Match match;



    public static final String MATCH = "match";

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




        Intent intent = getIntent();
        match = (Match) intent.getSerializableExtra(MATCH);

        setMatch(match);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.edit);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(MainActivity.isAdmin){

                    Intent intent = new Intent(VisualizationMatchActivity.this, RegisterCreateMatchActivity.class);
                    intent.putExtra(VisualizationMatchActivity.MATCH, match);

                    startActivity(intent);

                    finish();


                }else{

                    Snackbar.make(findViewById(R.id.visualizationMatch_content), getResources().getString(R.string.not_permission), Snackbar.LENGTH_LONG)
                            .show();

                }


                return false;
            }
        });


        return true;
    }


    public void setMatch(Match match){

        /*
        MatchDAO matchDAO = new MatchDAO(getBaseContext());
        List<Match> match = matchDAO.newsLoadByID(id);;
        */

        textViewDateOfMatch.setText(match.getDateOfMatch());
        textViewHourOfMatch.setText(match.getHourOfMatch());
        textViewOpponentTeam.setText(match.getOpponentTeam());
        textViewPlaceOfMatch.setText(match.getPlaceOfMatch());
        textViewDescriptionMatch.setText(match.getDescription());

    }


}
