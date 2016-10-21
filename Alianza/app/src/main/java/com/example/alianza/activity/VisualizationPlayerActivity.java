package com.example.alianza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;

public class VisualizationPlayerActivity extends AppCompatActivity {

    //ImageView picturePlayer;
    TextView textViewPlayer;
    TextView textViewPlayerBirth;
    TextView textViewPlayerPosition;
    TextView textViewPlayerDescription;
    Player player;

    public static final String PLAYER = "player";


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

        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra(PLAYER);


        setPlayer(player);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.edit);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(VisualizationPlayerActivity.this, RegisterCreatePlayerActivity.class);
                intent.putExtra(VisualizationPlayerActivity.PLAYER, player);

                startActivity(intent);

                finish();

                return false;
            }
        });


        return true;
    }

    public void setPlayer(Player player) {

        /*PlayerDAO playerDAO = new PlayerDAO(getBaseContext());
        List<Player> player = playerDAO.playersLoadByID(id);;
        */

        textViewPlayer.setText(player.getPlayer());
        textViewPlayerBirth.setText(player.getBirth());
        textViewPlayerPosition.setText(player.getPosition());
        textViewPlayerDescription.setText(player.getDescription());

    }
}
