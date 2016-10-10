package com.example.alianza.database.table;

import java.util.Calendar;

/**
 * Created by andre on 10/10/16.
 */

public class TableMatch {

    //Table Match

    public static final String NAME = "player";
    public static final String ID = "_id";
    public static final String DATEOFMATCH = "date";
    public static final String HOUROFMATCH = "hour";
    public static final String TEAM = "team";
    public static final String OPPONENTTEAM = "opponent";
    public static final String PLACEOFMATCH = "place";
    public static final String DESCRIPTION = "description";



    public static final String createStatement(){

        String sql = "CREATE TABLE "+ NAME +"("
                + ID + " integer primary key autoincrement,"
                + DATEOFMATCH + " text,"
                + HOUROFMATCH + " text,"
                + TEAM + " text,"
                + OPPONENTTEAM + " text,"
                + PLACEOFMATCH + " text"
                + DESCRIPTION + " text"
                +")";

        return sql;
    }



}
