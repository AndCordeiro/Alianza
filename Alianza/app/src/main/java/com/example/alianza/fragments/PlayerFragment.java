package com.example.alianza.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alianza.R;
import com.example.alianza.activity.MainActivity;
import com.example.alianza.activity.VisualizationPlayerActivity;
import com.example.alianza.adapters.PlayerAdapter;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.Player;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 09/10/16.
 */

public class PlayerFragment extends Fragment implements PlayerAdapter.OnClickListener, PlayerAdapter.OnLongClickListener, MainActivity.OnClickSearchPlayer {

    RecyclerView recyclerView;


    private PlayerAdapter playerAdapter;
    private DatabaseReference databaseReference;
    private List<Player> playerList;
    private List<Player> searchList;
    private String key = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnClickSearchPlayer(this);

        playerList = new ArrayList<>();
        searchList = new ArrayList<>();

        databaseReference.child(FireBaseInsert.NAME_PLAYER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (!key.equals(dataSnapshot.getKey())) {

                    getAllPlayer(dataSnapshot);

                }

                key = dataSnapshot.getKey();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                for (int i = 0; i < playerList.size(); i++) {

                    if (playerList.get(i).getId().equals(dataSnapshot.getKey())) {

                        playerList.get(i).setBirth(dataSnapshot.child("birth").getValue().toString());
                        playerList.get(i).setPlayer(dataSnapshot.child("player").getValue().toString());
                        playerList.get(i).setDescription(dataSnapshot.child("description").getValue().toString());
                        playerList.get(i).setPosition(dataSnapshot.child("position").getValue().toString());
                        playerList.get(i).setPhoto(dataSnapshot.child("photo").getValue().toString());


                        getAllPlayer();
                    }

                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                for (int i = 0; i < playerList.size(); i++) {

                    if (playerList.get(i).getId().equals(dataSnapshot.getKey())) {

                        playerList.remove(i);
                    }

                }
                getAllPlayer();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                getAllPlayer();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;

    }


    private void getAllPlayer(DataSnapshot dataSnapshot) {


        Player player = dataSnapshot.getValue(Player.class);
        playerList.add(player);
        playerAdapter = new PlayerAdapter(getContext(), playerList);
        playerAdapter.setOnClickListener(this);
        playerAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(playerAdapter);


    }

    private void getAllPlayer() {

        playerAdapter = new PlayerAdapter(getContext(), playerList);
        playerAdapter.setOnClickListener(this);
        playerAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(playerAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onItemClickListener(Player player) {

        Intent intent = new Intent(this.getActivity(), VisualizationPlayerActivity.class);
        intent.putExtra(VisualizationPlayerActivity.PLAYER, player);
        startActivity(intent);

    }


    @Override
    public boolean onLongItemClickListener(final Player player) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                FireBaseInsert fireBaseInsert = new FireBaseInsert();
                fireBaseInsert.deletePhoto(player);
                databaseReference.child(FireBaseInsert.NAME_PLAYER).child(player.getId()).removeValue();

            }
        })
                .setNegativeButton(getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setTitle(getResources().getString(R.string.attention))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(getResources().getString(R.string.delete))
                .show();


        return false;
    }

    @Override
    public void onItemClickSearch(final String query) {

        if (query.length() >= 3) {

            databaseReference.child(FireBaseInsert.NAME_PLAYER).orderByChild("player").startAt(query).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    getSearchPlayer(dataSnapshot);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            searchList.clear();
            getAllPlayer();

        }

    }


    private void getSearchPlayer(DataSnapshot dataSnapshot) {


        Player player = dataSnapshot.getValue(Player.class);
        searchList.add(player);
        playerAdapter = new PlayerAdapter(getContext(), searchList);
        playerAdapter.setOnClickListener(this);
        playerAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(playerAdapter);

    }

}
