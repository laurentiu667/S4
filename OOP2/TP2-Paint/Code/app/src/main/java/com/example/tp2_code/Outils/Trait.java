package com.example.tp2_code.Outils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Trait extends Forme{

    private Path path;

    public Trait(int color, int largeur) {
        super(color, largeur);
        path = new Path();
    }

    public void move_to(float x, float y){
        path.moveTo(x, y);
    }

    public void line_to(float x, float y) {
        path.lineTo(x, y);
    }

    @Override
    public void dessiner(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getCouleur());
        paint.setStrokeWidth(getLargeur());
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
    }




}
