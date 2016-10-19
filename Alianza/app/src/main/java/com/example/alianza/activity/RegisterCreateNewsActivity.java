package com.example.alianza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alianza.R;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.News;
import com.example.alianza.utils.DateUtils;

import java.util.Calendar;

public class RegisterCreateNewsActivity extends AppCompatActivity {


    private EditText title;
    private EditText author;
    private EditText edNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_create_news);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (EditText) findViewById(R.id.editTextTitleNews);
        author = (EditText) findViewById((R.id.editTextAuthorNews));
        edNews = (EditText) findViewById(R.id.editTextNews);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.check);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

               // NewsDAO newsDAO = new NewsDAO(getBaseContext());


                String titleString = title.getText().toString();
                String authorString = author.getText().toString();
                String newsString = edNews.getText().toString();

                //String resultado;



                if (titleString != null && !titleString.isEmpty() && !titleString.equals("") && authorString != null && !authorString.isEmpty() && !authorString.equals("") && newsString != null && !newsString.isEmpty() && !newsString.equals("")) {


                  //resultado = newsDAO.dataInsert(titleString, newsString, authorString, DateUtils.getDate(DateUtils.DATETIME_DB));

                    News news = new News(titleString, newsString, authorString, DateUtils.getDate("dd/MM/yyyy"));

                    FireBaseInsert f = new FireBaseInsert();
                    f.insertData(news);

                   // Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

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
