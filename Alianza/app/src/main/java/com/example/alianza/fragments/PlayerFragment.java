package com.example.alianza.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alianza.R;
import com.example.alianza.activity.MainActivity;
import com.example.alianza.activity.RegisterCreateNewsActivity;
import com.example.alianza.activity.RegisterCreatePlayerActivity;
import com.example.alianza.adapters.PlayerAdapter;

/**
 * Created by andre on 09/10/16.
 */

public class PlayerFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(
                R.layout.fragment_player, container, false);
        PlayerAdapter playerAdapter = new PlayerAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(playerAdapter);
        recyclerView.setHasFixedSize(true);

        // Set padding for Tiles (not needed for Cards/Lists!)
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }




}
