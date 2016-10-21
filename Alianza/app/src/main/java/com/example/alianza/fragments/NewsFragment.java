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
import com.example.alianza.activity.VisualizationNewsActivity;
import com.example.alianza.adapters.NewsAdapter;
import com.example.alianza.firebase.FireBaseInsert;
import com.example.alianza.pojo.News;
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

public class NewsFragment extends Fragment implements NewsAdapter.OnClickListener, NewsAdapter.OnLongClickListener, MainActivity.OnClickSearchNews {

    RecyclerView recyclerView;
    //NewsDAO newsDAO;

    private NewsAdapter newsAdapter;
    private DatabaseReference databaseReference;
    private List<News> newsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //recyclerView.setAdapter(newsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnClickSearchNews(this);


        newsList = new ArrayList<News>();

        databaseReference.child(FireBaseInsert.NAME_NEWS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (s != null) {
                    if (!s.equals(dataSnapshot)) {
                        getAllNews(dataSnapshot);
                    }
                } else {
                    getAllNews(dataSnapshot);

                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                for (int i = 0; i < newsList.size(); i++) {

                    if (newsList.get(i).getId().equals(dataSnapshot.getKey())) {

                        newsList.get(i).setTitle(dataSnapshot.child("title").getValue().toString());
                        newsList.get(i).setNews(dataSnapshot.child("news").getValue().toString());
                        newsList.get(i).setAuthor(dataSnapshot.child("author").getValue().toString());
                        newsList.get(i).setDateNews(dataSnapshot.child("dateNews").getValue().toString());

                        getAllNews();
                    }

                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                getAllNews();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                System.out.println("Passei aqui move");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println("Passei aqui canceled");
            }
        });


        return view;
    }

    private void getAllNews(DataSnapshot dataSnapshot) {


        News news = dataSnapshot.getValue(News.class);
        newsList.add(news);
        newsAdapter = new NewsAdapter(getContext(), newsList);
        newsAdapter.setOnClickListener(this);
        newsAdapter.setOnLongClickListener(this);
        news.setId(dataSnapshot.getKey());
        recyclerView.setAdapter(newsAdapter);

    }

    private void getAllNews() {

        newsAdapter = new NewsAdapter(getContext(), newsList);
        newsAdapter.setOnClickListener(this);
        newsAdapter.setOnLongClickListener(this);
        recyclerView.setAdapter(newsAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onItemClickListener(News news) {

        Intent intent = new Intent(this.getActivity(), VisualizationNewsActivity.class);
        intent.putExtra(VisualizationNewsActivity.NEWS, news);
        startActivity(intent);

    }

    @Override
    public boolean onLongItemClickListener(final News news) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                for (int i = 0; i < newsList.size(); i++) {

                    if (newsList.get(i).getId().equals(news.getId())) {

                        newsList.remove(i);
                    }

                }
                databaseReference.child(FireBaseInsert.NAME_NEWS).child(news.getId()).removeValue();

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
            System.out.println("News");
            //recyclerView.setAdapter(new NewsAdapter(getActivity(), new NewsDAO(getActivity()).newsLoadByTitle(query)));

        } else {
            System.out.println("News1");
            //NewsAdapter newsAdapter = new NewsAdapter(getActivity(), new NewsDAO(getContext()).newsLoad());
            //recyclerView.setAdapter(newsAdapter);

        }

    }
}
