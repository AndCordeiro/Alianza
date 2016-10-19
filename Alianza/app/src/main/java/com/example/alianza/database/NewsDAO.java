package com.example.alianza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alianza.database.table.TableNews;
import com.example.alianza.pojo.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 09/10/16.
 */
/*
public class NewsDAO {



    private SQLiteDatabase db;
    private DBHelper dataBase;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myNews = database.getReference(TableNews.NEWS);
    DatabaseReference myTitle = database.getReference(TableNews.TITLE);
    DatabaseReference myDateNews = database.getReference(TableNews.DATENEWS);
    DatabaseReference myAuthor = database.getReference(TableNews.AUTHOR);

    public NewsDAO(Context context){

        dataBase = new DBHelper(context);
    }




    public String dataInsert(String title, String news, String author, String dateNews){

        ContentValues values;
        long result;

        db = dataBase.getWritableDatabase();
        values = new ContentValues();
        values.put(TableNews.TITLE, title);
        values.put(TableNews.NEWS, news);
        values.put(TableNews.AUTHOR, author);
        values.put(TableNews.DATENEWS, dateNews);


        result = db.insert(TableNews.NAME, null, values);
        db.close();

        if (result ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public List<News> newsLoad(){

        List<News> newsList = new ArrayList<>();
        db = dataBase.getReadableDatabase();
        String orderBy = TableNews.DATENEWS + " DESC";
        Cursor cursor = db.query(TableNews.NAME, new String[]{"*"}, null, null, null, null, orderBy, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(TableNews.ID)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(TableNews.TITLE)));
                news.setNews((cursor.getString(cursor.getColumnIndex(TableNews.NEWS))));
                news.setAuthor(cursor.getString(cursor.getColumnIndex(TableNews.AUTHOR)));
                news.setDateNews(cursor.getString(cursor.getColumnIndex(TableNews.DATENEWS)));
                newsList.add(news);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();


        return newsList;
    }

    public List<News> newsLoadByTitle(String newsTitle){

        List<News> newsList = new ArrayList<>();

        String where = TableNews.TITLE + " LIKE ?";
        String[] whereArgs = {"%"+newsTitle+"%"};

        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TableNews.NAME, new String[]{"*"}, where, whereArgs, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(TableNews.ID)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(TableNews.TITLE)));
                news.setNews((cursor.getString(cursor.getColumnIndex(TableNews.NEWS))));
                news.setAuthor(cursor.getString(cursor.getColumnIndex(TableNews.AUTHOR)));
                news.setDateNews(cursor.getString(cursor.getColumnIndex(TableNews.DATENEWS)));
                newsList.add(news);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();
        return newsList;
    }

    public List<News> newsLoadByID(int id){

        List<News> newsList = new ArrayList<>();

        String where = TableNews.ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TableNews.NAME, new String[]{"*"}, where, whereArgs, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(TableNews.ID)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(TableNews.TITLE)));
                news.setNews((cursor.getString(cursor.getColumnIndex(TableNews.NEWS))));
                news.setAuthor(cursor.getString(cursor.getColumnIndex(TableNews.AUTHOR)));
                news.setDateNews(cursor.getString(cursor.getColumnIndex(TableNews.DATENEWS)));
                newsList.add(news);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();
        return newsList;
    }

    public void dataAlter(int id, String title, String news, String author){

        ContentValues values;
        String where;

        db = dataBase.getWritableDatabase();

        where = TableNews.ID + "=" + id;

        values = new ContentValues();
        values.put(TableNews.TITLE, title);
        values.put(TableNews.NEWS, news);
        values.put(TableNews.AUTHOR, author);
        values.put(TableNews.DATENEWS, author);

        db.update(TableNews.NAME,values,where,null);
        db.close();


    }
    public void dataDelete(int id){

        String where = TableNews.ID + "=" + id;
        db = dataBase.getReadableDatabase();
        db.delete(TableNews.NAME,where,null);
        db.close();
    }

}
*/