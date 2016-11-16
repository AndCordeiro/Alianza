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
import com.example.alianza.pojo.News;
import com.example.alianza.utils.DateUtils;

public class VisualizationNewsActivity extends AppCompatActivity {

    TextView textViewTitleNews;
    TextView textViewNews;
    TextView textViewAuthorNews;
    TextView textViewDateNews;
    News news;

    public static final String NEWS = "news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization_news);


        // Adding Toolbar to screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textViewTitleNews = (TextView) findViewById(R.id.textViewTitleNews);
        textViewNews = (TextView) findViewById(R.id.textViewNews);
        textViewAuthorNews = (TextView) findViewById(R.id.textViewAuthorNews);
        textViewDateNews = (TextView) findViewById(R.id.textViewDateNews);


        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra(NEWS);


        setNews(news);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.edit);

        myActionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (MainActivity.isAdmin) {

                    Intent intent = new Intent(VisualizationNewsActivity.this, RegisterCreateNewsActivity.class);
                    intent.putExtra(VisualizationNewsActivity.NEWS, news);

                    startActivity(intent);

                    finish();

                } else {

                    Snackbar.make(findViewById(R.id.visualizationNews_content), getResources().getString(R.string.not_permission), Snackbar.LENGTH_LONG)
                            .show();

                }


                return false;
            }
        });


        return true;
    }

    public void setNews(News news) {

        textViewTitleNews.setText(news.getTitle());
        textViewNews.setText(news.getNews());
        textViewAuthorNews.setText(news.getAuthor());
        textViewDateNews.setText(getResources().getConfiguration().locale.getLanguage().equals("pt") ? news.getDateNews() : DateUtils.formatDate(news.getDateNews(), DateUtils.DATE_BR, DateUtils.DATE_USA));

    }
}
