package com.example.alianza.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.News;
import com.example.alianza.utils.DateUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * Created by andre on 10/10/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements
        GoogleApiClient.OnConnectionFailedListener {


    protected List<News> mNews;
    protected OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;
    private Context context;


    public NewsAdapter(Context context, List<News> news) {

        this.mNews = news;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news, parent, false);
        return new ViewHolder(layoutView, mNews);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        News news = mNews.get(position);
        holder.title.setText(news.getTitle());
        holder.author.setText(news.getAuthor());
        holder.dateNews.setText(context.getResources().getConfiguration().locale.getLanguage().equals("pt") ? news.getDateNews() : DateUtils.formatDate(news.getDateNews(), DateUtils.DATE_BR, DateUtils.DATE_USA));


    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public interface OnClickListener {
        void onItemClickListener(News news);
    }

    public interface OnLongClickListener {
        boolean onLongItemClickListener(News news);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView author;
        public TextView dateNews;


        public ViewHolder(View itemView, List<News> newsList) {
            super(itemView);

            mNews = newsList;
            title = (TextView) itemView.findViewById(R.id.title_new);
            author = (TextView) itemView.findViewById(R.id.author_new);
            dateNews = (TextView) itemView.findViewById(R.id.date_new);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    if (onClickListener != null) {
                        onClickListener.onItemClickListener(mNews.get(getAdapterPosition()));

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    if (onLongClickListener != null) {

                        onLongClickListener.onLongItemClickListener(mNews.get(getAdapterPosition()));

                    }

                    return true;
                }
            });

        }
    }


}
