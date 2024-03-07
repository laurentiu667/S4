package com.example.tp1_laurentiu_dilion;

import java.util.Hashtable;
import java.util.Set;

public class ListeProduits {

    private final String[] taille = {"petit", "moyen", "grand"};

    private Hashtable<String, Cafe> liste;

    public ListeProduits ()
    {
        liste = new Hashtable();
        liste.put("filtre petit", new CafeFiltre(taille[0]));
        liste.put("filtre moyen", new CafeFiltre(taille[1]));
        liste.put("filtre grand", new CafeFiltre(taille[2]));
        liste.put("americano petit", new CafeAmericano(taille[0]));
        liste.put("americano moyen", new CafeAmericano(taille[1]));
        liste.put("americano grand", new CafeAmericano(taille[2]));
        liste.put("glace petit", new CafeGlace(taille[0]));
        liste.put("glace moyen", new CafeGlace(taille[1]));
        liste.put("glace grand", new CafeGlace(taille[2]));
        liste.put("latte petit", new CafeLatte(taille[0]));
        liste.put("latte moyen", new CafeLatte(taille[1]));
        liste.put("latte grand", new CafeLatte(taille[2]));
    }

    public Cafe getCafe(String nom) {
        return liste.get(nom);
    }
}

