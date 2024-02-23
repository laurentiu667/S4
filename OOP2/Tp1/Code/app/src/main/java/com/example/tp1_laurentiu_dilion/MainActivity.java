package com.example.tp1_laurentiu_dilion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


public class MainActivity extends AppCompatActivity {

    // Declaration des variables
    LinearLayout cafe_disponible;
    ChipGroup tailleCafe;
    Button ajouterCaisse;
    Button effacerCaisse;
    Button passerCommande;

    Ecouteur ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des variables
        cafe_disponible = findViewById(R.id.choixCafe);
        ajouterCaisse = findViewById(R.id.ajouterCaisse);
        effacerCaisse = findViewById(R.id.effacerCaisse);
        passerCommande = findViewById(R.id.passerCommande);
        tailleCafe = findViewById(R.id.choixTaille);
        ec = new Ecouteur();


        // Ajout des ecouteurs pour les images
        for (int i = 0; i < cafe_disponible.getChildCount(); i++) {
            LinearLayout sous_layout = (LinearLayout) cafe_disponible.getChildAt(i);
            for (int j = 0; j < sous_layout.getChildCount(); j++) {
                LinearLayout sous_layout2 = (LinearLayout) sous_layout.getChildAt(j);
                for (int k = 0; k < sous_layout2.getChildCount(); k++) {
                    LinearLayout sous_layout3 = (LinearLayout) sous_layout2.getChildAt(k);
                    for (int l = 0; l < sous_layout3.getChildCount(); l++) {
                        View view = sous_layout3.getChildAt(l);
                        if (view instanceof ImageView) {
                            ImageView img = (ImageView) view;
                            img.setOnClickListener(ec);
                        }
                    }
                }
            }
        }

        // Ajout des ecouteurs pour les chips
        for (int i = 0; i < tailleCafe.getChildCount(); i++) {
            Chip chip = (Chip) tailleCafe.getChildAt(i);
            if (chip instanceof Chip)
                chip.setOnClickListener(ec);

        }


    }
    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v instanceof ImageView) {
                ImageView b = (ImageView) v;
                Toast.makeText(MainActivity.this, "caffe", Toast.LENGTH_SHORT).show();
            } else if (v instanceof Chip) {
                Chip c = (Chip) v;
                Toast.makeText(MainActivity.this, "taille", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
//        @Override
//        public void OnItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        }
//
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }