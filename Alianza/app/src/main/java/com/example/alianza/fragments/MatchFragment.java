package com.example.alianza.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MatchFragment extends Fragment implements MatchAdapter.OnClickListener, MatchAdapter.OnLongClickListener, MainActivity.OnClickSearchMatch{

    RecyclerView recyclerView;
    // MatchDAO matchDAO;
    private MatchAdapter matchAdapter;
    private DatabaseReference databaseReference;
    private List<Match> matchList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        //MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).matchesLoad());
        //matchAdapter.setOnClickListener(this);
        //matchAdapter.setOnLongClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //recyclerView.setAdapter(matchAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.setOnClickSearchMatch(this);

        matchList = new ArrayList<Match>();

        databaseReference.child(FireBaseInsert.NAME_MATCH).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (s != null) {
                    if (!s.equals(dataSnapshot)) {
                        Log.e("Is Not Null", "onChildAdded: ");
                        getAllMatch(dataSnapshot);
                    }
                } else {
                    Log.e("Is Null", "onChildAdded: ");
                    getAllMatch(dataSnapshot);

                }
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

                getAllMatch();

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                System.out.println("moved firebase");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;
    }

    private void getAllMatch(DataSnapshot dataSnapshot){


        Match match = dataSnapshot.getValue(Match.class);
        matchList.add(match);
        matchAdapter = new MatchAdapter(getContext(), matchList);
        matchAdapter.setOnClickListener(this);
        matchAdapter.setOnLongClickListener(this);
        match.setId(dataSnapshot.getKey());
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

        //MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).matchesLoad());
        //matchAdapter.setOnClickListener(this);
        //matchAdapter.setOnLongClickListener(this);
        //recyclerView.setAdapter(matchAdapter);


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

                for (int i = 0; i < matchList.size(); i++) {

                    if (matchList.get(i).getId().equals(match.getId())) {

                        matchList.remove(i);
                    }

                }
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
    public void onItemClickSearch(String query) {

        if (query.length() >= 3) {

            //recyclerView.setAdapter(new MatchAdapter(getActivity(), new MatchDAO(getActivity()).matchLoadByOpponent(query)));

        }else{

            //MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).matchesLoad());
            //recyclerView.setAdapter(matchAdapter);

        }

    }
}
