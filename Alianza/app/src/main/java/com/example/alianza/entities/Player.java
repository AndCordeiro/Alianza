package com.example.alianza.entities;

import java.util.Calendar;

/**
 * Created by andre on 09/10/16.
 */

public class Player {

    private int id;
    private Calendar birth;
    private String name;
    private String description;


    public Player(int id, Calendar birth, String name, String description){

        this.id = id;
        this.birth = birth;
        this.name = name;
        this.description = description;

    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
