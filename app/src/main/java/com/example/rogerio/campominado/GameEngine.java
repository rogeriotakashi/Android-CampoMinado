package com.example.rogerio.campominado;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rogerio.campominado.leaderboard.InsertPlayer;
import com.example.rogerio.campominado.settings.GameSettings;
import com.example.rogerio.campominado.util.Generator;
import com.example.rogerio.campominado.util.PrintGrid;
import com.example.rogerio.campominado.views.grid.Cell;

/**
 * Created by ROGERIO on 19/10/2017.
 */

public class GameEngine {
    private static GameEngine instance;
    public static int BOMB_NUMBER = GameSettings.getBombQuantityByDifficult();
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;


    private Context context;
    private boolean isStarted;
    Chronometer chronometer;
    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];


    public static GameEngine getInstance()
    {
        if( instance == null )
        {
            instance = new GameEngine();
        }
        return instance;
    }

    public static void resetInstance()
    {
        instance = null;
        BOMB_NUMBER = GameSettings.getBombQuantityByDifficult();
    }






    private GameEngine(){ }

    public void startGame()
    {
        isStarted = true;
    }
    public void stopGame()
    {
        isStarted = false;
    }

    public void createGrid(Context context)
    {
        this.context = context;

        int[][] GenaratedrGrid = Generator.generate(BOMB_NUMBER,WIDTH,HEIGHT);
        PrintGrid.print(GenaratedrGrid,WIDTH,HEIGHT);
        setGrid(context,GenaratedrGrid);
        this.isStarted=false;

    }


    public Cell getCellAt(int position)
    {
        int x = position % WIDTH;
        int y = position / WIDTH;



        return MinesweeperGrid[x][y];
    }

    private void setGrid(final Context context,final int[][] grid)
    {
        for(int x = 0 ; x < WIDTH;x++)
        {
            for(int y = 0; y < HEIGHT; y++)
            {
                if(MinesweeperGrid[x][y]==null)
                    MinesweeperGrid[x][y] = new Cell (context,x,y);

                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int x,int y)
    {
        return MinesweeperGrid[x][y];
    }

    public void click(int x, int y)
    {
        if(isStarted) {
            if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked()) {
                getCellAt(x, y).setClicked();

                if (getCellAt(x, y).getValue() == 0) {
                    for (int xt = -1; xt <= 1; xt++) {
                        for (int yt = -1; yt <= 1; yt++) {
                            if (xt != yt)
                                click(x + xt, y + yt);
                        }
                    }
                }
                if (getCellAt(x, y).isBomb()) {
                    onGameLost();
                    isStarted = false;
                }
            }

            checkEnd();
        }

    }

    private boolean checkEnd()
    {
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH * HEIGHT;

        for(int x = 0; x < WIDTH; x++)
        {
            for(int y=0; y< HEIGHT;y++)
            {
                if(getCellAt(x,y).isRevealed() || getCellAt(x,y).isFlagged())
                    notRevealed--;

                if(getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb())
                    bombNotFound--;
            }
        }

        if(bombNotFound == 0 && notRevealed == 0){
            Toast.makeText(context, "Game Won!", Toast.LENGTH_SHORT).show();

            View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
            chronometer = (Chronometer) rootView.findViewById(R.id.chronometer2);

            chronometer.stop();
            final long finishedTime = SystemClock.elapsedRealtime();
            AlertDialog.Builder alert = new AlertDialog.Builder(context);

            alert.setTitle("You Won");
            alert.setMessage("Nickname:");

            // Set an EditText view to get user input
            final EditText input = new EditText(context);
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String[] fields = new String[2];
                    String[] values = new String[2];
                    fields[0] = "nickname";
                    fields[1] = "time";

                    values[0] = input.getText().toString();
                    values[1] = finishedTime - chronometer.getBase() + "";
                    Toast.makeText(context,values[0],Toast.LENGTH_SHORT).show();
                    Toast.makeText(context,values[1],Toast.LENGTH_SHORT).show();


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



        return false;
    }

    private void onGameLost()
    {
        Toast.makeText(context, "Game Lost!", Toast.LENGTH_SHORT).show();
        for(int x = 0; x < WIDTH; x++)
        {
            for(int y=0; y< HEIGHT;y++)
            {
                getCellAt(x,y).setRevealed();
            }
        }
    }



    public void flag(int x, int y)
    {
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();

        checkEnd();
    }
}
