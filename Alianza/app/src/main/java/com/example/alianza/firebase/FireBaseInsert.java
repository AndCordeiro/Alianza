package com.example.alianza.firebase;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;
import com.example.alianza.pojo.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
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
    StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/alianza-43035.appspot.com/o/");


    public void insertData(News news) {


        DatabaseReference myRef = database.getReference(NAME_NEWS).push();

        news.setId(myRef.getKey());
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


        // File or Blob
        Uri file = Uri.fromFile(new File(path));

        // Create the file metadata
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpeg")
                .build();

        // Upload file and metadata to the path 'images/mountains.jpg'
        UploadTask uploadTask = storageRef.child(NAME_IMAGES + player.getPlayer() + NAME_JPG).putFile(file, metadata);

        // Listen for state changes, errors, and completion of the upload.
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                System.out.println("Upload is " + progress + "% done");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("Upload is paused");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle successful uploads on complete

                Uri downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
                player.setPhoto(downloadUrl.toString());
                updatePhoto(player);
            }
        });



    }


    public void deletePhoto(Player player){

        StorageReference desertRef = storageRef.child(NAME_IMAGES + player.getPlayer() + NAME_JPG);

        desertRef.delete().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });


    }




    public void updatePhoto(Player player) {


        DatabaseReference myRef = database.getReference(NAME_PLAYER).child(player.getId());

        Map<String, Object> playerMap = new HashMap<String, Object>();
        playerMap.put("photo", player.getPhoto());

        myRef.updateChildren(playerMap);

    }

}
