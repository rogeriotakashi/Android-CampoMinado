package com.example.rogerio.campominado.leaderboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.rogerio.campominado.R;

public class LeaderboardActivity extends AppCompatActivity {

    ListView lista;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        lista = (ListView)findViewById(R.id.lvLeaderboard);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setProgress(0);

        SelectPlayer sp = new SelectPlayer(this,lista,progress);
        sp.execute();
    }
}
