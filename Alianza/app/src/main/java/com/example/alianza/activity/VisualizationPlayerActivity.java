package com.example.alianza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alianza.R;

public class VisualizationPlayerActivity extends AppCompatActivity {

    //ImageView picturePlayer;
    TextView textViewPlayer;
    TextView textViewPlayerBirth;
    TextView textViewPlayerPosition;
    TextView textViewPlayerDescription;


    public static final String ID = "player";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualization_player);

        // Adding Toolbar to screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //picturePlayer = (ImageView) findViewById(R.id.picturePlayer);
        textViewPlayer = (TextView) findViewById(R.id.textViewPlayer);
        textViewPlayerBirth = (TextView) findViewById(R.id.textViewPlayerBirth);
        textViewPlayerPosition = (TextView) findViewById(R.id.textViewPlayerPosition);
        textViewPlayerDescription = (TextView) findViewById(R.id.textViewPlayerDescription);

        setPlayer(getIntent().getIntExtra(ID, 0));
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

    public void setPlayer(int id) {

        /*PlayerDAO playerDAO = new PlayerDAO(getBaseContext());
        List<Player> player = playerDAO.playersLoadByID(id);;

        textViewPlayer.setText(player.get(0).getPlayer());
        textViewPlayerBirth.setText(DateUtils.formatDate(player.get(0).getBirth(), DateUtils.DATE_DB, DateUtils.DATE_BR));
        textViewPlayerPosition.setText(player.get(0).getPosition());
        textViewPlayerDescription.setText(player.get(0).getDescription());
*/
    }
}
