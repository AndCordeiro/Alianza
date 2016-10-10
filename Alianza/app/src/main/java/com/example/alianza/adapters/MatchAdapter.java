package com.example.alianza.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alianza.R;

/**
 * Created by andre on 10/10/16.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder>{

    // Set numbers of List in RecyclerView.
    private static final int LENGTH = 1;

    public MatchAdapter() {

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
            super(inflater.inflate(R.layout.adapter_match, parent, false));
        }
    }



}
