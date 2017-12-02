package com.example.rogerio.campominado.game;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.adapters.GridAdapter;
import com.example.rogerio.campominado.game.Engine;


public class GameActivity extends AppCompatActivity {

    Engine e;
    GridView grid;
    GridAdapter adapter;
    Chronometer chronometer;
    Button start;
    Button restart;
    Integer[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        start = (Button) findViewById(R.id.btnStart);
        restart = (Button) findViewById(R.id.btnRestart);
        grid = (GridView) findViewById(R.id.grid);
        initiate();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                e.open(position / 10, position % 10);
                int status = e.checkEnd(position / 10, position % 10);

                // Lose
                if(status == -1) {
                    Toast.makeText(GameActivity.this, "You Lose", Toast.LENGTH_SHORT).show();
                    chronometer.stop();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setText("Time Spent: ");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                e.startGame();
                start.setEnabled(false);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                e.stopGame();
                start.setEnabled(true);
                initiate();
            }
        });
    }



    public Integer[] getDefaultGrid(){
        list = new Integer[100];

        for(int i=0; i<list.length;i++)
            list[i] = R.drawable.button;

        return list;
    }

    public void initiate(){
        adapter = new GridAdapter(this,getDefaultGrid());
        grid.setAdapter(adapter);
        e = new Engine(10,10,10,adapter);
        e.run();
    }


}
