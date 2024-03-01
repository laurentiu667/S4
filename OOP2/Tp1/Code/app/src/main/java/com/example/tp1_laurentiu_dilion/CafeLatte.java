package com.example.tp1_laurentiu_dilion;

public class CafeLatte extends Cafe {
    static final double prix = 4;
    static final int calorieP = 125;
    static final int calorieM = calorieP * 5/3;
    static final int calorieG = calorieP * 2;
    static String taille;
    public CafeLatte(String taille) {
        super("Latte", calculerPrix(taille), calculerCalorie(taille));
        this.taille = taille;
    }

    private static double calculerPrix(String taille) {
        switch (taille) {
            case "petit":
                return prix;
            case "moyen":
                return prix * 5 / 3;
            case "grand":
                return prix * 2.5;
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
