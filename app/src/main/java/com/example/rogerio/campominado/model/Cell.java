package com.example.rogerio.campominado.model;

/**
 * Created by ROGERIO on 01/12/2017.
 */

public class Cell {

    private boolean isOpened;
    private boolean isFlag;
    private boolean isMine;
    private int neighbourMineCount;

    public Cell() {

        this.isOpened = false;
        this.isFlag = false;
        this.isMine = false;
        this.neighbourMineCount = 0;

    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        this.neighbourMineCount = neighbourMineCount;
    }

    public void addNeighbourMineCount() {
        this.neighbourMineCount++;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "isOpened=" + isOpened +
                ", isFlag=" + isFlag +
                ", isMine=" + isMine +
                ", neighbourMineCount=" + neighbourMineCount +
                '}';
    }
}
