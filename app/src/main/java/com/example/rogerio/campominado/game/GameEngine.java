package com.example.rogerio.campominado.game;

import android.util.Log;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.adapters.GridAdapter;
import com.example.rogerio.campominado.model.Cell;

import java.util.Random;

/**
 * Created by ROGERIO on 01/12/2017.
 */

public class GameEngine {
    private Cell[][] campo;
    private GridAdapter adapter;
    private int totalMines;
    private int maxRow;
    private int maxCol;
    private boolean isStarted;

    public GameEngine(int maxRow, int maxCol, int totalMines, GridAdapter adapter) {
        campo = new Cell[maxRow][maxCol];

        for (int i = 0; i < maxRow; i++)
            for (int j = 0; j < maxCol; j++)
                campo[i][j] = new Cell();

        this.totalMines = totalMines;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.adapter = adapter;
        this.isStarted = false;
    }

    public void setGrid() {
        setRamdomMines();
        scanNeighbour();
    }

    public void startGame() {
        this.isStarted = true;
    }

    public void stopGame() {
        this.isStarted = false;
    }

    public void setRamdomMines() {
        int actualMines = 0;
        Random random = new Random();

        while (actualMines < totalMines) {
            int randomRow = random.nextInt(maxRow);
            int randomCol = random.nextInt(maxCol);

            if (!campo[randomRow][randomCol].isMine()) {
                campo[randomRow][randomCol].setMine(true);
                actualMines++;
            }

        }
    }

    public void scanNeighbour() {

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (i > 0 && j > 0 && campo[i - 1][j - 1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (i > 0 && campo[i - 1][j].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (i > 0 && j < (maxCol - 1) && campo[i - 1][j + 1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (j > 0 && campo[i][j - 1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (j < (maxCol - 1) && campo[i][j + 1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (i < (maxCol - 1) && j > 0 && campo[i + 1][j - 1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (i < (maxCol - 1) && campo[i + 1][j].isMine())
                    campo[i][j].addNeighbourMineCount();
                if (i < (maxCol - 1) && j < (maxCol - 1) && campo[i + 1][j + 1].isMine())
                    campo[i][j].addNeighbourMineCount();
            }
        }

    }

    public void open(int row, int col) {
        if (!isStarted)
            return;

        int value = campo[row][col].getNeighbourMineCount();
        campo[row][col].setOpened(true);


        if (!campo[row][col].isMine()) {
            switch (value) {
                case 0:
                    openRecursive(row, col);
                    adapter.updatePosition(row * 10 + col, R.drawable.number_0);
                    break;
                case 1:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_1);
                    break;
                case 2:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_2);
                    break;
                case 3:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_3);
                    break;
                case 4:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_4);
                    break;
                case 5:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_5);
                    break;
                case 6:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_6);
                    break;
                case 7:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_7);
                    break;
                case 8:
                    adapter.updatePosition(row * 10 + col, R.drawable.number_8);
                    break;
            }
        }

        if (campo[row][col].isMine())
            adapter.updatePosition(row * 10 + col, R.drawable.bomb_exploded);


    }

    public void openRecursive(int row, int col) {
        Log.d("Recursive :", row + "-" + col);

        if (row > 0 && col > 0)
            openNextRecursive(row - 1, col - 1);

        if (row > 0)
            openNextRecursive(row - 1, col);

        if (row > 0 && col < (maxCol - 1))
            openNextRecursive(row - 1, col + 1);

        if (col < (maxCol - 1))
            openNextRecursive(row, col + 1);

        if (row < (maxRow - 1))
            openNextRecursive(row + 1, col);

        if (col > 0)
            openNextRecursive(row, col - 1);


        if (row < (maxRow - 1) && col > 0)
            openNextRecursive(row + 1, col - 1);

        if (row < (maxRow - 1) && col < (maxCol - 1))
            openNextRecursive(row + 1, col + 1);

    }

    public void openNextRecursive(int row, int col) {
        if (campo[row][col].isOpened()) return;

        if (campo[row][col].getNeighbourMineCount() == 0) {
            campo[row][col].setOpened(true);
            openNone(row, col);
            openRecursive(row, col);
        } else {
            campo[row][col].setOpened(true);
            open(row, col);
        }
    }

    public void openNone(int row, int col) {
        adapter.updatePosition(row * 10 + col, R.drawable.number_0);
    }

    public void flag(int row, int col) {
        if (!isStarted)
            return;

        if (!campo[row][col].isOpened()) {
            campo[row][col].setFlag(!campo[row][col].isFlag());
            if (campo[row][col].isFlag())
                adapter.updatePosition(row * 10 + col, R.drawable.flag);
            else
                adapter.updatePosition(row * 10 + col, R.drawable.button);
        }

        if (campo[row][col].isMine())
            if (campo[row][col].isFlag())
                totalMines--;
            else
                totalMines++;


    }

    /**
     * @param row
     * @param col
     * @return Return -1 if game is over, 0 if game is not ended and 1 1f player wins the game
     */
    public int checkEnd(int row, int col) {

        if (campo[row][col].isMine() && !campo[row][col].isFlag() && campo[row][col].isOpened()) {
            stopGame();
            return -1;
        }

        if (totalMines == 0) {
            stopGame();
            return 1;
        }

        return 0;
    }


}
