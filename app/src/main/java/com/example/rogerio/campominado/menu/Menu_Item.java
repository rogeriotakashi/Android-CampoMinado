package com.example.rogerio.campominado.menu;

/**
 * Created by ROGERIO on 01/09/2017.
 */

public class Menu_Item {

    private String icon;

    public Menu_Item(String icon, String nome) {
        this.icon = icon;
        this.nome = nome;
    }

    private String nome;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }




}
