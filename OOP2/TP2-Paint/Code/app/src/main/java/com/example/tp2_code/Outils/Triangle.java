package com.example.tp2_code.Outils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
public class Triangle extends Forme {
    private int x1, y1, x2, y2, x3, y3;

    public Triangle(int couleur, int largeur, int x1, int y1, int x2, int y2) {
        super(couleur, largeur);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setCoordonnees(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setThirdVertex(int x3, int y3) {
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void dessiner(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(couleur);
        paint.setStrokeWidth(largeur);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
        canvas.drawPath(path, paint);
    }
}