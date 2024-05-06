package com.example.code;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class PileCarte {
    private Context context;

    public ArrayList<Carte> listeCartes = new ArrayList<Carte>();


    public PileCarte(Context context, ArrayList<Integer> numerosCartes) {
        for (Integer numero : numerosCartes) {
            listeCartes.add(new Carte(numero));
        }
        this.context = context;

    }

    public void afficherPile() {
        for (Carte carte : listeCartes) {
            System.out.println("carte : " + carte.getNombre());
        }
    }
    public void reduirePile(){
        listeCartes.remove(listeCartes.size()-1);
    }
    public int nbCartesRestantes(){
        return listeCartes.size();
    }


}
