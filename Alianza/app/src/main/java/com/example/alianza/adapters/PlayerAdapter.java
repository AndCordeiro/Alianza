package com.example.alianza.adapters;

/**
 * Created by andre on 10/10/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alianza.R;
import com.example.alianza.pojo.Match;
import com.example.alianza.pojo.Player;
import com.example.alianza.utils.DateUtils;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Adapter to display recycler view.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    protected List<Player> mPlayer;
    protected PlayerAdapter.OnClickListener onClickListener;
    protected OnLongClickListener onLongClickListener;


    public PlayerAdapter(Context context, List<Player> player) {

        this.mPlayer = player;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Player player = mPlayer.get(position);


        //holder.photo.set(player.getPhoto());
        holder.player.setText(player.getPlayer());
        holder.age.setText(DateUtils.getAge(player.getBirth()) + " " + "");


    }

    @Override
    public int getItemCount() {
        return mPlayer.size();
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

        //public ImageView photo;
        public TextView player;
        public TextView age;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.adapter_player, parent, false));

            //photo = (ImageView) itemView.findViewById(R.id.picturePlayer);
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
