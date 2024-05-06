package com.example.code;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partie {

    private ArrayList<Integer> listeCartesShuffle = new ArrayList<Integer>();
    private PileCarte pileCarte;
    Context context;
    int nblinear = 0;

    public Partie(Context context) {
        // Créer un array de nombres de 1 à 97
        for (int i = 1; i <= 97; i++) {
            listeCartesShuffle.add(i);
        }
        this.context = context;
        // Mélanger l'array
        shuffleCarte();
        // Créer la pile de cartes avec l'array mélangé
        pileCarte = new PileCarte(context ,listeCartesShuffle);
    }

    public void shuffleCarte() {
        Collections.shuffle(listeCartesShuffle);
    }

    public boolean partieTerminee() {
        return false;
    }

    public ArrayList<Integer> getListeCartesShuffle() {
        return listeCartesShuffle;
    }

    public int ajouetLesEcouteurs(LinearLayout containerToutesLesCartes, MainActivity.DragDrop ecouteur){
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
        return nblinear;
    }
    public void ajouterUneCarte(LinearLayout parent, Partie partie, MainActivity.DragDrop ecouteur) {
        LinearLayout nouveauLayout = new LinearLayout(this.context);
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
            nouveauLayout.setBackground(context.getDrawable(R.drawable.carte1a25));
        } else if (nombrePourCarte >= 25 && nombrePourCarte <=50) {
            nouveauLayout.setBackground(context.getDrawable(R.drawable.carte26a50));
        } else if (nombrePourCarte >= 51 && nombrePourCarte <= 75) {
            nouveauLayout.setBackground(context.getDrawable(R.drawable.carte51a75));
        } else if (nombrePourCarte >= 76 && nombrePourCarte <= 98){
            nouveauLayout.setBackground(context.getDrawable(R.drawable.carte76a98));
        }

        // Create a new TextView
        TextView textView = new TextView(this.context);
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
    public int compterLinear(LinearLayout containerToutesLesCartes){
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
    public void ajouterLesCartesJouables(LinearLayout containerToutesLesCartes, Partie partie){
        // Aajouter les cartes dans les colonnes
        for (int i = 0; i < containerToutesLesCartes.getChildCount(); i++) {
            LinearLayout colone = (LinearLayout) containerToutesLesCartes.getChildAt(i);

            for (int j = 0; j < colone.getChildCount(); j++) {
                LinearLayout colonne2 = (LinearLayout) colone.getChildAt(j);

                // Vérifier si colonne2 a des enfants
                if (colonne2.getChildCount() == 0 && colonne2 != null) {

                    int nombrePourCarte = partie.getListeCartesShuffle().get(0);
                    LinearLayout nouveauLayout = new LinearLayout(this.context);
                    nouveauLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));

                    nouveauLayout.setTag(nombrePourCarte);

                    if (nombrePourCarte >= 1 && nombrePourCarte <= 25){
                        nouveauLayout.setBackground(context.getDrawable(R.drawable.carte1a25));
                    } else if (nombrePourCarte >= 25 && nombrePourCarte <=50) {
                        nouveauLayout.setBackground(context.getDrawable(R.drawable.carte26a50));
                    } else if (nombrePourCarte >= 51 && nombrePourCarte <= 75) {
                        nouveauLayout.setBackground(context.getDrawable(R.drawable.carte51a75));
                    } else if (nombrePourCarte >= 76 && nombrePourCarte <= 98){
                        nouveauLayout.setBackground(context.getDrawable(R.drawable.carte76a98));
                    }
                    partie.getListeCartesShuffle().remove(0);
                    pileCarte.reduirePile();
                    colonne2.addView(nouveauLayout);

                    // Create a new TextView
                    TextView textView = new TextView(this.context);
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

    public void afficherLesCartesRestantes(TextView nbCartesRestantes){
        nbCartesRestantes.setText(String.valueOf(pileCarte.nbCartesRestantes()));
    }
}
