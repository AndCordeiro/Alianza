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
import com.example.alianza.activity.VisualizationNewsActivity;
import com.example.alianza.activity.VisualizationPlayerActivity;
import com.example.alianza.adapters.NewsAdapter;
import com.example.alianza.database.NewsDAO;
import com.example.alianza.database.PlayerDAO;
import com.example.alianza.pojo.News;

/**
 * Created by andre on 09/10/16.
 */

public class NewsFragment extends Fragment implements NewsAdapter.OnClickListener, NewsAdapter.OnLongClickListener{

    RecyclerView recyclerView;
    NewsDAO newsDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), new NewsDAO(getContext()).newsLoad());
        newsAdapter.setOnClickListener(this);
        newsAdapter.setOnLongClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), new NewsDAO(getContext()).newsLoad());
        newsAdapter.setOnClickListener(this);
        newsAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(newsAdapter);

    }


    @Override
    public void onItemClickListener(News news) {

        Intent intent = new Intent(this.getActivity(), VisualizationNewsActivity.class);
        intent.putExtra(VisualizationNewsActivity.ID, news.getId());
        startActivity(intent);

    }

    @Override
    public boolean onLongItemClickListener(final News news) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                newsDAO = new NewsDAO(getContext());
                newsDAO.recordsDelete(news.getId());
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
