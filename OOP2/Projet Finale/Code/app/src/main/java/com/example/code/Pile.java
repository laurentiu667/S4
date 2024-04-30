package com.example.code;

public class Pile {
    int valeur;
    String sens;

    public Pile(int valeur, String sens){
        this.valeur = valeur;
        this.sens = sens;
    }

    public int getValeur(){
        return valeur;
    }

    public String getSens(){
        return sens;
    }

    public void setValeur(int valeur){
        this.valeur = valeur;
    }

    public void setSens(String sens){
        this.sens = sens;
    }

}
