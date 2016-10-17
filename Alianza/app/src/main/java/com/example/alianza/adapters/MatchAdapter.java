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
import com.example.alianza.utils.DateUtils;

import java.util.List;

/**
 * Created by andre on 10/10/16.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder>{

    protected List<Match> mMatch;
    protected OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;

    public MatchAdapter(Context context, List<Match> match) {


        this.mMatch = match;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final Match match = mMatch.get(position);
        holder.dateOfMatch.setText(DateUtils.formatDate(match.getDateOfMatch(), DateUtils.DATE_DB, DateUtils.DATE_BR));
        holder.hourOfMatch.setText(match.getHourOfMatch());
        holder.opponentTeam.setText(match.getOpponentTeam());
        holder.placeOfMatch.setText(match.getPlaceOfMatch());
        holder.team.setText(match.TEAM);

    }

    @Override
    public int getItemCount() {
        return mMatch.size();
    }

    public interface OnClickListener {
        void onItemClickListener(Match match);
    }

    public interface OnLongClickListener {
        boolean onLongItemClickListener(Match match);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateOfMatch;
        public TextView hourOfMatch;
        public TextView opponentTeam;
        public TextView placeOfMatch;
        public TextView team;



        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.adapter_match, parent, false));

            dateOfMatch = (TextView) itemView.findViewById(R.id.dateMatch);
            hourOfMatch = (TextView) itemView.findViewById(R.id.hourMatch);
            opponentTeam = (TextView) itemView.findViewById(R.id.nameTeam2);
            placeOfMatch = (TextView) itemView.findViewById(R.id.namePlace);
            opponentTeam = (TextView) itemView.findViewById(R.id.nameTeam2);
            team = (TextView) itemView.findViewById(R.id.nameTeam1);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (onClickListener != null) {
                        onClickListener.onItemClickListener(mMatch.get(getAdapterPosition()));

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if (onLongClickListener != null) {

                        onLongClickListener.onLongItemClickListener(mMatch.get(getAdapterPosition()));

                    }

                    return true;
                }
            });

        }
    }



}
