package com.example.alianza.database.table;

/**
 * Created by andre on 10/10/16.
 */

public class TablePlayer {

    //Table Team

    public static final String NAME = "player";
    public static final String ID = "_id";
    public static final String PLAYER = "name";
    public static final String BIRTH = "birth";
    public static final String DESCRIPTION = "description";
    public static final String POSITION = "position";
    public static final String PHOTO = "photo";


    public static final String createStatement(){

        String sql = "CREATE TABLE "+ NAME +"("
                + ID + " integer primary key autoincrement,"
                + PLAYER + " text,"
                + BIRTH + " date,"
                + DESCRIPTION + " text,"
                + POSITION + " text,"
                + PHOTO + " text"
                +")";

        return sql;
    }

}
