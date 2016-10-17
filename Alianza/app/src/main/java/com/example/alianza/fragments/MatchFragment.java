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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alianza.R;
import com.example.alianza.activity.VisualizationMatchActivity;
import com.example.alianza.activity.VisualizationNewsActivity;
import com.example.alianza.adapters.MatchAdapter;
import com.example.alianza.adapters.NewsAdapter;
import com.example.alianza.database.MatchDAO;
import com.example.alianza.database.NewsDAO;
import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;

/**
 * Created by andre on 09/10/16.
 */

public class MatchFragment extends Fragment implements MatchAdapter.OnClickListener, MatchAdapter.OnLongClickListener{

    RecyclerView recyclerView;
    MatchDAO matchDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).newsLoad());
        matchAdapter.setOnClickListener(this);
        matchAdapter.setOnLongClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(matchAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MatchAdapter matchAdapter = new MatchAdapter(getActivity(), new MatchDAO(getContext()).newsLoad());
        matchAdapter.setOnClickListener(this);
        matchAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(matchAdapter);

    }

    @Override
    public void onItemClickListener(Match match) {

        Intent intent = new Intent(this.getActivity(), VisualizationMatchActivity.class);
        intent.putExtra(VisualizationMatchActivity.ID, match.getId());
        startActivity(intent);

    }

    @Override
    public boolean onLongItemClickListener(final Match match) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                matchDAO = new MatchDAO(getContext());
                matchDAO.recordsDelete(match.getId());
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
}
