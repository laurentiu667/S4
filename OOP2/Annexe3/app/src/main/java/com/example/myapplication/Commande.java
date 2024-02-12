package com.example.myapplication;

import java.util.Vector;

public class Commande {

    private Vector<Produit> listeCommande;

    public Commande() {
        listeCommande = new Vector();
    }

    public void ajouterProduit(Produit p) {
        listeCommande.add(p);
    }

    public double total() {
        double totalavanttaxes = 0;


        for (int i = 0; i < listeCommande.size(); i++) {
            totalavanttaxes += listeCommande.get(i).getQte() * listeCommande.get(i).getPrixUnitaire();
        }
//        listeCommande.forEach((p) -> {
//            totalavanttaxes += p.getQte() * p.getPrixUnitaire();
//        });
        System.out.println(totalavanttaxes);

        return totalavanttaxes;
    }

    public double taxes() {
        return total() * 0.14975;
    }

    public double grandTotal() {
        return total() + taxes();

    }
}
