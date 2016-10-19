package com.example.alianza.pojo;

import java.util.Calendar;

/**
 * Created by andre on 09/10/16.
 */

public class Player {


    //private int id;
    private String birth;
    private String player;
    private String description;
    private String position;
    private String photo;

    public Player(String birth, String player, String description, String position, String photo){

        //this.id = id;
        this.birth = birth;
        this.player = player;
        this.description = description;
        this.position = position;
        this.photo = photo;

    }



    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String  photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "Player{" +
                "birth='" + birth + '\'' +
                ", player='" + player + '\'' +
                ", description='" + description + '\'' +
                ", position='" + position + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
