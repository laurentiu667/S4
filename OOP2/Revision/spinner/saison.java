package com.example.pratique;

import java.util.Vector;

public class saison {
    
    private Vector<entrainement> liste;
    public static final int OBJECTIF = 12;
    
    public saison(){
        liste = new Vector<>();
    }
    
    public void ajouterEntrainement (entrainement e){
        liste.add(e);
    }
    
    public int nbHeureEntrainer(){
        int nombreHeureTotal = 0;

        for (entrainement e: liste){
            nombreHeureTotal += e.getHeurefin() - e.getHeuredep();
            
        }

//        for (int i = 0; i < liste.size(); i++) {
//            nombreHeureTotal += liste.get(i).getHeurefin() - liste.get(i).getHeuredep();
//        }

        return nombreHeureTotal;
    }
    
}
