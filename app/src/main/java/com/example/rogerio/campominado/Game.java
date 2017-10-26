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
    Button start,button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        start = (Button) findViewById(R.id.btnStart);
        button = (Button) findViewById(R.id.button);

        GameEngine.getInstance().createGrid(this);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setText("Time Spent:");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                GameEngine.getInstance().startGame();;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            chronometer.stop();
            final long finishedTime = SystemClock.elapsedRealtime();
            AlertDialog.Builder alert = new AlertDialog.Builder(Game.this);

            alert.setTitle("You Won");
            alert.setMessage("Nickname:");

            // Set an EditText view to get user input
            final EditText input = new EditText(Game.this);
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String[] fields = new String[2];
                    String[] values = new String[2];
                    fields[0] = "nickname";
                    fields[1] = "time";

                    values[0] = input.getText().toString();
                    values[1] = finishedTime - chronometer.getBase() + "";
                    Toast.makeText(Game.this,values[0],Toast.LENGTH_SHORT).show();
                    Toast.makeText(Game.this,values[1],Toast.LENGTH_SHORT).show();


                    InsertPlayer ip = new InsertPlayer(fields,values);
                    ip.execute();
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();
            }
        });









    }
}
