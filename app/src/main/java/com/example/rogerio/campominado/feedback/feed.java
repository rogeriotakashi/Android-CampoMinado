package com.example.rogerio.campominado.feedback;

/**
 * Created by ROGERIO on 07/11/2017.
 */

public class feed {
    public String name;
    public String email;
    public String rate;
    public String comment;

    public feed() {

    }

    public feed(String name, String email, String rate, String comment) {
        this.name = name;
        this.email = email;
        this.rate = rate;
        this.comment = comment;
    }
}
