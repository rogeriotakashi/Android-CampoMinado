package com.example.rogerio.campominado.game;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.adapters.GridAdapter;
import com.example.rogerio.campominado.leaderboard.InsertPlayer;
import com.example.rogerio.campominado.personal.DatabaseHelper;
import com.example.rogerio.campominado.settings.GameSettings;


public class GameActivity extends AppCompatActivity {

    GameEngine e;
    GridView grid;
    GridAdapter adapter;
    Chronometer chronometer;
    Button start;
    Button restart;
    Integer[] list;
    DatabaseHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chronometer = (Chronometer) findViewById(R.id.chronometer2);
        start = (Button) findViewById(R.id.btnStart);
        restart = (Button) findViewById(R.id.btnRestart);
        grid = (GridView) findViewById(R.id.grid);

        // Core Operations to start the game
        initiate();



        // Grid Events
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                e.open(position / 10, position % 10);
                int status = e.checkEnd(position / 10, position % 10);
                checkStatus(status);

            }
        });

        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                e.flag(position / 10, position % 10);
                int status = e.checkEnd(position / 10, position % 10);
                checkStatus(status);

                return true;
            }
        });

        // Button Events
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


    /**
     * Check the game status and execute actions based on it
     *
     * @param status Actual game status
     */
    public void checkStatus(int status) {
        if (status == -1) {
            Toast.makeText(GameActivity.this, "You Lose", Toast.LENGTH_SHORT).show();
            chronometer.stop();
        }

        if (status == 1) {
            chronometer.stop();
            insertPersonalRecord();
            showAlertDialogOnWin();
        }
    }

    /**
     * Fill a Integer[] with closed buttons
     * @return returns a Integer[] filled with closed buttons
     */
    public Integer[] getDefaultGrid() {
        list = new Integer[100];

        for (int i = 0; i < list.length; i++)
            list[i] = R.drawable.button;

        return list;
    }

    /**
     * Core operations to create a new game
     */
    public void initiate() {
        adapter = new GridAdapter(this, getDefaultGrid());
        grid.setAdapter(adapter);
        e = new GameEngine(10, 10, GameSettings.getBombQuantityByDifficult(), adapter);
        e.setGrid();
    }

    /**
     * Displayed when player wins
     */
    public void showAlertDialogOnWin() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final long finishedTime = SystemClock.elapsedRealtime();

        alert.setTitle("You Won");
        alert.setMessage("Nickname:");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String[] fields = {"nickname", "time"};
                String[] values = new String[2];
                values[0] = input.getText().toString();
                values[1] = finishedTime - chronometer.getBase() + "";

                InsertPlayer ip = new InsertPlayer(fields, values);
                ip.execute();

            }
        });
        alert.show();
    }

    /**
     * Executed when player wins
     */
    public void insertPersonalRecord() {
        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        final long finishedTime = SystemClock.elapsedRealtime();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nickname", "You");
        contentValues.put("time", finishedTime - chronometer.getBase() + "");
        contentValues.put("difficulty", GameSettings.getGameDifficult());
        sqLiteDatabase.insert("PersonalRecords", null, contentValues);

    }


}
