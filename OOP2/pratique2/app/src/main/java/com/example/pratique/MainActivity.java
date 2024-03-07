package com.example.pratique;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {



    EditText nom = findViewById(R.id.typeEntrainement);
    Spinner debut = findViewById(R.id.debutSpinner);
    Spinner fin = findViewById(R.id.finSpinner);
    Button enregistrer = findViewById(R.id.enregistrer);

    ProgressBar bar = findViewById(R.id.progressBar);
    Ecouteur ec = new Ecouteur();
    saison s;
    entrainement e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vector<Integer> heure = new Vector<>();

        for (int i = 7; i <= 23; i++) {
            heure.add(i);
        }


        enregistrer.setOnClickListener(ec);

        // pour ajotuer
        debut.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, heure));
        fin.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, heure));

        s = new saison();

        bar.setMax(saison.OBJECTIF);

    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            if (v instanceof Button){

                s.ajouterEntrainement(new entrainement(nom.toString(), (Integer) debut.getSelectedItem(), (Integer) fin.getSelectedItem()));
                bar.setProgress(s.nbHeureEntrainer());
            }


        }



    }


}