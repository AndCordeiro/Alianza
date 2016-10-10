package com.example.alianza.adapters;

/**
 * Created by andre on 10/10/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alianza.R;

/**
 * Adapter to display recycler view.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    // Set numbers of List in RecyclerView.
    private static final int LENGTH = 1;



    public PlayerAdapter(Context context) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.adapter_player, parent, false));
        }
    }
}
