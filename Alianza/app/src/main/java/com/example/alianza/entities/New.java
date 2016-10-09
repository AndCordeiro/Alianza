package com.example.alianza.entities;

/**
 * Created by andre on 09/10/16.
 */

public class New {

    private int id;
    private String title;
    private String news;


    public New(int id, String title, String news){

        this.id = id;
        this.title = title;
        this.news = news;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }


    @Override
    public String toString() {
        return "New{" +
                "title='" + title + '\'' +
                ", news='" + news + '\'' +
                '}';
    }
}
