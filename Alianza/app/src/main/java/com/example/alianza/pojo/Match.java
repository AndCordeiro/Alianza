package com.example.alianza.pojo;

/**
 * Created by andre on 09/10/16.
 */

public class Match {

    //private int id;
    private String dateOfMatch;
    private String hourOfMatch;
    private String opponentTeam;
    private String placeOfMatch;
    private String description;
    public static final String TEAM = "Alianza";


    public Match(String dateOfMatch, String hourOfMatch, String opponentTeam, String placeOfMatch, String description){

        //this.id = id;
        this.dateOfMatch = dateOfMatch;
        this.hourOfMatch = hourOfMatch;
        this.opponentTeam = opponentTeam;
        this.placeOfMatch = placeOfMatch;
        this.description = description;

    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/
    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public String getHourOfMatch() {
        return hourOfMatch;
    }

    public void setHourOfMatch(String hourOfMatch) {
        this.hourOfMatch = hourOfMatch;
    }

    public String getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public String getPlaceOfMatch() {
        return placeOfMatch;
    }

    public void setPlaceOfMatch(String placeOfMatch) {
        this.placeOfMatch = placeOfMatch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Match{" +
                "dateOfMatch='" + dateOfMatch + '\'' +
                ", hourOfMatch='" + hourOfMatch + '\'' +
                ", opponentTeam='" + opponentTeam + '\'' +
                ", placeOfMatch='" + placeOfMatch + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
