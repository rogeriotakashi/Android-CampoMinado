package com.example.rogerio.campominado.leaderboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rogerio.campominado.R;

public class LeaderboardActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        lista = (ListView)findViewById(R.id.lvLeaderboard);

        SelectPlayer sp = new SelectPlayer(this,lista);
        sp.execute();
    }
}
