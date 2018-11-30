package com.example.adrien.morpion;

import java.io.Serializable;

public class Player implements Serializable {

    private String nomPlayer;
    private int scorePlayer = 0;

    public Player(String i) {
        this.nomPlayer = i;
    }

    public String getNomPlayer(){return nomPlayer;}
    public int getScorePlayer(){return scorePlayer;}

    public void AjouterScore() {
        this.scorePlayer += 1;
    }

    public void ResetScore(){
        this.scorePlayer = 0;
    }



}
