package com.example.code;

import java.util.ArrayList;

public class PileDescendant{


    public ArrayList<Carte> listeCartes = new ArrayList<Carte>();
    int numero;
    String sens;
    public PileDescendant(){
        this.sens = "descendant";
        this.numero = 98;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
