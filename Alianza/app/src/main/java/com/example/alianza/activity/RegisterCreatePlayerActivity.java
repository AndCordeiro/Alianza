package com.example.alianza.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.alianza.pojo.Player;
import com.example.alianza.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterCreatePlayerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText birth;
    private EditText player;
    private EditText description;
    private EditText position;
    private ImageView photo;
    private int id = 0;
    Player playerVisualization;
    private String pathPhoto;
    private String mCurrentPhotoPath;


    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_create_player);

        //ui control
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

                String birthString = birth.getText().toString();
                String playerString = player.getText().toString();
                String descriptionString = description.getText().toString();
                String positionString = position.getText().toString();


                if (id != 1) {

                    if (!birthString.isEmpty() && !birthString.equals("") && !playerString.isEmpty() && !playerString.equals("") && !descriptionString.isEmpty() && !descriptionString.equals("") && !descriptionString.isEmpty() && !descriptionString.equals("") && !positionString.isEmpty() && !positionString.equals("") && pathPhoto != null) {

                        FireBaseInsert f = new FireBaseInsert();

                        Player player = new Player(birthString, playerString, descriptionString, positionString, null);

                        f.insertData(player);
                        f.setPhotos(pathPhoto, player);

                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.all_fields_mandatory), Toast.LENGTH_LONG).show();
                    }

                } else {


                    if (!birthString.isEmpty() && !birthString.equals("") && !playerString.isEmpty() && !playerString.equals("") && !descriptionString.isEmpty() && !descriptionString.equals("") && !descriptionString.isEmpty() && !descriptionString.equals("") && !positionString.isEmpty() && !positionString.equals("")) {

                        FireBaseInsert f = new FireBaseInsert();

                        Player player = new Player(birthString, playerString, descriptionString, positionString, playerVisualization.getPhoto());

                        player.setId(playerVisualization.getId());

                        f.updateData(player);

                        if (pathPhoto != null) {

                            f.setPhotos(pathPhoto, player);


                        }


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

        if (resultCode == RESULT_CANCELED) {

            pathPhoto = null;
        }

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {

            Picasso.with(this).load(Uri.parse(mCurrentPhotoPath)).fit().centerCrop().into(photo);


        } else if (requestCode == REQUEST_GALERY && resultCode == RESULT_OK) {

            try {

                Picasso.with(this).load(data.getData()).fit().centerCrop().into(photo);
                pathPhoto = new File(getRealPathFromURI(data.getData())).getAbsolutePath();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void getCamera() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("TAG", "IOException");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            }
        }

    }

    public void getGalery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_image)), REQUEST_GALERY);

    }

    public void setPlayer(Player player) {

        birth.setText(player.getBirth());
        this.player.setText(player.getPlayer());
        description.setText(player.getDescription());
        position.setText(player.getPosition());
        Picasso.with(this).load(player.getPhoto()).fit().error(getResources().getDrawable(R.drawable.perfil_sombra)).placeholder(getResources().getDrawable(R.drawable.perfil_sombra)).centerCrop().into(photo);

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        pathPhoto = image.getAbsolutePath();
        return image;
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
