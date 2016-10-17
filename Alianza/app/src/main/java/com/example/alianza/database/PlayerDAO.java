package com.example.alianza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alianza.database.table.TablePlayer;
import com.example.alianza.pojo.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by andre on 09/10/16.
 */

public class PlayerDAO {


    private SQLiteDatabase db;
    private DBHelper dataBase;

    public PlayerDAO(Context context){

        dataBase = new DBHelper(context);
    }



    public String dataInsert(String birth, String player, String description, String position, File photo){

        ContentValues values;
        long result;

        db = dataBase.getWritableDatabase();
        values = new ContentValues();
        values.put(TablePlayer.BIRTH, birth);
        values.put(TablePlayer.PLAYER, player);
        values.put(TablePlayer.DESCRIPTION, description);
        values.put(TablePlayer.POSITION, position);
        values.put(TablePlayer.PHOTO, String.valueOf(photo));

        result = db.insert(TablePlayer.NAME, null, values);
        db.close();

        if (result ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public List<Player> playersLoad(){

        List<Player> players = new ArrayList<>();
        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TablePlayer.NAME, new String[]{"*"}, null, null, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                Player player = new Player();
                player.setId(cursor.getInt(cursor.getColumnIndex(TablePlayer.ID)));
                player.setPlayer(cursor.getString(cursor.getColumnIndex(TablePlayer.PLAYER)));
                player.setBirth((cursor.getString(cursor.getColumnIndex(TablePlayer.BIRTH))));
                player.setDescription(cursor.getString(cursor.getColumnIndex(TablePlayer.DESCRIPTION)));
                player.setPosition(cursor.getString(cursor.getColumnIndex(TablePlayer.POSITION)));
                player.setPhoto(cursor.getString(cursor.getColumnIndex(TablePlayer.PHOTO)));
                players.add(player);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();

        return players;
    }

    public List<Player> playersLoadByNamePlayer(String namePlayer){

        List<Player> players = new ArrayList<>();

        String where = TablePlayer.NAME + " LIKE ?";
        String[] whereArgs = {"%"+namePlayer+"%"};

        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TablePlayer.NAME, new String[]{"*"}, where, whereArgs, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Player player = new Player();
                player.setId(cursor.getInt(cursor.getColumnIndex(TablePlayer.ID)));
                player.setPlayer(cursor.getString(cursor.getColumnIndex(TablePlayer.PLAYER)));
                player.setBirth(cursor.getString(cursor.getColumnIndex(TablePlayer.BIRTH)));
                player.setDescription(cursor.getString(cursor.getColumnIndex(TablePlayer.DESCRIPTION)));
                player.setPosition(cursor.getString(cursor.getColumnIndex(TablePlayer.POSITION)));
                player.setPhoto(cursor.getString(cursor.getColumnIndex(TablePlayer.PHOTO)));
                players.add(player);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();
        return players;
    }

    public List<Player> playersLoadByID(int id){

        List<Player> players = new ArrayList<>();

        String where = TablePlayer.ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TablePlayer.NAME, new String[]{"*"}, where, whereArgs, null, null, null, null);


        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Player player = new Player();
                player.setId(cursor.getInt(cursor.getColumnIndex(TablePlayer.ID)));
                player.setPlayer(cursor.getString(cursor.getColumnIndex(TablePlayer.PLAYER)));
                player.setBirth(cursor.getString(cursor.getColumnIndex(TablePlayer.BIRTH)));
                player.setDescription(cursor.getString(cursor.getColumnIndex(TablePlayer.DESCRIPTION)));
                player.setPosition(cursor.getString(cursor.getColumnIndex(TablePlayer.POSITION)));
                player.setPhoto(cursor.getString(cursor.getColumnIndex(TablePlayer.PHOTO)));
                players.add(player);
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();
        return players;
    }

    public void recordsAlter(int id, String birth, String player, String description, String position, String photo){

        ContentValues values;
        String where;

        db = dataBase.getWritableDatabase();

        where = TablePlayer.ID + "=" + id;

        values = new ContentValues();
        values.put(TablePlayer.BIRTH, birth);
        values.put(TablePlayer.PLAYER, player);
        values.put(TablePlayer.DESCRIPTION, description);
        values.put(TablePlayer.POSITION, position);
        values.put(TablePlayer.PHOTO, photo);

        db.update(TablePlayer.NAME,values,where,null);
        db.close();


    }
    public void recordsDelete(int id){

        String where = TablePlayer.ID + "=" + id;
        db = dataBase.getReadableDatabase();
        db.delete(TablePlayer.NAME,where,null);
        db.close();
    }

}
