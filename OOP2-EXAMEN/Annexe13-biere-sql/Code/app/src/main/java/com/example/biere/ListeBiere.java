package com.example.biere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

public class ListeBiere extends AppCompatActivity {

    ListView list;
    Vector<String> top3Biere;
    GestionDB instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_biere);

        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrire_db();
        list = findViewById(R.id.Biereliste);
        top3Biere = instance.retournerMeilleur();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, top3Biere);
        list.setAdapter(adapter);






    }
}