package com.example.alianza.pojo;

import java.io.Serializable;

/**
 * Created by andre on 31/10/16.
 */

public class User implements Serializable{

    private String id;
    private String name;
    private String key;


    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
