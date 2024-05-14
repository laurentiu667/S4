package com.example.examenfinal;

public class Drapeau {
    String couleur_gauche;
    String couleur_centre;
    String couleur_droite;
    String nom_pays;


    public Drapeau( String couleur_gauche, String couleur_centre, String couleur_droite, String nom_pays){
        this.couleur_gauche = couleur_gauche;
        this.couleur_centre = couleur_centre;
        this.couleur_droite = couleur_droite;
        this.nom_pays = nom_pays;

    }

    public String getCouleur_centre() {
        return couleur_centre;
    }

    public String getCouleur_droite() {
        return couleur_droite;
    }

    public String getCouleur_gauche() {
        return couleur_gauche;
    }

    public String getNom_pays() {
        return nom_pays;
    }
}
