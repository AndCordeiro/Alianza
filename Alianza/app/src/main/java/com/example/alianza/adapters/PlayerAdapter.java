package com.example.alianza.adapters;

/**
 * Created by andre on 10/10/16.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.Player;
import com.example.alianza.utils.DateUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter to display recycler view.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> implements
        GoogleApiClient.OnConnectionFailedListener {

    protected List<Player> mPlayer;
    protected PlayerAdapter.OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;
    Context context;


    public PlayerAdapter(Context context, List<Player> player) {

        this.mPlayer = player;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        PlayerAdapter.ViewHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_player, parent, false);
        viewHolder = new PlayerAdapter.ViewHolder(layoutView, mPlayer);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Player player = mPlayer.get(position);
        Picasso.with(context).load(player.getPhoto()).error(R.drawable.perfil_sombra).fit().centerCrop().into(holder.photo);
        holder.player.setText(player.getPlayer());
        holder.age.setText(DateUtils.getAge(player.getBirth()) + " " + holder.itemView.getContext().getString(R.string.years));


    }

    @Override
    public int getItemCount() {
        return mPlayer.size();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public interface OnClickListener {
        void onItemClickListener(Player player);
    }

    public interface OnLongClickListener {
        boolean onLongItemClickListener(Player player);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public TextView player;
        public TextView age;


        public ViewHolder(View itemView, List<Player> playerList) {
            super(itemView);

            mPlayer = playerList;
            photo = (ImageView) itemView.findViewById(R.id.picturePlayer);
            player = (TextView) itemView.findViewById(R.id.name_player);
            age = (TextView) itemView.findViewById(R.id.age_player);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (onClickListener != null) {
                        onClickListener.onItemClickListener(mPlayer.get(getAdapterPosition()));

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {


                    if (onLongClickListener != null) {

                        onLongClickListener.onLongItemClickListener(mPlayer.get(getAdapterPosition()));

                    }

                    return true;
                }
            });

        }
    }

}
