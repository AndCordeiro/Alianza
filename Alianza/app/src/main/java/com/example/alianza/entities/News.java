package com.example.alianza.entities;

/**
 * Created by andre on 09/10/16.
 */

public class News {

    private int id;
    private String title;
    private String news;
    private String author;
    private String dateNews;


    /*public News(int id, String title, String news, String author){

        this.id = id;
        this.title = title;
        this.news = news;
        this.author = author;

    }*/


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
                "title='" + title + '\'' +
                ", news='" + news + '\'' +
                ", author='" + author + '\'' +
                ", dateNews='" + dateNews + '\'' +
                '}';
    }
}
