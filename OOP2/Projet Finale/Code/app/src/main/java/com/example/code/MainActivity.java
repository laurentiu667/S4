package com.example.code;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Partie partie;
    PileCarte pilecarte;
    Carte carte;
    private LinearLayout containerToutesLesCartes;
    LinearLayout ascendant1, ascendant2, descendant1, descendant2;
    TextView nbCartesRestantes;
    private DragDrop ecouteur;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        containerToutesLesCartes = findViewById(R.id.global8cards);
        ascendant1 = findViewById(R.id.ascendant1);
        ascendant2 = findViewById(R.id.ascendant2);
        descendant1 = findViewById(R.id.descendant1);
        descendant2 = findViewById(R.id.descendant2);
        nbCartesRestantes = findViewById(R.id.nbCartes);
        ecouteur = new DragDrop();

        //Commencer la partie
        partie = new Partie(context);
        pilecarte = new PileCarte(context ,partie.getListeCartesShuffle());

        // Ajouter les classes jouables
        partie.ajouterLesCartesJouables(containerToutesLesCartes, partie);

        // Ajouter les ecouteurs
        partie.ajouetLesEcouteurs(containerToutesLesCartes, ecouteur);


        // Ajouter les ecouteurs sur les colonnes
        ascendant1.setOnDragListener(ecouteur);
        ascendant2.setOnDragListener(ecouteur);
        descendant1.setOnDragListener(ecouteur);
        descendant2.setOnDragListener(ecouteur);



        System.out.println("Nombre de linear : " + partie.compterLinear(containerToutesLesCartes));

        partie.afficherLesCartesRestantes(nbCartesRestantes);


    }



    public class DragDrop implements View.OnDragListener, View.OnTouchListener {
        View carte = null;

        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, shadowBuilder, v, 0);
            v.setVisibility(View.VISIBLE);
            partie.nblinear = 0;

            return true;
        }

        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    carte = (View) event.getLocalState();
                    LinearLayout parent = (LinearLayout) carte.getParent();
                    LinearLayout conteneur = (LinearLayout) v;

                    if (conteneur == ascendant1 || conteneur == ascendant2 || conteneur == descendant1 || conteneur == descendant2){
                        parent.removeView(carte);
                        pilecarte.reduirePile();
                        partie.afficherLesCartesRestantes(nbCartesRestantes);
                        conteneur.addView(carte);

                        partie.compterLinear(containerToutesLesCartes);

                        if (parent.getChildCount() == 0) { // Vérifie si le parent est vide
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    partie.ajouterUneCarte(parent, partie, ecouteur);
                                }
                            }, 1000);
                        }
                    }
                    break;
            }
            return true;
        }

    }

}