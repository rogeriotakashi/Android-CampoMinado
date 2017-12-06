package com.example.rogerio.campominado.model;

/**
 * Created by ROGERIO on 06/12/2017.
 */

public class PersonalRecord {
    private String nickname;
    private String time;
    private String difficulty;

    public PersonalRecord(String nickname, String time, String difficulty) {
        this.nickname = nickname;
        this.time = time;
        this.difficulty = difficulty;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }


}
