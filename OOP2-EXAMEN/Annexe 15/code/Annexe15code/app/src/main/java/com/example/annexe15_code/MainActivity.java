package com.example.annexe15_code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.annexe15_code.DataBase.GestionDB;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    GestionDB instance;
    TextView nb_equipe, moyenne_capacite, reponse;
    Button rechercher;
    Spinner spinner;
    Vector<String> liste_arena;
    String arena;
    Ecouteur ecouteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecouteur = new Ecouteur();

        moyenne_capacite = findViewById(R.id.moyenne);
        spinner = findViewById(R.id.spinner);
        reponse = findViewById(R.id.reponse);
        rechercher = findViewById(R.id.rechercher);
        nb_equipe = findViewById(R.id.nbequipedivision);
        rechercher.setOnClickListener(ecouteur);
        // ==========================================//
        // initialisation de la base de données
        instance = GestionDB.getInstance(this);
        // remplir le vecteur avec les arénas
        liste_arena = instance.retournerLesArena();

        // remplir le spinner
        // creer un arrayadapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, liste_arena);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        double moyenne = instance.moyenne_capacite();
        moyenne_capacite.setText(String.valueOf(moyenne));
        int nb = instance.nombreDeOuest();
        nb_equipe.setText(String.valueOf(nb));
    }

    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if(v == rechercher){
                arena = spinner.getSelectedItem().toString();
                reponse.setText(instance.returnerNomEquipeEnPrenantLArene(arena));
            }
        }
    }
}