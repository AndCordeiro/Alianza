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
import com.example.alianza.firebase.FireBaseConfig;
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

public class NewsFragment extends Fragment implements NewsAdapter.OnClickListener, NewsAdapter.OnLongClickListener, MainActivity.OnClickSearchNews{

    RecyclerView recyclerView;
    //NewsDAO newsDAO;
    private static final String TAG = MainActivity.class.getSimpleName();
    private NewsAdapter newsAdapter;
    private DatabaseReference databaseReference;
    private List<News> newsList;

    private final static int TITLE = 3;
    private final static int AUTHOR = 0;
    private final static int NEWS = 2;
    private final static int DATE_NEWS = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        //NewsAdapter newsAdapter = new NewsAdapter(getActivity(), new NewsDAO(getContext()).newsLoad());
        //newsAdapter.setOnClickListener(this);
        //newsAdapter.setOnLongClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //recyclerView.setAdapter(newsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.setOnClickSearchNews(this);
        newsList = new ArrayList<News>();

        databaseReference.child(FireBaseInsert.NAME_NEWS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllNews(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllNews(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;
    }

    private void getAllNews(DataSnapshot dataSnapshot){

        List<String> news = new ArrayList<String>();
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

            news.add(singleSnapshot.getValue().toString());
            System.out.println(news);

        }

        newsList.add(new News(news.get(TITLE), news.get(NEWS), news.get(AUTHOR), news.get(DATE_NEWS)) );
        newsAdapter = new NewsAdapter(getContext(), newsList);
        recyclerView.setAdapter(newsAdapter);
    }



    @Override
    public void onResume() {
        super.onResume();

        //NewsAdapter newsAdapter = new NewsAdapter(getActivity(), new NewsDAO(getContext()).newsLoad());
       // newsAdapter.setOnClickListener(this);
       // newsAdapter.setOnLongClickListener(this);
        //recyclerView.setAdapter(newsAdapter);

    }


    @Override
    public void onItemClickListener(News news) {

        Intent intent = new Intent(this.getActivity(), VisualizationNewsActivity.class);
        //intent.putExtra(VisualizationNewsActivity.ID, news.getId());
        startActivity(intent);

    }

    @Override
    public boolean onLongItemClickListener(final News news) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AppThemeRegisters));
        builder.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //newsDAO = new NewsDAO(getContext());
               // newsDAO.dataDelete(news.getId());
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

        System.out.println("News");
        if (query.length() >= 3) {

            //recyclerView.setAdapter(new NewsAdapter(getActivity(), new NewsDAO(getActivity()).newsLoadByTitle(query)));

        }else{

            //NewsAdapter newsAdapter = new NewsAdapter(getActivity(), new NewsDAO(getContext()).newsLoad());
            //recyclerView.setAdapter(newsAdapter);

        }

    }
}
