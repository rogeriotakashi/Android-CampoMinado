package com.example.rogerio.campominado.settings;


/**
 * Created by ROGERIO on 01/11/2017.
 */

public final class GameSettings {

    public static int GAME_DIFFICULT = 0; // NORMAL AS DEFAULT


    public static int getBombQuantityByDifficult(){

        switch (GAME_DIFFICULT){
            // NORMAL
            case 0:
                return 10;
            // HARD
            case 1:
                return 20;

            // INSANE
            case 2:
                return 30;
        }
        return 0;
    }

}
