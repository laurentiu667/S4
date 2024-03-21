package com.example.tp2_code;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.SeekBar;
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
    String hexColor = "#000000";
    int traitNombre;

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

                    break;
                case "trait":

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    SeekBar seekBar = new SeekBar(MainActivity.this);
                    seekBar.setMax(50);
                    seekBar.setProgress(5);
                    builder.setView(seekBar);
                    // mettre la valeur de la seekbar dans la variable traitNombre
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            traitNombre = progress;
                            paint.setStrokeWidth(traitNombre);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }


                    });

                    builder.setTitle("Taille du trait");

                    builder.setPositiveButton("Changer", null);
                    builder.setNegativeButton("Annuler", null);
                    builder.show();
                    break;

            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x, y);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x, y);
                    surface.invalidate();
                    return true;
                case MotionEvent.ACTION_UP:
                    if (remplissageActive) {
                        surface.setBackgroundColor(Color.parseColor(hexColor));
                        remplissageActive = false;
                    }

                    return true;
            }
            return false;
        }

    }



    public class Surface extends View {

        public Surface(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.parseColor(hexColor));
            paint.setStrokeWidth(traitNombre);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(path, paint);
        }
    }





}