package com.example.code;

import android.content.Context;

public class Score {
    //Le pointage doit être fonction :
    //
    //•	La proximité de la carte jouée de la carte sur la suite
    //•	La vitesse avec laquelle on a joué la carte
    //•	Le nombre de cartes restantes ( plus de points à mesure qu’on avance dans le jeu )

    public int pointage;
    public int temps;
    public int nbCartesRestantes;

    int POINTAGE_CARTE_JOUER = 100;
    Context context ;
    Partie partie = new Partie(context);

    public Score(Context context){
        this.context = context;
    }

    public int calculerPointChaqueCartejouer(){
        POINTAGE_CARTE_JOUER += 20;
        pointage += POINTAGE_CARTE_JOUER;
        return pointage;
    }

    public int calculerScoreTotal(){
        return calculerPointChaqueCartejouer();
    }

    public int getPointage() {
        return calculerScoreTotal();
    }
}
