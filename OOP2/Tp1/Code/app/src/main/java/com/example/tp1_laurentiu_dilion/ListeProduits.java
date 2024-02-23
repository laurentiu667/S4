package com.example.tp1_laurentiu_dilion;

import java.util.Hashtable;

public class ListeProduits {

    private final String[] taille = {"petit", "moyen", "grand"};

    private Hashtable<String, Cafe> liste;

    public ListeProduits ()
    {
        liste = new Hashtable();
        liste.put("Café filtre Petit", new CafeFiltre(taille[0]));
        liste.put("Café filtre Moyen", new CafeFiltre(taille[1]));
        liste.put("Café filtre Grand", new CafeFiltre(taille[2]));
        liste.put("Americano Petit", new CafeAmericano(taille[0]));
        liste.put("Americano Moyen", new CafeAmericano(taille[1]));
        liste.put("Americano Grand", new CafeAmericano(taille[2]));
        liste.put("Café glacé Petit", new CafeGlace(taille[0]));
        liste.put("Café glacé Moyen", new CafeGlace(taille[1]));
        liste.put("Café glacé Grand", new CafeGlace(taille[2]));
        liste.put("Latté Petit", new CafeLatte(taille[0]));
        liste.put("Latté Moyen", new CafeLatte(taille[1]));
        liste.put("Latté Grand", new CafeLatte(taille[2]));
    }

    public Cafe getCafe(String nom)
    {
        return liste.get(nom);
    }


}
