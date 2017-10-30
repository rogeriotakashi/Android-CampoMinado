package com.example.rogerio.campominado.leaderboard;

/**
 * Created by ROGERIO on 30/10/2017.
 */

public class Player_item {

    private String nickname;
    private String time;

    public Player_item(String nickname, String time) {
        this.nickname = nickname;
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
