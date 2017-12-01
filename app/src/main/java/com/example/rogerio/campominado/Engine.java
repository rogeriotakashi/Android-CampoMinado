package com.example.rogerio.campominado;

import android.content.Context;
import android.util.Log;

import java.util.Random;

/**
 * Created by ROGERIO on 01/12/2017.
 */

public class Engine  {
    private Cell[][] campo;
    private int totalMines;
    private int row;
    private int col;


    public Engine(int row , int col, int totalMines) {
        campo = new Cell[row][col];

        for(int i = 0; i < row; i++)
            for(int j =0 ; j < col; j++)
                campo[i][j] = new Cell();

        this.totalMines = totalMines;
        this.row = row;
        this.col = col;
    }


    public void run (){
        setRamdomMines();
        scanNeighbour();


        printCampo();
    }




    public void setRamdomMines(){
        int actualMines = 0;
        Random random = new Random();

        while(actualMines < totalMines)
        {
            int randomRow = random.nextInt(row);
            int randomCol = random.nextInt(col);

            if(! campo[randomRow][randomCol].isMine()){
                campo[randomRow][randomCol].setMine(true);
                actualMines++;
            }

        }
    }

    public void scanNeighbour(){

        for (int i = 0 ; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i > 0 && j > 0 && campo[i-1][j-1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(i > 0 && campo[i-1][j].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(i > 0 && j < (col-1) && campo[i-1][j+1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(j > 0 && campo[i][j-1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(j < (col-1) && campo[i][j+1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(i < (row-1) && j > 0 && campo[i+1][j-1].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(i < (row-1) && campo[i+1][j].isMine())
                    campo[i][j].addNeighbourMineCount();
                if(i < (row-1) && j < (col-1) && campo[i+1][j+1].isMine())
                    campo[i][j].addNeighbourMineCount();
            }
        }

    }

    public int open(int row, int col) {
        int value = campo[row][col].getNeighbourMineCount();

        if (!campo[row][col].isMine()) {
            switch (value) {
                case 0:
                    return R.drawable.number_0;
                case 1:
                    return R.drawable.number_1;
                case 2:
                    return R.drawable.number_2;
                case 3:
                    return R.drawable.number_3;
                case 4:
                    return R.drawable.number_4;
                case 5:
                    return R.drawable.number_5;
                case 6:
                    return R.drawable.number_6;
                case 7:
                    return R.drawable.number_7;
                case 8:
                    return R.drawable.number_8;
            }
        }

        return R.drawable.bomb_exploded;
    }

    public void printCampo(){
        for(int i = 0; i < row; i++)
            for(int j =0 ; j < col; j++)
                Log.i("Campo",campo[i][j].toString());


    }


}
