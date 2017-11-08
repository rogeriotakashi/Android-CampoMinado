package com.example.rogerio.campominado;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rogerio.campominado.leaderboard.InsertPlayer;


public class Game extends AppCompatActivity {

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


        GameEngine.getInstance().createGrid(this);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setText("Time Spent: ");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                GameEngine.getInstance().startGame();
                start.setEnabled(false);
            }
        });



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().createGrid(Game.this);
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                GameEngine.getInstance().stopGame();
                start.setEnabled(true);
            }
        });


    }


}
