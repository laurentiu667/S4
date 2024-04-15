package com.example.projetadressage;


import java.util.Hashtable;

import bla.HashtableAssociation;


public class Inscrit {
    private String nom;
    private String prenom;
    private String adresse;
    private String capitale;
    private String etat;
    private String codeZip;


    public Inscrit(String nom, String prenom, String adresse, String capitale, String etat, String codeZip) throws AdresseException{
        // vérifier si la capitale fait partie de l'état à l'aide d'une Hashtable secrète ( classe HashtableAssociation )

        HashtableAssociation hs = new HashtableAssociation();

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.capitale = capitale;
        this.etat = etat;
        this.codeZip = codeZip;



        if (!hs.get(capitale).equals(etat)){
            throw new AdresseException(capitale, etat);
        }












    }
}
