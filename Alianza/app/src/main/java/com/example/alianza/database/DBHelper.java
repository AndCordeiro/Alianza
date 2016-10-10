package com.example.alianza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alianza.database.table.TableMatch;
import com.example.alianza.database.table.TableNews;
import com.example.alianza.database.table.TablePlayer;

/**
 * Created by andre on 10/10/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "alianza.db";

    public static final int VERSION = 1;



    public DBHelper(Context context) {

        super(context, DBNAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TableNews.createStatement());
        db.execSQL(TableMatch.createStatement());
        db.execSQL(TablePlayer.createStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
