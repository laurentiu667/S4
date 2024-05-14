package com.eric.appsql;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import java.util.Vector;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec; // Écouteur pour gérer les événements de clic sur les éléments de la liste
    ListView l; // ListView pour afficher la liste des inventions
    TextView nom; // TextView pour afficher le nom de l'inventeur sélectionné
    GestionBD instance; // Instance de la base de données
    Vector<String> v; // Vecteur pour stocker les noms des inventeurs
    Vector<String> v2; // Vecteur pour stocker les noms des inventions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'instance de la base de données
        instance = GestionBD.getInstance(getApplicationContext());
        // Ouvrir la base de données
        instance.ouvrir_database();

        // Récupérer les données des inventeurs et des inventions depuis la base de données
        v = instance.select_inventeur();
        v2 = instance.select_invention();

        // Initialisation des éléments de l'interface utilisateur
        nom = findViewById(R.id.nom);
        l = findViewById(R.id.list);

        // Générer un nombre aléatoire pour sélectionner un inventeur
        Random r = new Random();
        int i = r.nextInt(v.size());
        String reponse = v.get(i);

        // Afficher le nom de l'inventeur sélectionné dans TextView
        nom.setText(reponse);

        // Créer un ArrayAdapter pour afficher la liste des inventions dans ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v2);
        l.setAdapter(adapter);

        // Initialisation de l'écouteur d'événements pour gérer les clics sur les éléments de la liste
        ec = new Ecouteur();
        l.setOnItemClickListener(ec);
    }

    // Classe interne pour gérer les événements de clic sur les éléments de la liste
    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Récupérer l'élément (invention) cliqué
            String clickedItem = v2.get(position);

            // Récupérer le nom de l'inventeur
            String inventorName = nom.getText().toString();

            // Vérifier si l'invention sélectionnée correspond à l'inventeur
            boolean isCorrect = instance.aBonneReponse(inventorName, clickedItem);

            // Si l'invention sélectionnée correspond à l'inventeur, définir la couleur de fond sur vert, sinon sur rouge
            if (isCorrect) {
                view.setBackgroundColor(Color.GREEN);
            } else {
                view.setBackgroundColor(Color.RED);
            }
        }
    }
}
