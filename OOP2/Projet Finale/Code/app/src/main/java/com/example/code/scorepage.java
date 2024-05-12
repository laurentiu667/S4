package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

public class scorepage extends AppCompatActivity {

    ListView listScore;
    Vector<Integer> TopScore;
    GestionDB instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorepage);


        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrire_db();
        listScore = findViewById(R.id.listescore);

        TopScore = instance.retournerTousLesScores();

        // Convertir chaque entier en String
        Vector<String> stringScores = new Vector<>();
        for (Integer score : TopScore) {
            stringScores.add(String.valueOf(score));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringScores);
        listScore.setAdapter(adapter);



    }
}