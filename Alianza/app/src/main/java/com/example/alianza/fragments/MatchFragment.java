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
import com.example.alianza.pojo.Match;

/**
 * Created by andre on 09/10/16.
 */

public class MatchFragment extends Fragment implements MatchAdapter.OnClickListener, MatchAdapter.OnLongClickListener, MainActivity.OnClickSearchMatch{

    RecyclerView recyclerView;
   // MatchDAO matchDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        //MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).matchesLoad());
        //matchAdapter.setOnClickListener(this);
        //matchAdapter.setOnLongClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //recyclerView.setAdapter(matchAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.setOnClickSearchMatch(this);

        return view;
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
        //intent.putExtra(VisualizationMatchActivity.ID);
        startActivity(intent);

    }

    @Override
    public boolean onLongItemClickListener(final Match match) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //matchDAO = new MatchDAO(getContext());
                //matchDAO.dataDelete(match.getId());
                onResume();

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

        System.out.println("Match");
        if (query.length() >= 3) {

            //recyclerView.setAdapter(new MatchAdapter(getActivity(), new MatchDAO(getActivity()).matchLoadByOpponent(query)));

        }else{

            //MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).matchesLoad());
            //recyclerView.setAdapter(matchAdapter);

        }

    }
}
