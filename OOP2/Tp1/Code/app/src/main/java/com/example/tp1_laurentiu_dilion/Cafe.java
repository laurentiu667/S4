package com.example.tp1_laurentiu_dilion;

public class Cafe {
    protected String nom;
    protected double prix;
    protected int calorie;

    public Cafe(String nom, double prix, int calorie) {
        this.nom = nom;
        this.prix = prix;
        this.calorie = calorie;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getCalorie() {
        return calorie;
    }
}
