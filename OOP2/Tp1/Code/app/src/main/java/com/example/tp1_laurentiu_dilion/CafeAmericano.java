package com.example.tp1_laurentiu_dilion;

public class CafeAmericano extends Cafe {
    static final double prix = 2.4;
    static final int calorieP = 9;
    static final int calorieM = calorieP + 2;
    static final int calorieG = calorieP * 2;


    public CafeAmericano() {
        super("Americano", prix, calorieP);
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
