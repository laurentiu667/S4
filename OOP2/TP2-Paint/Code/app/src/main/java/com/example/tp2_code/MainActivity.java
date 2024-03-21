package com.example.tp2_code;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.Path;
import android.widget.TableRow;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    LinearLayout parent, choixImage;
    TableRow couleur;
    Surface surface;
    Path path;
    Ecouteur ec;
    ImageView effacer, crayon, trait, palette, pipelet, remplissage, rectangle, triangle, cercle, enregistrer, redo, undo;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        choixImage = findViewById(R.id.choixImage);
        effacer = findViewById(R.id.effacer);
        crayon = findViewById(R.id.crayon);
        trait = findViewById(R.id.trait);
        palette = findViewById(R.id.palette);
        pipelet = findViewById(R.id.pipelet);
        remplissage = findViewById(R.id.remplissage);
        rectangle = findViewById(R.id.rectangle);
        triangle = findViewById(R.id.triangle);
        cercle = findViewById(R.id.cercle);
        enregistrer = findViewById(R.id.enregistrer);
        redo = findViewById(R.id.redo);
        undo = findViewById(R.id.undo);
        couleur = findViewById(R.id.tableCouleur);


        surface = new Surface(this);
        surface.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        surface.setBackgroundColor(getResources().getColor(R.color.white));

        parent.addView(surface);

        path = new Path();
        ec = new Ecouteur();
        surface.setOnTouchListener(ec);
        couleur.setOnTouchListener(ec);


        for (int i = 0; i < choixImage.getChildCount(); i++) {
            choixImage.getChildAt(i).setOnClickListener(ec);
        }

        for (int i = 0; i < couleur.getChildCount(); i++) {
            couleur.getChildAt(i).setOnClickListener(ec);
        }


    }

    private class Ecouteur implements View.OnTouchListener, View.OnClickListener {
        private boolean remplissageActive = false;

        String hexColor = "#000000";
        @Override
        public void onClick(View v) {
            if (v instanceof Button){
                hexColor = v.getTag().toString();
                paint.setColor(Color.parseColor(hexColor));

            }
            switch (v.getTag().toString()) {
                case "crayon":
                    paint.setStyle(Paint.Style.STROKE);
                    break;
                case "effacer":
                    path.reset();
                    surface.invalidate();
                    System.out.println("Effacer");
                    break;
                case "remplissage":
                    remplissageActive = true;
                    surface.setBackgroundColor(Color.parseColor(hexColor));

                    break;
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (v instanceof Surface) {
                        path.moveTo(x, y);
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (v instanceof Surface) {
                        path.lineTo(x, y);
                        if (remplissageActive) {
                            surface.setBackgroundColor(Color.parseColor(hexColor));
                        }
                        surface.invalidate();
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (v instanceof Surface) {
                        path.lineTo(x, y);
                        surface.invalidate();
                        if (remplissageActive) {
                            remplissageActive = false;
                        }
                        return true;
                    }
                    break;
            }

            return false;
        }
    }



    public class Surface extends View {

        public Surface(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.parseColor("#000000"));
            paint.setStrokeWidth(300);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(path, paint);
        }
    }





}