package com.example.tp2_code.Outils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Forme {
    private int x, y, x2, y2;

    public Rectangle(int couleur, int largeur, int x, int y, int x2, int y2) {
        super(couleur, largeur);
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setCoordonnees(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void dessiner(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(couleur);
        paint.setStrokeWidth(largeur);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x2, y2, paint);
    }
}
