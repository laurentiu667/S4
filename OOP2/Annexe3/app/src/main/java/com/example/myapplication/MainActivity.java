package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    // Variable

    Ecouteur ec;
    ImageView unBidon;
    ImageView uneBouteille;
    ImageView unVerre;

    ProgressBar barre;

    TextView nombre_litre;


    int somme = 0;
    final int verre_eau = 150;
    final int bouteille_eau = 330;
    final int bidon_eau = 1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unBidon = findViewById(R.id.bidon);
        uneBouteille = findViewById(R.id.bouteille);
        unVerre = findViewById(R.id.verre);
        barre = findViewById(R.id.progresseLitre);
        nombre_litre = findViewById(R.id.resultat_litre);

        ec = new Ecouteur();

        unBidon.setOnClickListener(ec);
        uneBouteille.setOnClickListener(ec);
        unVerre.setOnClickListener(ec);


        barre.setMax(2000);

    }

    private class Ecouteur implements View.OnClickListener{
        int somme = 0;
        @Override
        public void onClick(View v){

            if (v == unBidon){
                somme += bidon_eau;
            } else if (v == unVerre){
                somme += verre_eau;
            } else {
                somme += bouteille_eau;
            }
            if (somme < 2000){
                nombre_litre.setText(String.valueOf(somme));
            }
            barre.setProgress(somme);
        }

    }

}