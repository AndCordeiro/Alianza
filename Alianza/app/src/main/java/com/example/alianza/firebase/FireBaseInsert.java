package com.example.alianza.firebase;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;
import com.example.alianza.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andre on 19/10/16.
 */

public class FireBaseInsert {


    public static final String NAME_NEWS = "news";
    public static final String NAME_PLAYER = "player";
    public static final String NAME_MATCH = "match";
    public static final String NAME_USER = "user";
    public static final String ADMIN = "admin";
    public static final String REQUEST_ADMIN = "request admin";
    public static final String NAME_IMAGES = "images/";
    public static final String NAME_JPG = ".jpg";

    int id = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String url;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRefUpload = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/alianza-43035.appspot.com/o/");
    StorageReference storageRefDownload = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/alianza-43035.appspot.com/o/");


    public void insertData(News news) {


        DatabaseReference myRef = database.getReference(NAME_NEWS).push();


        myRef.setValue(news);

    }

    public void insertData(Player player) {


        DatabaseReference myRef = database.getReference(NAME_PLAYER).push();

        player.setId(myRef.getKey());
        myRef.setValue(player);


    }

    public void insertData(Match match) {


        DatabaseReference myRef = database.getReference(NAME_MATCH).push();

        match.setId(myRef.getKey());
        myRef.setValue(match);

    }

    public void updateData(Match match) {


        DatabaseReference myRef = database.getReference(NAME_MATCH).child(match.getId());

        Map<String, Object> matchMap = new HashMap<String, Object>();
        matchMap.put("dateOfMatch", match.getDateOfMatch());
        matchMap.put("hourOfMatch", match.getHourOfMatch());
        matchMap.put("opponentTeam", match.getOpponentTeam());
        matchMap.put("placeOfMatch", match.getPlaceOfMatch());
        matchMap.put("description", match.getDescription());

        myRef.updateChildren(matchMap);


    }

    public void updateData(News news) {


        DatabaseReference myRef = database.getReference(NAME_NEWS).child(news.getId());

        Map<String, Object> newsMap = new HashMap<String, Object>();
        newsMap.put("title", news.getTitle());
        newsMap.put("news", news.getNews());
        newsMap.put("author", news.getAuthor());
        newsMap.put("dateNews", news.getDateNews());


        myRef.updateChildren(newsMap);


    }

    public void updateData(Player player) {


        DatabaseReference myRef = database.getReference(NAME_PLAYER).child(player.getId());

        Map<String, Object> playerMap = new HashMap<String, Object>();
        playerMap.put("birth", player.getBirth());
        playerMap.put("player", player.getPlayer());
        playerMap.put("description", player.getDescription());
        playerMap.put("position", player.getPosition());


        myRef.updateChildren(playerMap);


    }


    public void insertAdmin(User user) {


        DatabaseReference myRef = database.getReference(ADMIN);

        myRef.push().setValue(user);

    }

    public void insertRequestAdmin(User user) {


        DatabaseReference myRef = database.getReference(REQUEST_ADMIN);


        myRef.push().setValue(user);

    }

    public void setPhotos(String path, final Player player) {

        Uri file = Uri.fromFile(new File(path));
        StorageReference imageRef = storageRefUpload.child(NAME_IMAGES + player.getPlayer() + NAME_JPG);
        UploadTask uploadTask = imageRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                player.setPhoto(taskSnapshot.getDownloadUrl().toString());
                updatePhoto(player);

            }
        });

    }


    public void updatePhoto(Player player) {


        DatabaseReference myRef = database.getReference(NAME_PLAYER).child(player.getId());

        Map<String, Object> playerMap = new HashMap<String, Object>();
        playerMap.put("photo", player.getPhoto());

        Log.e("TAG", "updatePhoto: " + player.getPhoto() );
        myRef.updateChildren(playerMap);

    }

}
