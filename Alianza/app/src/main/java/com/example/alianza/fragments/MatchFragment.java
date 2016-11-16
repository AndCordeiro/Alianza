package com.example.alianza.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alianza.R;
import com.example.alianza.activity.MainActivity;
import com.example.alianza.activity.VisualizationMatchActivity;
import com.example.alianza.adapters.MatchAdapter;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.Match;
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

public class MatchFragment extends Fragment implements MatchAdapter.OnClickListener, MatchAdapter.OnLongClickListener, MainActivity.OnClickSearchMatch {

    private final String TAG = this.getClass().getSimpleName();
    RecyclerView recyclerView;
    private MatchAdapter matchAdapter;
    private DatabaseReference databaseReference;
    private List<Match> matchList;
    private List<Match> searchList;
    private String key = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnClickSearchMatch(this);

        matchList = new ArrayList<>();
        searchList = new ArrayList<>();

        databaseReference.child(FireBaseInsert.NAME_MATCH).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(matchList.size() < 1){

                    getAllMatch(dataSnapshot);

                }else{

                    if (!key.equals(dataSnapshot.getKey())) {

                        getAllMatch(dataSnapshot);

                    }

                }

                key = dataSnapshot.getKey();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                for (int i = 0; i < matchList.size(); i++) {

                    if (matchList.get(i).getId().equals(dataSnapshot.getKey())) {

                        matchList.get(i).setHourOfMatch(dataSnapshot.child("hourOfMatch").getValue().toString());
                        matchList.get(i).setDateOfMatch(dataSnapshot.child("dateOfMatch").getValue().toString());
                        matchList.get(i).setOpponentTeam(dataSnapshot.child("opponentTeam").getValue().toString());
                        matchList.get(i).setPlaceOfMatch(dataSnapshot.child("placeOfMatch").getValue().toString());
                        matchList.get(i).setDescription(dataSnapshot.child("description").getValue().toString());

                        getAllMatch();
                    }

                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                for (int i = 0; i < matchList.size(); i++) {

                    if (matchList.get(i).getId().equals(dataSnapshot.getKey())) {

                        matchList.remove(i);
                    }

                }
                getAllMatch();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                getAllMatch();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;
    }

    private void getAllMatch(DataSnapshot dataSnapshot) {


        Match match = dataSnapshot.getValue(Match.class);
        matchList.add(match);
        matchAdapter = new MatchAdapter(getContext(), matchList);
        matchAdapter.setOnClickListener(this);
        matchAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(matchAdapter);
    }


    private void getAllMatch() {

        matchAdapter = new MatchAdapter(getContext(), matchList);
        matchAdapter.setOnClickListener(this);
        matchAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(matchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onItemClickListener(Match match) {

        Intent intent = new Intent(this.getActivity(), VisualizationMatchActivity.class);
        intent.putExtra(VisualizationMatchActivity.MATCH, match);
        startActivity(intent);

    }

    @Override
    public boolean onLongItemClickListener(final Match match) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                databaseReference.child(FireBaseInsert.NAME_MATCH).child(match.getId()).removeValue();

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

            databaseReference.child(FireBaseInsert.NAME_MATCH).orderByChild("opponentTeam").startAt(query).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (dataSnapshot.getValue() != null) {
                        if (dataSnapshot.getValue().toString().contains(query)) {
                            getSearchMatch(dataSnapshot);

                        }

                    }

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
            getAllMatch();

        }

    }

    private void getSearchMatch(DataSnapshot dataSnapshot) {


        Match match = dataSnapshot.getValue(Match.class);
        searchList.add(match);
        matchAdapter = new MatchAdapter(getContext(), searchList);
        matchAdapter.setOnClickListener(this);
        matchAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(matchAdapter);

    }
}
