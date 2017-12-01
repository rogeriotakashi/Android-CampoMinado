package com.example.rogerio.campominado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.Toast;


public class Game extends AppCompatActivity {

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

        // Initiate a 10x10 field
        e = new Engine(10,10,10);
        e.run();

        grid = (GridView) findViewById(R.id.grid);
        adapter = new GridAdapter(this,getListButtons());
        grid.setAdapter(adapter);


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                adapter.updateList(position,e.open(position / 10, position % 10));
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
                engine.createGrid(Game.this);
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                engine.stopGame();
                start.setEnabled(true);
            }
        });

*/
    }

    public Integer[] getListButtons(){
        list = new Integer[100];

        for(int i=0; i<list.length;i++)
            list[i] = R.drawable.button;

        return list;
    }


}
