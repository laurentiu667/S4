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
    public void reset(){
        boissons.clear();
        total = 0;
    }


    public double getTotal() {
        return total;
    }
    public double ajouterTaxes(){
        return total * 0.14975;
    }

    public double totalTaxes(){
        return getTotal() + ajouterTaxes();
    }
    public void setBoissons(Vector<Cafe> boissons) {
        this.boissons = boissons;
    }


    public Vector<Cafe> getBoissons() {
        return boissons;
    }
}
