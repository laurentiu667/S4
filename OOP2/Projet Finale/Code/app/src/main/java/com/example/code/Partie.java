package com.example.code;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partie {

    final int NB_CARTES = 97;
    private ArrayList<Integer> listeCartesShuffle = new ArrayList<Integer>();
    private ArrayList<Integer> listeCarteMain = new ArrayList<Integer>();
    private PileCarte pileCarte;
    Context context;
    int nblinear = 0;
    GestionDB gestionDB;


    public Partie(Context context) {
        // Créer un array de nombres de 1 à 97
        for (int i = 1; i <= NB_CARTES; i++) {
            listeCartesShuffle.add(i);
        }
        this.context = context;
        // Mélanger l'array
        shuffleCarte();
        // Créer la pile de cartes avec l'array mélangé
        pileCarte = new PileCarte(context ,listeCartesShuffle);
        gestionDB = new GestionDB(this.context);

    }

    public void shuffleCarte() {
        Collections.shuffle(listeCartesShuffle);
    }

    public boolean aucuneCarteJouable(LinearLayout containerToutesLesCartes, String ascendant1, String ascendant2, String descendant1, String descendant2) {
        listeCarteMain.clear();
        Integer tagAscendant1 = Integer.valueOf(ascendant1);
        Integer tagAscendant2 = Integer.valueOf(ascendant2);
        Integer tagDescendant1 = Integer.valueOf(descendant1);
        Integer tagDescendant2 = Integer.valueOf(descendant2);


        // Ajouter les ecouteurs sur les cartes
        for (int i = 0; i < containerToutesLesCartes.getChildCount(); i++) {
            LinearLayout colone = (LinearLayout) containerToutesLesCartes.getChildAt(i);

            for (int j = 0; j < colone.getChildCount(); j++) {
                LinearLayout colonne2 = (LinearLayout) colone.getChildAt(j);
                for (int k = 0; k < colonne2.getChildCount(); k++) {
                    LinearLayout carte = (LinearLayout) colonne2.getChildAt(k);
                    listeCarteMain.add((Integer) carte.getTag());

                }
            }
        }
        System.out.println("ascendant1 : " + tagAscendant1 + " ascendant2 : " + tagAscendant2 + " descendant1 : " + tagDescendant1 + " descendant2 : " + tagDescendant2);
        for (Integer carte : listeCarteMain) {
            System.out.println("carte : " + carte);
        }

        for (Integer carte : listeCarteMain) {
            boolean laReglesEstRespectee = Math.abs(carte - tagAscendant1) == 10 || Math.abs(carte - tagAscendant2) == 10 || Math.abs(carte - tagDescendant1) == 10 || Math.abs(carte - tagDescendant2) == 10;
            if (carte > tagAscendant1 || laReglesEstRespectee || carte > tagAscendant2 || laReglesEstRespectee || carte < tagDescendant1 || laReglesEstRespectee || carte < tagDescendant2 || laReglesEstRespectee) {
                System.out.println("au moins une carte est jouable");
                return false; // au moins une carte est jouable
            }
        }

        return true; // aucune carte jouable

    }


    public boolean partieTerminee(Score score, LinearLayout containerToutesLesCartes, String ascendant1, String ascendant2, String descendant1, String descendant2) {

        if ( aucuneCarteJouable(containerToutesLesCartes, ascendant1, ascendant2, descendant1, descendant2)) {

            return true; // partie terminée
        }
        return false; // partie non terminée
    }

    public boolean perdu_0carte_0dispo(LinearLayout containerToutesLesCartes){
        if (compterLinear(containerToutesLesCartes) == 0){

            return true;
        }
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
                    System.out.println("tag des cartes : " + carte.getTag());

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

        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(String.valueOf(nombrePourCarte));


        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(14, 5, 0, 0);
        textView.setTextSize(14);

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

                    colonne2.addView(nouveauLayout);
                    reduirePile();

                    TextView textView = new TextView(this.context);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setText(String.valueOf(nombrePourCarte));


                    textView.setTextColor(Color.WHITE);
                    textView.setTypeface(null, Typeface.BOLD);
                    textView.setPadding(14, 5, 0, 0);
                    textView.setTextSize(14);


                    nouveauLayout.addView(textView);

                }
            }
        }
    }
    public void reduirePile(){
        pileCarte.listeCartes.remove(pileCarte.listeCartes.size()-1);

    }

    public int afficherLesCartesRestantes(TextView nbCartesRestantes){
        nbCartesRestantes.setText(String.valueOf(pileCarte.nbCartesRestantes()));
        return pileCarte.nbCartesRestantes();
    }

}
