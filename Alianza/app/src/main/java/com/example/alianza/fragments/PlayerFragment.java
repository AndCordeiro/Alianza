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
import com.example.alianza.pojo.Player;

/**
 * Created by andre on 09/10/16.
 */

public class PlayerFragment extends Fragment  implements PlayerAdapter.OnClickListener, PlayerAdapter.OnLongClickListener, MainActivity.OnClickSearchPlayer {

    RecyclerView recyclerView;
    //PlayerDAO playerDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(
                R.layout.fragment_player, container, false);
        //PlayerAdapter playerAdapter = new PlayerAdapter(getActivity(), new PlayerDAO(getActivity()).playersLoad());
       // playerAdapter.setOnClickListener(this);
        //playerAdapter.setOnLongClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //recyclerView.setAdapter(playerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.setOnClickSearchPlayer(this);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        //PlayerAdapter playerAdapter = new PlayerAdapter(getActivity(), new PlayerDAO(getContext()).playersLoad());
        //playerAdapter.setOnClickListener(this);
        //playerAdapter.setOnLongClickListener(this);
        //recyclerView.setAdapter(playerAdapter);

    }

    @Override
    public void onItemClickListener(Player player) {

        Intent intent = new Intent(this.getActivity(), VisualizationPlayerActivity.class);
        //intent.putExtra(VisualizationPlayerActivity.ID, player.getId());
        startActivity(intent);

    }


    @Override
    public boolean onLongItemClickListener(final Player player) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //playerDAO = new PlayerDAO(getContext());
                //playerDAO.dataDelete(player.getId());
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
        System.out.println("Player");
        if (query.length() >= 3) {

            //recyclerView.setAdapter(new PlayerAdapter(getActivity(), new PlayerDAO(getActivity()).playersLoadByNamePlayer(query)));

        }else{

            //PlayerAdapter playerAdapter = new PlayerAdapter(getActivity(), new PlayerDAO(getActivity()).playersLoad());
            //recyclerView.setAdapter(playerAdapter);

        }

    }


}
