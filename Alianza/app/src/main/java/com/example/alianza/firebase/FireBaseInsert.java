package com.example.alianza.firebase;

import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by andre on 19/10/16.
 */

public class FireBaseInsert {


    public static final String NAME_NEWS = "news";
    public static final String NAME_PLAYER = "player";
    public static final String NAME_MATCH = "match";


    public void insertData(News news) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(NAME_NEWS);


        myRef.push().setValue(news);

    }

    public void insertData(Player player) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(NAME_PLAYER);


        myRef.push().setValue(player);

    }

    public void insertData(Match match) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(NAME_MATCH);


        myRef.push().setValue(match);

    }


}
