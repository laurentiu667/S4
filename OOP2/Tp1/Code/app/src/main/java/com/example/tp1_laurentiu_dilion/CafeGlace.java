package com.example.tp1_laurentiu_dilion;

public class CafeGlace extends Cafe {
    static final double prix = 2.5;
    static final int calorieP = 10;
    static final int calorieM = calorieP + 2;
    static final int calorieG = calorieP * 2;

    static String taille;
    public CafeGlace(String taille) {
        super("Glace", calculerPrix(taille), calculerCalorie(taille));
        this.taille = taille;
    }

    private static double calculerPrix(String taille) {
        switch (taille) {
            case "petit":
                return prix;
            case "moyen":
                return prix + prix * 5 / 3;
            case "grand":
                return prix * 2.2;
            default:
                return 0;
        }
    }
    private static int calculerCalorie(String taille) {
        switch (taille) {
            case "petit":
                return calorieP;
            case "moyen":
                return calorieM;
            case "grand":
                return calorieG;
            default:
                return 0;
        }
    }
}
