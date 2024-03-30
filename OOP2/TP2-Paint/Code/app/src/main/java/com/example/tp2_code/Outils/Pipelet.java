package com.example.tp2_code.Outils;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Pipelet extends Forme {
    private Bitmap bitmapImage;

    public Pipelet(int couleur, int largeur, Bitmap bitmapImage) {
        super(couleur, largeur);
        this.bitmapImage = bitmapImage;
    }

    @Override
    public void dessiner(Canvas canvas) {

    }

    public int pickColor(int x, int y) {
        return bitmapImage.getPixel(x, y);
    }
}