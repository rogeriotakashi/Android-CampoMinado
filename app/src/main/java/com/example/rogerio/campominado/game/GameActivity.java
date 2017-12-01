package com.example.rogerio.campominado.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;

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


    boolean isPaused;
    long timeWhenPaused = 0;
    Integer[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        start = (Button) findViewById(R.id.btnStart);
        restart = (Button) findViewById(R.id.btnRestart);

        grid = (GridView) findViewById(R.id.grid);
        adapter = new GridAdapter(this,getDefaultGrid());
        grid.setAdapter(adapter);

        // Initiate a 10x10 field
        e = new Engine(10,10,10,adapter);
        e.run();


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                e.open(position / 10, position % 10);
            }
        });


/*

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setText("Time Spent: ");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                engine.startGame();
                start.setEnabled(false);
            }
        });



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine.createGrid(GameActivity.this);
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                engine.stopGame();
                start.setEnabled(true);
            }
        });

*/
    }

    public Integer[] getDefaultGrid(){
        list = new Integer[100];

        for(int i=0; i<list.length;i++)
            list[i] = R.drawable.button;

        return list;
    }


}
