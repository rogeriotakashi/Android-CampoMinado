package com.example.rogerio.campominado.util;

import android.util.Log;

/**
 * Created by ROGERIO on 24/10/2017.
 */

public class PrintGrid {

    public static void print (final int [][] grid, final int width,final int height)
    {
        String printedText ="";

        for(int x = 0; x < width ; x++)
        {
            printedText = "| ";
            for(int y = 0; y < height ; y ++)
            {
                printedText += String.valueOf(grid[x][y]).replace("-1","B") + " | ";
            }

            Log.e("",printedText);
        }

    }
}
