package com.example.alianza.entities;

import java.util.Calendar;

/**
 * Created by andre on 09/10/16.
 */

public class Match {

    private int id;
    private Calendar dateOfMatch;
    private String hourOfMatch;
    private String opponentTeam;
    private String placeOfMatch;
    private String description;
    private String position;
    public final String TEAM = "Alianza";


    public Match(int id, Calendar dateOfMatch, String hourOfMatch, String opponentTeam, String placeOfMatch, String description, String position){

        this.id = id;
        this.dateOfMatch = dateOfMatch;
        this.hourOfMatch = hourOfMatch;
        this.opponentTeam = opponentTeam;
        this.placeOfMatch = placeOfMatch;
        this.description = description;
        this.position = position;

    }




}
