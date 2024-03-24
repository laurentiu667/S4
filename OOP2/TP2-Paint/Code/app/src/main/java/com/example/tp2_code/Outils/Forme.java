package com.example.tp2_code.Outils;

import android.graphics.Canvas;

public abstract class Forme {
    protected int couleur;
    protected int largeur;

    public Forme(int couleur, int largeur) {
        this.couleur = couleur;
        this.largeur = largeur;
    }


    public int getCouleur() {
        return couleur;
    }

    public int getLargeur() {
        return largeur;
    }

    public abstract void dessiner(Canvas canvas);



}