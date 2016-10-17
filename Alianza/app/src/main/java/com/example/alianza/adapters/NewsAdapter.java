package com.example.alianza.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.News;
import com.example.alianza.pojo.Player;
import com.example.alianza.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

/**
 * Created by andre on 10/10/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    protected List<News> mNews;
    protected OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;

    public NewsAdapter(Context context, List<News> news) {

        this.mNews = news;

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        final News news = mNews.get(position);
        holder.title.setText(news.getTitle());
        holder.author.setText(news.getAuthor());
        holder.dateNews.setText(DateUtils.formatDate(news.getDateNews(), DateUtils.DATE_DB, DateUtils.DATE_BR));


    }

    @Override
    public int getItemCount() {
        return mNews.size();
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


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.adapter_news, parent, false));

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
