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
    Button pauseOrPlay;
    Button restart;

    boolean isPaused;
    long timeWhenPaused = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        start = (Button) findViewById(R.id.btnStart);
        pauseOrPlay = (Button) findViewById(R.id.btnPauseOrPlay);
        restart = (Button) findViewById(R.id.btnRestart);


        GameEngine.getInstance().createGrid(this);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setText("Time Spent: ");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                GameEngine.getInstance().startGame();
                isPaused = false;
                start.setEnabled(false);
            }
        });

        pauseOrPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPaused) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenPaused);
                    chronometer.start();
                    isPaused = false;
                    pauseOrPlay.setText("Pause");
                }else{
                    chronometer.stop();
                    timeWhenPaused = chronometer.getBase() - SystemClock.elapsedRealtime();
                    isPaused = true;
                    GameEngine.getInstance().stopGame();
                    pauseOrPlay.setText("Continue");
                }

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
                pauseOrPlay.setText("Pause");
                isPaused = false;
            }
        });


    }


}
