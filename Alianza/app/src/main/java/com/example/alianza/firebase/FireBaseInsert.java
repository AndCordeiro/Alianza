package com.example.alianza.firebase;

import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andre on 19/10/16.
 */

public class FireBaseInsert {


    public static final String NAME_NEWS = "news";
    public static final String NAME_PLAYER = "player";
    public static final String NAME_MATCH = "match";
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public void insertData(News news) {


        DatabaseReference myRef = database.getReference(NAME_NEWS);


        myRef.push().setValue(news);

    }

    public void insertData(Player player) {


        DatabaseReference myRef = database.getReference(NAME_PLAYER);


        myRef.push().setValue(player);

    }

    public void insertData(Match match) {


        DatabaseReference myRef = database.getReference(NAME_MATCH);


        myRef.push().setValue(match);

    }

    public void updateData(Match match) {


        DatabaseReference myRef = database.getReference(NAME_MATCH).child(match.getId());

        Map<String,Object> matchMap = new HashMap<String,Object>();
        matchMap.put("dateOfMatch", match.getDateOfMatch());
        matchMap.put("hourOfMatch", match.getHourOfMatch());
        matchMap.put("opponentTeam", match.getOpponentTeam());
        matchMap.put("placeOfMatch", match.getPlaceOfMatch());
        matchMap.put("description", match.getDescription());

        myRef.updateChildren(matchMap);


    }

    public void updateData(News news) {


        DatabaseReference myRef = database.getReference(NAME_NEWS).child(news.getId());

        Map<String,Object> newsMap = new HashMap<String,Object>();
        newsMap.put("title", news.getTitle());
        newsMap.put("news", news.getNews());
        newsMap.put("author", news.getAuthor());
        newsMap.put("dateNews", news.getDateNews());


        myRef.updateChildren(newsMap);


    }

    public void updateData(Player player) {


        DatabaseReference myRef = database.getReference(NAME_PLAYER).child(player.getId());

        Map<String,Object> playerMap = new HashMap<String,Object>();
        playerMap.put("birth", player.getBirth());
        playerMap.put("player", player.getPlayer());
        playerMap.put("description", player.getDescription());
        playerMap.put("position", player.getPosition());
        //playerMap.put("photo", player.getPhoto());


        myRef.updateChildren(playerMap);


    }

}
