package com.example.biere;

public class Biere {
    private String nom;
    private String brasserie;
    private float note;


    public Biere(String nom, String brasserie, float note) {
        this.nom = nom;
        this.brasserie = brasserie;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public String getBrasserie() {
        return brasserie;
    }

    public float getNote() {
        return note;
    }






}
