package com.example.code;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Partie partie;
    PileCarte pilecarte;
    Carte carte;
    Timer timer;
    Score score;
    private LinearLayout containerToutesLesCartes;
    LinearLayout ascendant1, ascendant2, descendant1, descendant2;
    TextView nbCartesRestantes, temps, scoreTotal;
    private DragDrop ecouteur;
    private Context context;
    GestionDB gestionDB;

    private MediaPlayer mediaPlayer;



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
        scoreTotal = findViewById(R.id.score);
        temps = findViewById(R.id.timer);
        ecouteur = new DragDrop();
        gestionDB = GestionDB.getInstance(context);

        //Commencer la partie
        partie = new Partie(this);
        pilecarte = new PileCarte(context ,partie.getListeCartesShuffle());

        // Ajouter les classes jouables
        partie.ajouterLesCartesJouables(containerToutesLesCartes, partie);

        // Ajouter les ecouteurs
        partie.ajouetLesEcouteurs(containerToutesLesCartes, ecouteur);

        // lancer le timer

        timer = new Timer(temps);
        timer.startTimer();

        // score

        score = new Score(context);

        mediaPlayer = MediaPlayer.create(context, R.raw.card);
        mediaPlayer.setVolume(1.5f, 1.5f);



        // Ajouter les ecouteurs sur les colonnes
        ascendant1.setOnDragListener(ecouteur);
        ascendant2.setOnDragListener(ecouteur);
        descendant1.setOnDragListener(ecouteur);
        descendant2.setOnDragListener(ecouteur);



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
                        mediaPlayer.start();
                        Integer tagCartePrecedente = Integer.valueOf((String) conteneur.getTag().toString());
                        Integer tagCarteActuelle = Integer.valueOf((String) carte.getTag().toString());

                        Integer reglePlusouMoins10 = tagCartePrecedente - tagCarteActuelle;
                        boolean laReglesEstRespectee = Math.abs(reglePlusouMoins10) == 10;

                        if (conteneur == ascendant1 || conteneur == ascendant2){

                            if (tagCartePrecedente < tagCarteActuelle || laReglesEstRespectee){

                                carte.setOnTouchListener(null);
                                carte.setOnDragListener(null);
                                conteneur.setTag(tagCarteActuelle);
                                conteneur.removeAllViews();
                                parent.removeView(carte);
                                conteneur.addView(carte);
                                score.calculerPointChaqueCartejouer();
                                scoreTotal.setText(String.valueOf(score.calculerScoreTotal()));
                                if (partie.afficherLesCartesRestantes(nbCartesRestantes) != 0){

                                    partie.ajouterUneCarte(parent, partie, ecouteur);
                                    partie.reduirePile();
                                }

                            }
                        } else if (conteneur == descendant1 || conteneur == descendant2){
                            if (tagCartePrecedente > tagCarteActuelle || laReglesEstRespectee){
                                mediaPlayer.start();
                                carte.setOnTouchListener(null);
                                carte.setOnDragListener(null);
                                conteneur.setTag(tagCarteActuelle);
                                conteneur.removeAllViews();
                                parent.removeView(carte);
                                conteneur.addView(carte);
                                score.calculerPointChaqueCartejouer();
                                scoreTotal.setText(String.valueOf(score.calculerScoreTotal()));

                                if (partie.afficherLesCartesRestantes(nbCartesRestantes) != 0){

                                    partie.ajouterUneCarte(parent, partie, ecouteur);
                                    partie.reduirePile();

                                }


                            }
                        }if (partie.partieTerminee(score, containerToutesLesCartes, ascendant1.getTag().toString(), ascendant2.getTag().toString(), descendant1.getTag().toString(), descendant2.getTag().toString())){

                           Intent intent = new Intent(context, BeginPage.class);
                           startActivity(intent);
                       }



                   }
                   break;
            }
            return true;
        }

    }

}

