package com.example.biere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.Vector;

public class EnregistrementBiere extends AppCompatActivity {

    Button Enregistrer;

    EditText nom, brasserie;
    RatingBar note;
    Ecouteur ec;

    GestionDB instance;
    Vector<String> biereNom;
    Vector<String> brasserieNom;
    Vector<String> noteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_biere);

        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrire_db();

        Enregistrer = findViewById(R.id.enregistrer);

        nom = findViewById(R.id.nomBiere);
        brasserie = findViewById(R.id.nomBrasserie);
        note = findViewById(R.id.noteBiere);
        note.setStepSize(1.0f);
        note.setNumStars(5);

        ec = new Ecouteur();
        Enregistrer.setOnClickListener(ec);
        nom.setOnClickListener(ec);
        brasserie.setOnClickListener(ec);
        note.setOnClickListener(ec);
        biereNom = instance.select_biere();
        brasserieNom = instance.select_brasserie();
        noteFinal = instance.select_note();

    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == Enregistrer){

                String nomBiere = nom.getText().toString();
                String nomBrasserie = brasserie.getText().toString();
                float noteBiere = note.getRating();


                instance.ajouter_new_biere(nomBiere, nomBrasserie, noteBiere);

//                instance.onUpgrade( instance.getWritableDatabase(), 1, 1 );

                finish();


            }
        }
    }
}