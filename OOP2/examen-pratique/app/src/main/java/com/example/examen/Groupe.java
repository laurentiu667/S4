package com.example.examen;

import java.util.Vector;

public class Groupe {

    private Vector<Evaluation> evaluations;

    public Groupe() {
        evaluations = new Vector<>();
    }

    public void ajouter_evaluation(Evaluation e){
        evaluations.add(e);
    }

    public int nbr_evaluation_vecteur(){
        return evaluations.size();
    }
    public double moyenne_nbr_services(int nombre_personne){
        int nombre_total = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            nombre_total += evaluations.get(i).getNbr_service_reussi();
        }
        return nombre_total / nombre_personne;

    }

    public String meilleur_matricule_service(){
        int plus_grand = 0;
        int temp = 0;
        int index = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            temp = evaluations.get(i).getNbr_service_reussi();
            if (plus_grand < temp){
                plus_grand = temp;
                index = i;
            } else if (plus_grand == temp){

            }
        }

        return evaluations.get(index).getMatricule();
    }
}
