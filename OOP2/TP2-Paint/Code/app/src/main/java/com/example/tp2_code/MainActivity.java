package com.example.tp2_code;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

import com.example.tp2_code.Outils.Background;
import com.example.tp2_code.Outils.Efface;
import com.example.tp2_code.Outils.Forme;
import com.example.tp2_code.Outils.Ovale;
import com.example.tp2_code.Outils.Pipelet;
import com.example.tp2_code.Outils.Rectangle;
import com.example.tp2_code.Outils.TailleTrait;
import com.example.tp2_code.Outils.Trait;
import com.example.tp2_code.Outils.Triangle;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout parent, choixImage;
    TableRow couleur;
    Surface surface;
    String hexColor = "#000000";
    Forme forme;

    Rectangle rectangle;
    Triangle triangle;
    Ovale ovale;
    Efface efface;
    TailleTrait tailleTrait;
    Pipelet pipelet;
    trait trait;
    Background bg;

    Ecouteur ec;
    List<Forme> listeFormes = new ArrayList<>();
    List<Forme> listeFormesEfface = new ArrayList<>();
    boolean cliquer = false;

    ColorDrawable color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        couleur = findViewById(R.id.tableCouleur);
        choixImage = findViewById(R.id.choixImage);
        trait = new trait(this);
        forme = new Trait(Color.parseColor(hexColor), recupererTaille());


        surface = new Surface(this);
        surface.setBackgroundColor(Color.parseColor("#FFFFFF"));
        parent.addView(surface);

        ec = new Ecouteur();

        surface.setOnTouchListener(ec);



        for (int i = 0; i < couleur.getChildCount(); i++) {
            couleur.getChildAt(i).setOnClickListener(ec);
        }

        for (int i = 0; i < choixImage.getChildCount(); i++) {
            choixImage.getChildAt(i).setOnClickListener(ec);
        }


    }

    private void dialog(){
        trait.show();
    }
    private int recupererTaille(){
        try {
            return trait.retournerTaille();
        } catch (Exception e){
            return 20;
        }

    }
    private class Ecouteur implements View.OnTouchListener, View.OnClickListener {



        private int x1, y1, x2, y2, x3, y3;


        @Override
        public void onClick(View v) {
            color = (ColorDrawable) surface.getBackground();


            if (v instanceof Button) {
                hexColor = v.getTag().toString();
            } else if (v instanceof ImageView) {
                switch (v.getId()) {
                    case R.id.remplissage:
                        bg = new Background();
                        bg.setHexColor(hexColor);
                        bg.dessiner(surface);
                        break;
                    case R.id.effacer:
                        forme = new Efface(color.getColor(), recupererTaille());
                        break;
                    case R.id.crayon:
                        forme = new Trait(Color.parseColor(hexColor), recupererTaille());

                        break;
                    case R.id.rectangle:
                        forme = new Rectangle(Color.parseColor(hexColor), recupererTaille(), 0, 0, 0, 0);
                        break;
                    case R.id.triangle:
                        forme = new Triangle(Color.parseColor(hexColor), recupererTaille(), 0, 0, 0, 0);
                        break;
                    case R.id.ovale:
                        forme = new Ovale(Color.parseColor(hexColor), recupererTaille(), 0, 0, 0, 0);
                        break;
                    case R.id.trait:
                        dialog();
                        break;
                    case R.id.undo:
                        if (listeFormes.size() > 0) {
                            int lastIndex = listeFormes.size() - 1;
                            listeFormesEfface.add(listeFormes.get(lastIndex));
                            listeFormes.remove(lastIndex);
                            surface.invalidate();
                        }
                        break;
                    case R.id.redo:
                        if (listeFormesEfface.size() > 0) {
                            int lastIndex = listeFormesEfface.size() - 1;
                            listeFormes.add(listeFormesEfface.get(lastIndex));
                            listeFormesEfface.remove(lastIndex);
                            surface.invalidate();
                        }
                        break;
                }
            }
        }



        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    x1 = (int) event.getX();
                    y1 = (int) event.getY();

                    if (forme instanceof Trait) {
                        forme = new Trait(Color.parseColor(hexColor), recupererTaille());
                        ((Trait) forme).move_to(x1, y1);

                    } else if (forme instanceof Rectangle) {
                        forme = new Rectangle(Color.parseColor(hexColor), recupererTaille(), x1, y1, x1, y1);
                    } else if (forme instanceof Ovale) {
                        forme = new Ovale(Color.parseColor(hexColor), recupererTaille(), x1, y1, x1, y1);
                    } else if (forme instanceof Efface) {
                        forme = new Efface(color.getColor(), recupererTaille());
                        ((Efface) forme).move_to(x1, y1);
                    }

                    listeFormes.add(forme);
                    surface.invalidate();

                    return true;
                case MotionEvent.ACTION_MOVE:
                    x2 = (int) event.getX();
                    y2 = (int) event.getY();

                    if (forme instanceof Trait) {
                        ((Trait) forme).line_to(x2, y2);
                    } else if (forme instanceof Rectangle) {
                        ((Rectangle) forme).setCoordonnees(x1, y1, x2, y2);
                    } else if (forme instanceof Ovale) {
                        ((Ovale) forme).setCoordonnees(x1, y1, x2, y2);
                    } else if (forme instanceof Efface) {
                        ((Efface) forme).line_to(x2, y2);
                    }
                    surface.invalidate();

                    return true;
                case MotionEvent.ACTION_UP:



                    return true;
            }
            return false;
        }


    }


    public class Surface extends View {

        public Surface(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (Forme forme : listeFormes) {

                if (forme instanceof Efface) {
                    // changer la couleur de l efface en fonction de la couleur du fond
                    forme.setCouleur(color.getColor());
                }

                forme.dessiner(canvas);
            }


            System.out.println(listeFormes.size());
        }
    }





}