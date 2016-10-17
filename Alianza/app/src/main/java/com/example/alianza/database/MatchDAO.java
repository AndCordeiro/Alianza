package com.example.alianza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alianza.database.table.TableMatch;
import com.example.alianza.database.table.TableNews;
import com.example.alianza.pojo.Match;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by andre on 09/10/16.
 */

public class MatchDAO {

    private SQLiteDatabase db;
    private DBHelper dataBase;

    public MatchDAO(Context context){

        dataBase = new DBHelper(context);
    }


    public String dataInsert(String dateOfMatch,String hourOfMatch, String team, String opponent, String description, String placeOfMatch){

        ContentValues values;
        long result;

        db = dataBase.getWritableDatabase();
        values = new ContentValues();
        values.put(TableMatch.DATEOFMATCH, dateOfMatch);
        values.put(TableMatch.HOUROFMATCH, hourOfMatch);
        values.put(TableMatch.TEAM, team);
        values.put(TableMatch.OPPONENTTEAM, opponent);
        values.put(TableMatch.DESCRIPTION, description);
        values.put(TableMatch.PLACEOFMATCH, placeOfMatch);


        result = db.insert(TableMatch.NAME, null, values);
        db.close();

        if (result ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }


    public List<Match> newsLoad(){

        List<Match> matches = new ArrayList<>();
        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TableMatch.NAME, new String[]{"*"}, null, null, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                Match match = new Match();
                match.setId(cursor.getInt(cursor.getColumnIndex(TableMatch.ID)));
                match.setDateOfMatch(cursor.getString(cursor.getColumnIndex(TableMatch.DATEOFMATCH)));
                match.setHourOfMatch(cursor.getString(cursor.getColumnIndex(TableMatch.HOUROFMATCH)));
                match.setOpponentTeam(cursor.getString(cursor.getColumnIndex(TableMatch.OPPONENTTEAM)));
                match.setDescription(cursor.getString(cursor.getColumnIndex(TableMatch.DESCRIPTION)));
                match.setPlaceOfMatch(cursor.getString(cursor.getColumnIndex(TableMatch.PLACEOFMATCH)));
                matches.add(match);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();

        return matches;
    }


    public List<Match> newsLoadByID(int id){

        List<Match> matches = new ArrayList<>();

        String where = TableMatch.ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TableMatch.NAME, new String[]{"*"}, where, whereArgs, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Match match = new Match();
                match.setId(cursor.getInt(cursor.getColumnIndex(TableMatch.ID)));
                match.setDateOfMatch(cursor.getString(cursor.getColumnIndex(TableMatch.DATEOFMATCH)));
                match.setHourOfMatch(cursor.getString(cursor.getColumnIndex(TableMatch.HOUROFMATCH)));
                match.setOpponentTeam(cursor.getString(cursor.getColumnIndex(TableMatch.OPPONENTTEAM)));
                match.setDescription(cursor.getString(cursor.getColumnIndex(TableMatch.DESCRIPTION)));
                match.setPlaceOfMatch(cursor.getString(cursor.getColumnIndex(TableMatch.PLACEOFMATCH)));
                matches.add(match);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();
        return matches;
    }

    public void recordsLoad(int id, String dateOfMatch,String hourOfMatch, String opponent, String description, String placeOfMatch){

        ContentValues values;
        String where;

        db = dataBase.getWritableDatabase();

        where = TableNews.ID + "=" + id;

        values = new ContentValues();
        values.put(TableMatch.DATEOFMATCH, dateOfMatch);
        values.put(TableMatch.HOUROFMATCH, hourOfMatch);
        values.put(TableMatch.OPPONENTTEAM, opponent);
        values.put(TableMatch.DESCRIPTION, description);
        values.put(TableMatch.PLACEOFMATCH, placeOfMatch);

        db.update(TableMatch.NAME,values,where,null);
        db.close();


    }
    public void recordsDelete(int id){

        String where = TableMatch.ID + "=" + id;
        db = dataBase.getReadableDatabase();
        db.delete(TableMatch.NAME,where,null);
        db.close();
    }



}
