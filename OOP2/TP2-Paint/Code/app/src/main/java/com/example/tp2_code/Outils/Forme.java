package com.example.tp2_code.Outils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Surface;

import com.example.tp2_code.MainActivity;

public abstract class Forme {
    protected int couleur;
    protected int largeur;


    public Forme(int couleur, int largeur) {
        this.couleur = couleur;
        this.largeur = largeur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public int getCouleur() {
        return couleur;
    }

    public int getLargeur() {
        return largeur;
    }

    public abstract void dessiner(Canvas canvas);


}