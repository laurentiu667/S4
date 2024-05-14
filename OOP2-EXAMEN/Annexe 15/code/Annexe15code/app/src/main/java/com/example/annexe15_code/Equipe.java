package com.example.annexe15_code;

public class Equipe {
    private String nom;
    private String division;
    private String arena;
    private int capacite;

    public Equipe(String nom, String division, String arena, int capacite) {
        this.nom = nom;
        this.division = division;
        this.arena = arena;
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public String getDivision() {
        return division;
    }

    public String getArena() {
        return arena;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}