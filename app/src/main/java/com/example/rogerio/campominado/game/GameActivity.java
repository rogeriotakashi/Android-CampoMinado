package com.example.rogerio.campominado.game;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.adapters.GridAdapter;
import com.example.rogerio.campominado.leaderboard.InsertPlayer;
import com.example.rogerio.campominado.settings.GameSettings;


public class GameActivity extends AppCompatActivity {

    GameEngine e;
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
                checkStatus(status);

            }
        });

        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                e.flag(position /10, position % 10);
                int status = e.checkEnd(position / 10, position % 10);
                checkStatus(status);

                return true;
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


    public void checkStatus(int status){
        if(status == -1) {
            Toast.makeText(GameActivity.this, "You Lose", Toast.LENGTH_SHORT).show();
            chronometer.stop();
        }

        if(status == 1) {
            displayWinToast();
            chronometer.stop();
            showAlertDialogOnWin();
        }
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

        // Restore preferences
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        int bombQuantity = settings.getInt("bombQuantity",10);

        e = new GameEngine(10,10, GameSettings.getBombQuantityByDifficult(),adapter);
        Toast.makeText(this,"Bomb Quantity:"+GameSettings.getBombQuantityByDifficult(), Toast.LENGTH_SHORT).show();
        e.setGrid();
    }

    public void showAlertDialogOnWin(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final long finishedTime = SystemClock.elapsedRealtime();

        alert.setTitle("You Won");
        alert.setMessage("Nickname:");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String[] fields = {"nickname","time"};
                String[] values = new String[2];
                values[0] = input.getText().toString();
                values[1] = finishedTime - chronometer.getBase() + "";

                insertPlayer(fields,values);

            }
        });
        alert.show();
    }

    public void insertPlayer(String[] fields, String[] values){
        InsertPlayer ip = new InsertPlayer(fields,values);
        ip.execute();
    }

    public void displayWinToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_win,(ViewGroup) findViewById(R.id.customWinToast));
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


}
