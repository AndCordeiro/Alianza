package com.example.alianza.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.Match;
import com.example.alianza.utils.DateUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * Created by andre on 10/10/16.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> implements
        GoogleApiClient.OnConnectionFailedListener{

    protected List<Match> mMatch;
    protected OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;
    private Context context;

    public MatchAdapter(Context context, List<Match> match) {

        this.mMatch = match;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MatchAdapter.ViewHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_match, parent, false);
        viewHolder = new MatchAdapter.ViewHolder(layoutView, mMatch);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Match match = mMatch.get(position);
        holder.dateOfMatch.setText(context.getResources().getConfiguration().locale.getLanguage().equals("pt") ? match.getDateOfMatch() : DateUtils.formatDate(match.getDateOfMatch(), DateUtils.DATE_BR, DateUtils.DATE_USA));
        holder.hourOfMatch.setText(match.getHourOfMatch());
        holder.opponentTeam.setText(match.getOpponentTeam());
        holder.placeOfMatch.setText(match.getPlaceOfMatch());
        holder.team.setText(match.TEAM);

    }

    @Override
    public int getItemCount() {
        return mMatch.size();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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



        public ViewHolder(View itemView, List<Match> matchList) {
            super(itemView);

            mMatch = matchList;
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
