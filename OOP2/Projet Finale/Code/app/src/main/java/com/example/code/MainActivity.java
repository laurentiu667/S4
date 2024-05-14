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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    LinearLayout ascendant1, ascendant2, descendant1, descendant2, menu_button;
    TextView nbCartesRestantes, temps, scoreTotal;
    private DragDrop ecouteur;
    private Ecouteur ecouteurClick;
    private Context context;
    GestionDB gestionDB;
    Menu menu;

    private MediaPlayer mediaPlayer;
    Animation slideInAnimation;

    WinLoose winLoose;



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
        menu_button = findViewById(R.id.menubutton);
        ecouteur = new DragDrop();
        ecouteurClick = new Ecouteur();
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

        // menu
        menu = new Menu(context);

        // animation
        slideInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in);

        // win ou loose
        winLoose = new WinLoose(context);


        // Ajouter les ecouteurs sur les colonnes
        ascendant1.setOnDragListener(ecouteur);
        ascendant2.setOnDragListener(ecouteur);
        descendant1.setOnDragListener(ecouteur);
        descendant2.setOnDragListener(ecouteur);
        menu_button.setOnClickListener(ecouteurClick);



        partie.afficherLesCartesRestantes(nbCartesRestantes);

    }

    public void showMenu(){

        menu.show();
        menu.quittermenu.startAnimation(slideInAnimation);
        menu.nouvellepartie.startAnimation(slideInAnimation);
        menu.classementmenu.startAnimation(slideInAnimation);
        menu.quitterjeux.startAnimation(slideInAnimation);
    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v instanceof LinearLayout){
                showMenu();
            }
        }
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
//


                            }
                        }
                       if (partie.perdu_0carte_0dispo(containerToutesLesCartes)) {
                           gestionDB.ajouter_new_score(score);
                           winLoose.setMessage("gagné");
                           winLoose.show();
                           if (winLoose.isShowing()){
                               Handler handler = new Handler();
                               handler.postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Intent intent = new Intent(context, BeginPage.class);
                                       context.startActivity(intent);
                                   }
                               }, 5000);
                           }
                       } else if (partie.partieTerminee(score, containerToutesLesCartes, ascendant1.getTag().toString(), ascendant2.getTag().toString(), descendant1.getTag().toString(), descendant2.getTag().toString())){
                           gestionDB.ajouter_new_score(score);
                           winLoose.setMessage("perdu");
                           winLoose.show();
                           if (winLoose.isShowing()){
                               Handler handler = new Handler();
                               handler.postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Intent intent = new Intent(context, BeginPage.class);
                                       context.startActivity(intent);
                                   }
                               }, 5000);

                           }
                       }



                   }
                   break;
            }
            return true;
        }

    }

}

