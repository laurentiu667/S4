package com.example.tp1_laurentiu_dilion;

import java.util.Vector;

public class Commande {

    // 2.	Ajoutez également un fichier / classe Commande représentant la commande en cours. Une commande aura 2 variables d'instance :
    //•	Un Vector contenant les boissons que l'usager veut acheter
    //•	Le total en argent de la commande
    //Codez un constructeur initialisant le Vector vide et le total = 0
    //Codez une méthode permettant d'ajouter un Produit acheté à la commande et d'augmenter le total en conséquence.
    //Codez des méthodes d'accès ( get ) si nécessaire2.	Ajoutez également un fichier / classe Commande représentant la commande en cours. Une commande aura 2 variables d'instance :
    //•	Un Vector contenant les boissons que l'usager veut acheter
    //•	Le total en argent de la commande
    //Codez un constructeur initialisant le Vector vide et le total = 0
    //Codez une méthode permettant d'ajouter un Produit acheté à la commande et d'augmenter le total en conséquence.
    //Codez des méthodes d'accès ( get ) si nécessaire

    private Vector boissons;
    private double total;

    public Commande() {
        boissons = new Vector();
        total = 0;
    }

    public void ajouterBoisson(Cafe cafe) {
        boissons.add(cafe);
        total += cafe.getPrix();
    }

    public Vector getBoissons() {
        return boissons;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setBoissons(Vector boissons) {
        this.boissons = boissons;
    }

    public void afficher() {
        System.out.println("Commande : ");
        for (int i = 0; i < boissons.size(); i++) {
            Cafe cafe = (Cafe) boissons.get(i);
            System.out.println(cafe.getNom() + " " + cafe.getPrix());
        }
        System.out.println("Total : " + total);
    }



}
