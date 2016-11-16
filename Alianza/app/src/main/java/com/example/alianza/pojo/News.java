package com.example.alianza.pojo;

import java.io.Serializable;

/**
 * Created by andre on 09/10/16.
 */

public class News  implements Serializable {

    private String id;
    private String title;
    private String news;
    private String author;
    private String dateNews;


    public News(String title, String news, String author, String dateNews){

        this.title = title;
        this.news = news;
        this.author = author;
        this.dateNews = dateNews;

    }

    public News(){


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateNews() {
        return dateNews;
    }

    public void setDateNews(String dateNews) {
        this.dateNews = dateNews;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", news='" + news + '\'' +
                ", author='" + author + '\'' +
                ", dateNews='" + dateNews + '\'' +
                '}';
    }
}
