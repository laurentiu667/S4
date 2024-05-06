package com.example.code;

import java.util.ArrayList;

public class PileAscendant{


    public ArrayList<Carte> listeCartes = new ArrayList<Carte>();
    int numero;

    String sens;
    public PileAscendant(){
        this.sens = "ascendant";
        this.numero = 0;
    }




    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
