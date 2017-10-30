package com.example.rogerio.campominado.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.leaderboard.Player_item;
import com.example.rogerio.campominado.menu.Menu_Item;

import java.util.List;

/**
 * Created by ROGERIO on 30/10/2017.
 */

public class LeaderboardAdapter extends BaseAdapter {

    private Context context;
    private List<Player_item> players;

    public LeaderboardAdapter(Context context, List<Player_item> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player_item playerItem = players.get(position);

        View linha = LayoutInflater.from(context).inflate(R.layout.leaderboard_line,null);

        ImageView playerImage = (ImageView) linha.findViewById(R.id.playerImage);
        TextView nickname = (TextView) linha.findViewById(R.id.nickname);
        ImageView clockImage = (ImageView) linha.findViewById(R.id.clockImage);
        TextView time = (TextView) linha.findViewById(R.id.time);

        nickname.setText(playerItem.getNickname());
        time.setText(playerItem.getTime());


        return linha;
    }
}
