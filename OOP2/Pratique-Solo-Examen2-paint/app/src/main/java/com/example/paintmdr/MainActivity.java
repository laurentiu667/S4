package com.example.paintmdr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    Surface surface;
    Ecouteur ecouteur;
    paint paint = new paint();

    LinearLayout surfaceLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceLayout = findViewById(R.id.surface);
        ecouteur = new Ecouteur();
        surface = new Surface(this);
        surface.setOnTouchListener(ecouteur);
        surfaceLayout.addView(surface);


    }

    private class Ecouteur implements View.OnClickListener, View.OnTouchListener {


        @Override
        public void onClick(View v) {


        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                paint.moveTo(event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                paint.lineTo(event.getX(), event.getY());
            }
            v.invalidate();
            return true;
        }
    }

    public class Surface extends View {
        public Surface(MainActivity mainActivity) {
            super(mainActivity);
        }

        public void onDraw(Canvas c) {
            paint.dessiner(c);
        }
    }




}