package com.example.alianza.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alianza.R;

/**
 * Created by andre on 10/10/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{



    public NewsAdapter(Context context) {

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {




    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.adapter_news, parent, false));
        }
    }



}
