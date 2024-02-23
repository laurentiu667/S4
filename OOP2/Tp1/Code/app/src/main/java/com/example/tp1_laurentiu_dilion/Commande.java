package com.example.tp1_laurentiu_dilion;

import java.util.Vector;

public class Commande {

    private Vector<Cafe> boissons = new Vector();
    private double total;


    public Commande() {
        boissons = new Vector();
        total = 0;
    }

    public void ajouter_boisson(Cafe produit){
        boissons.add(produit);
        total += produit.getPrix();
    }


    public double getTotal() {
        return total;
    }

    public Vector<Cafe> getBoissons() {
        return boissons;
    }
}
