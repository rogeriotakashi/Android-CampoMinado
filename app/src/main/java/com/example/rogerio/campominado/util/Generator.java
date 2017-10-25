package com.example.rogerio.campominado.util;

import java.util.Random;

/**
 * Created by ROGERIO on 19/10/2017.
 */

public class Generator
{

    public static int [][] generate (int bombNumber,final int width,final int height)
    {

        Random r = new Random();

        int[][] grid = new int[width][height];

        for(int i = 0; i < width;i++)
        {
            grid[i] = new int [height];
        }

        while(bombNumber > 0)
        {
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            //-1 is the bomb
            if(grid[x][y] != -1)
            {
                grid[x][y] = -1;
                bombNumber--;
            }
        }

        grid = scanNeigbours(grid,width,height);

        return grid;
    }

    public static int [][]  scanNeigbours(int [][] grid,final int width, final int height)
    {
        for(int i = 0; i < width;i++)
        {
            for(int j = 0; j < height;j++)
            {
                grid[i][j] = getNeigbourNumber(grid,i,j,width,height);
            }

        }

        return grid;
    }

    public static int getNeigbourNumber(final int [][] grid,final int x, final int y,final int width, final int height)
    {
        if(grid[x][y] == -1)
        {
            return -1;
        }

        int count = 0;


        if(isMineAt(grid , x - 1, y + 1, width, height))count++;
        if(isMineAt(grid , x    , y + 1, width, height))count++;
        if(isMineAt(grid , x + 1, y + 1, width, height))count++;

        if(isMineAt(grid , x - 1, y, width, height))count++;
        if(isMineAt(grid , x + 1, y, width, height))count++;

        if(isMineAt(grid , x - 1, y - 1, width, height))count++;
        if(isMineAt(grid , x    , y - 1, width, height))count++;
        if(isMineAt(grid , x + 1, y - 1, width, height))count++;

        return count;
    }

    private static boolean isMineAt(final int[][]grid,final int x, final int y,final int width, final int height)
    {
        if(x >= 0 && y >= 0 && x < width && y < height)
        {
            if(grid[x][y] == -1)
            {
                return true;
            }
        }

        return false;
    }
}
