package com.example.rogerio.campominado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;


public class Game extends AppCompatActivity {

    GridView grid;
    Chronometer chronometer;
    Button start;
    Button restart;


    boolean isPaused;
    long timeWhenPaused = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        start = (Button) findViewById(R.id.btnStart);
        restart = (Button) findViewById(R.id.btnRestart);



        Engine e = new Engine(10,10,10);
        e.run();

        GridView gridview = (GridView) findViewById(R.id.grid);


        gridview.setAdapter(new GridAdapter(this,getListButtons()));




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
        Integer[] list = new Integer[100];

        for(int i=0; i<list.length;i++)
            list[i] = R.drawable.button;

        return list;
    }


}
