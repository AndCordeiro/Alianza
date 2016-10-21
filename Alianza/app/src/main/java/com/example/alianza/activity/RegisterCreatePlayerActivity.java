package com.example.alianza.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alianza.R;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;
import com.example.alianza.utils.DateUtils;

public class RegisterCreatePlayerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText birth;
    private EditText player;
    private EditText description;
    private EditText position;
    private ImageView photo;
    private int id = 0;
    Player playerVisualization;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_create_player);

        //ui control
        CollapsingToolbarLayout collapsingToolbarLayout;
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        birth = (EditText) findViewById(R.id.editTextPlayerBirth);
        player = (EditText) findViewById(R.id.editTextPlayer);
        description = (EditText) findViewById(R.id.editTextPlayerDescription);
        position = (EditText) findViewById(R.id.editTextPlayerPosition);
        photo = (ImageView) findViewById(R.id.picturePlayer);


        Intent intent = getIntent();
        playerVisualization = (Player) intent.getSerializableExtra(VisualizationPlayerActivity.PLAYER);
        if (playerVisualization != null) {

            id = 1;

            setPlayer(playerVisualization);
        }


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabPlayer);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPhotoPlayer(v);

            }
        });


    }


    public void showDatePickerDialog(View v) {

        DateUtils.createDateDialog(this, this).show();

    }

    public void addPhotoPlayer(View v) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(R.string.button_galery), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        getGalery();

                    }
                })
                .setNegativeButton(getResources().getString(R.string.button_camera), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        getCamera();

                    }
                })
                .setTitle(getResources().getString(R.string.choice))
                .show();


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        birth.setText(DateUtils.formatDatePicker(dayOfMonth, month, year));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.check);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

               // PlayerDAO playerDAO = new PlayerDAO(getBaseContext());


                String birthString = birth.getText().toString();
                String playerString = player.getText().toString();
                String descriptionString = description.getText().toString();
                String positionString = position.getText().toString();
                //String photoString = ;
                //String resultado;

                if (id != 1) {

                    if (birthString != null && !birthString.isEmpty() && !birthString.equals("") && playerString != null && !playerString.isEmpty() && !playerString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && positionString != null && !positionString.isEmpty() && !positionString.equals("")) {


                        //resultado = playerDAO.dataInsert(DateUtils.formatDate(birthString, DateUtils.DATE_BR, DateUtils.DATE_DB), playerString, descriptionString, positionString, null);

                        // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                        Player player = new Player(birthString, playerString, descriptionString, positionString, null);

                        FireBaseInsert f = new FireBaseInsert();
                        f.insertData(player);

                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.all_fields_mandatory), Toast.LENGTH_LONG).show();
                    }

                }else{


                    if (birthString != null && !birthString.isEmpty() && !birthString.equals("") && playerString != null && !playerString.isEmpty() && !playerString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && descriptionString != null && !descriptionString.isEmpty() && !descriptionString.equals("") && positionString != null && !positionString.isEmpty() && !positionString.equals("")) {


                        //resultado = playerDAO.dataInsert(DateUtils.formatDate(birthString, DateUtils.DATE_BR, DateUtils.DATE_DB), playerString, descriptionString, positionString, null);

                        // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                        Player player = new Player(birthString, playerString, descriptionString, positionString, null);

                        player.setId(playerVisualization.getId());


                        FireBaseInsert f = new FireBaseInsert();
                        f.updateData(player);


                        Intent intent = new Intent(RegisterCreatePlayerActivity.this, VisualizationPlayerActivity.class);
                        intent.putExtra(VisualizationPlayerActivity.PLAYER, player);

                        startActivity(intent);


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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {


        } else if (requestCode == REQUEST_GALERY && resultCode == RESULT_OK) {



        }
    }

    public void getCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_CAMERA);

    }

    public void getGalery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_image)), REQUEST_GALERY);

    }

    public void setPlayer(Player player) {

        birth.setText(player.getBirth());
        this.player.setText(player.getPlayer());
        description.setText(player.getDescription());
        position.setText(player.getPosition());


    }


}
