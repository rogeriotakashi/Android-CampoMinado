package com.example.rogerio.campominado.settings;


/**
 * Created by ROGERIO on 01/11/2017.
 */

public class GameSettings {

    public static int GAME_DIFFICULT = 2; // NORMAL AS DEFAULT

    public static int getBombQuantityByDifficult(){
        switch (GAME_DIFFICULT){
            // Easy
            case 1:
               return 1;
            // NORMAL
            case 2:
                return 10;
            // HARD
            case 3:
                return 15;
            // INSANE
            case 4:
                return 23;
        }
        return 0;
    }

    public static String getGameDifficult(){
        switch (GAME_DIFFICULT){
            case 1:
                return "Super Easy";
            case 2:
                return "Normal";
            case 3:
                return "Hard";
            case 4:
                return "Insane";
        }
        return "";
    }
}
