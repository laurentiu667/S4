package com.example.code;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partie {

    private ArrayList<Integer> listeCartesShuffle = new ArrayList<Integer>();
    private PileCarte pileCarte;
    Context context;

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
}
