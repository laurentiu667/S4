package com.example.tp1_laurentiu_dilion;

public class CafeFiltre extends Cafe {
    static final double prix = 1.8;
    static final int calorieP = 5;
    static final int calorieM = 7;
    static final int calorieG = 10;
    static String taille;


    public CafeFiltre(String taille) {
        super("Filtre", calculerPrix(taille), calculerCalorie(taille));
        this.taille = taille;
    }

    protected static double calculerPrix(String taille) {
        switch (taille) {
            case "petit":
                return prix;
            case "moyen":
                return prix * 5 / 3;
            case "grand":
                return prix * 2;
            default:
                return 0;
        }
    }
    protected static int calculerCalorie(String taille) {
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
