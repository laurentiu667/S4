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
    int nblinear = 0;

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
        ajouterLesCartesJouables();

        // Ajouter les ecouteurs
        ajouetLesEcouteurs();


        // Ajouter les ecouteurs sur les colonnes
        ascendant1.setOnDragListener(ecouteur);
        ascendant2.setOnDragListener(ecouteur);
        descendant1.setOnDragListener(ecouteur);
        descendant2.setOnDragListener(ecouteur);



        System.out.println("Nombre de linear : " + nblinear);



        afficherLesCartesRestantes();


    }



    public class DragDrop implements View.OnDragListener, View.OnTouchListener {
        View carte = null;

        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, shadowBuilder, v, 0);
            v.setVisibility(View.VISIBLE);
            nblinear = 0;

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
                        afficherLesCartesRestantes();
                        conteneur.addView(carte);

                        compterLinear();

                        if (parent.getChildCount() == 0) { // Vérifie si le parent est vide
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ajouterUneCarte(parent);
                                }
                            }, 1000);
                        }
                    }
                    break;
            }
            return true;
        }

    }
    public void ajouetLesEcouteurs(){
        // Ajouter les ecouteurs sur les cartes
        for (int i = 0; i < containerToutesLesCartes.getChildCount(); i++) {
            LinearLayout colone = (LinearLayout) containerToutesLesCartes.getChildAt(i);

            for (int j = 0; j < colone.getChildCount(); j++) {
                LinearLayout colonne2 = (LinearLayout) colone.getChildAt(j);
                for (int k = 0; k < colonne2.getChildCount(); k++) {
                    LinearLayout carte = (LinearLayout) colonne2.getChildAt(k);
                    carte.setOnDragListener(ecouteur);
                    carte.setOnTouchListener(ecouteur);

                    nblinear++;
                }
            }
        }
    }
    public void ajouterUneCarte(LinearLayout parent) {
        LinearLayout nouveauLayout = new LinearLayout(this);
        nouveauLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));


        int nombrePourCarte = partie.getListeCartesShuffle().get(0);
        nouveauLayout.setTag(nombrePourCarte);
        partie.getListeCartesShuffle().remove(0);
        nouveauLayout.setOnTouchListener(ecouteur);
        nouveauLayout.setOnDragListener(ecouteur);
        parent.addView(nouveauLayout);

        if (nombrePourCarte >= 1 && nombrePourCarte <= 25){
            nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte1a25));
        } else if (nombrePourCarte >= 25 && nombrePourCarte <=50) {
            nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte26a50));
        } else if (nombrePourCarte >= 51 && nombrePourCarte <= 75) {
            nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte51a75));
        } else if (nombrePourCarte >= 76 && nombrePourCarte <= 98){
            nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte76a98));
        }

        // Create a new TextView
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(String.valueOf(nombrePourCarte));


        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(10, 3, 0, 0);
        textView.setTextSize(16);

        nouveauLayout.addView(textView);
    }
    public int compterLinear(){
        for (int i = 0; i < containerToutesLesCartes.getChildCount(); i++) {
            LinearLayout colone = (LinearLayout) containerToutesLesCartes.getChildAt(i);

            for (int j = 0; j < colone.getChildCount(); j++) {
                LinearLayout colonne2 = (LinearLayout) colone.getChildAt(j);
                for (int k = 0; k < colonne2.getChildCount(); k++) {
                    LinearLayout carte = (LinearLayout) colonne2.getChildAt(k);
                    nblinear++;
                }
            }
        }
        return nblinear;
    }
    public void ajouterLesCartesJouables(){
        // Aajouter les cartes dans les colonnes
        for (int i = 0; i < containerToutesLesCartes.getChildCount(); i++) {
            LinearLayout colone = (LinearLayout) containerToutesLesCartes.getChildAt(i);

            for (int j = 0; j < colone.getChildCount(); j++) {
                LinearLayout colonne2 = (LinearLayout) colone.getChildAt(j);

                // Vérifier si colonne2 a des enfants
                if (colonne2.getChildCount() == 0 && colonne2 != null) {

                    int nombrePourCarte = partie.getListeCartesShuffle().get(0);
                    LinearLayout nouveauLayout = new LinearLayout(this);
                    nouveauLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));

                    nouveauLayout.setTag(nombrePourCarte);

                    if (nombrePourCarte >= 1 && nombrePourCarte <= 25){
                        nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte1a25));
                    } else if (nombrePourCarte >= 25 && nombrePourCarte <=50) {
                        nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte26a50));
                    } else if (nombrePourCarte >= 51 && nombrePourCarte <= 75) {
                        nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte51a75));
                    } else if (nombrePourCarte >= 76 && nombrePourCarte <= 98){
                        nouveauLayout.setBackground(getResources().getDrawable(R.drawable.carte76a98));
                    }
                    partie.getListeCartesShuffle().remove(0);
                    pilecarte.reduirePile();
                    colonne2.addView(nouveauLayout);

                    // Create a new TextView
                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setText(String.valueOf(nombrePourCarte));


                    textView.setTextColor(Color.BLACK);
                    textView.setTypeface(null, Typeface.BOLD);
                    textView.setPadding(10, 3, 0, 0);
                    textView.setTextSize(16);

                    nouveauLayout.addView(textView);

                }
            }
        }
    }

    public void afficherLesCartesRestantes(){
        nbCartesRestantes.setText(String.valueOf(pilecarte.nbCartesRestantes()));
    }
}