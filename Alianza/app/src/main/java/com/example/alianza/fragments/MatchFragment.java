package com.example.alianza.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alianza.R;
import com.example.alianza.adapters.MatchAdapter;

/**
 * Created by andre on 09/10/16.
 */

public class MatchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_match, container, false);
        MatchAdapter matchAdapter = new MatchAdapter();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(matchAdapter);
        recyclerView.setHasFixedSize(true);
        return view;
    }



}
