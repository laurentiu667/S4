package com.example.examen;

public class Evaluation {


    private String matricule;
    private int nbr_service_reussi;
    private String type_joueur;

    public Evaluation(String matricule, int nbr_service_reussi, String type_joueur) {
        this.matricule = matricule;
        this.nbr_service_reussi = nbr_service_reussi;
        this.type_joueur = type_joueur;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getNbr_service_reussi() {
        return nbr_service_reussi;
    }

    public void setNbr_service_reussi(int nbr_service_reussi) {
        this.nbr_service_reussi = nbr_service_reussi;
    }
}
