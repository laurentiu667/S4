package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Vector;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;

    Button bouton;
    Inscrit inscrit;
    Ecouteur ec;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ec = new Ecouteur();

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);
        bouton = findViewById(R.id.boutonInscrire);
        bouton.setOnClickListener(ec);
        HashtableAssociation hs = new HashtableAssociation();
        Vector<String> capitale = new Vector<>(hs.values());
        Vector<String> etat = new Vector<>(hs.keySet());

        ArrayAdapter<String> adapterCapitale = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, capitale);
        ArrayAdapter<String> adapterEtat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, etat);


        spinnerCapitale.setAdapter(adapterCapitale);
        spinnerEtat.setAdapter(adapterEtat);








    }
    private class Ecouteur implements View.OnClickListener
    {






        @Override
        public void onClick(View v) {
            String nom = champNom.getText().toString();
            String prenom = champPrenom.getText().toString();
            String adresse = champAdresse.getText().toString();
            String etat = spinnerEtat.getSelectedItem().toString();
            String capital = spinnerCapitale.getSelectedItem().toString();
            String code = champZip.getText().toString();

            if (v instanceof Button){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                try {
                    Inscrit inscrit = new Inscrit(nom, prenom, adresse, capital, etat, code);
                    builder.setTitle("reussi");
                } catch(AdresseException e){

                    builder.setTitle("Erreur");
                    builder.setMessage(e.getMessage());

                    builder.show();
                }

            }

        }
    }
}