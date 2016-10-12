package com.example.alianza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alianza.database.table.TableNews;
import com.example.alianza.entities.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 09/10/16.
 */

public class NewsDAO {



    private SQLiteDatabase db;
    private DBHelper banco;

    public NewsDAO(Context context){

        banco = new DBHelper(context);
    }




    public String dataInsert(String title, String news, String author, String dateNews){

        ContentValues values;
        long result;

        db = banco.getWritableDatabase();
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
        db = banco.getReadableDatabase();
        Cursor cursor = db.query(TableNews.NAME, new String[]{"*"}, null, null, null, null, null, null);


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

        String where = TableNews.NAME + " LIKE ?";
        String[] whereArgs = {"%"+newsTitle+"%"};

        db = banco.getReadableDatabase();
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

        db = banco.getReadableDatabase();
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

    public void recordsLoad(int id, String title, String news, String author){

        ContentValues values;
        String where;

        db = banco.getWritableDatabase();

        where = TableNews.ID + "=" + id;

        values = new ContentValues();
        values.put(TableNews.TITLE, title);
        values.put(TableNews.NEWS, news);
        values.put(TableNews.AUTHOR, author);
        values.put(TableNews.DATENEWS, author);

        db.update(TableNews.NAME,values,where,null);
        db.close();


    }
    public void recordsDelete(int id){

        String where = TableNews.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(TableNews.NAME,where,null);
        db.close();
    }


}
