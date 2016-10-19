package com.example.alianza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alianza.R;

public class VisualizationNewsActivity extends AppCompatActivity {

    TextView textViewTitleNews;
    TextView textViewNews;
    TextView textViewAuthorNews;
    TextView textViewDateNews;

    public static final String ID = "news";

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

        setNews(getIntent().getIntExtra(ID, 0));

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

    public void setNews(int id) {

        /*NewsDAO newsDAO = new NewsDAO(getBaseContext());
        List<News> news = newsDAO.newsLoadByID(id);;

        textViewTitleNews.setText(news.get(0).getTitle());
        textViewNews.setText(news.get(0).getNews());
        textViewAuthorNews.setText(news.get(0).getAuthor());
        textViewDateNews.setText(DateUtils.formatDate(news.get(0).getDateNews(), DateUtils.DATE_DB, DateUtils.DATE_BR));
*/
    }
}
