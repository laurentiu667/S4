package com.example.paintmdr;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class paint {

    private Path path;

    public paint() {
        path = new Path();

    }

    public void moveTo(float x, float y) {
        path.moveTo(x, y);
    }

    public void lineTo(float x, float y) {
        path.lineTo(x, y);
    }

    public void dessiner(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawPath(path, paint);
    }
}
