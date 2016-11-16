package com.example.alianza.activity;

import android.content.Intent;
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

public class RegisterCreateNewsActivity extends AppCompatActivity {


    private EditText title;
    private EditText author;
    private EditText edNews;
    private int id = 0;
    News newsVisualization;


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

        Intent intent = getIntent();
        newsVisualization = (News) intent.getSerializableExtra(VisualizationNewsActivity.NEWS);
        if (newsVisualization != null) {

            id = 1;

            setNews(newsVisualization);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.check);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                String titleString = title.getText().toString();
                String authorString = author.getText().toString();
                String newsString = edNews.getText().toString();

                if (id != 1) {

                    if (titleString != null && !titleString.isEmpty() && !titleString.equals("") && authorString != null && !authorString.isEmpty() && !authorString.equals("") && newsString != null && !newsString.isEmpty() && !newsString.equals("")) {

                        News news = new News(titleString, newsString, authorString, DateUtils.getDate());

                        FireBaseInsert f = new FireBaseInsert();
                        f.insertData(news);

                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.all_fields_mandatory), Toast.LENGTH_LONG).show();
                    }


                }else{

                    if (titleString != null && !titleString.isEmpty() && !titleString.equals("") && authorString != null && !authorString.isEmpty() && !authorString.equals("") && newsString != null && !newsString.isEmpty() && !newsString.equals("")) {

                        News news = new News(titleString, newsString, authorString, DateUtils.getDate());

                        news.setId(newsVisualization.getId());

                        FireBaseInsert f = new FireBaseInsert();
                        f.updateData(news);

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


    public void setNews(News news) {

        title.setText(news.getNews());
        author.setText(news.getAuthor());
        edNews.setText(news.getNews());

    }


}
