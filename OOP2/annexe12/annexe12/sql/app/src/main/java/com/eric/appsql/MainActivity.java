package com.eric.appsql;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;

import java.util.Random;
import java.util.Vector;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    ListView l;
    TextView nom;
    GestionBD instance = GestionBD.getInstance(getApplicationContext());
    Vector<String> v = instance.select_inventeur();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ec = new Ecouteur();
        nom = findViewById(R.id.nom);
        l = findViewById(R.id.list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Ouvrire la data base
        instance.ouvrir_database();




        for (String s : v){
            System.out.println(s);
        }

        Random r = new Random();
        int i = r.nextInt(v.size());

        Vector<String> v =

        ArrayAdapter<String> arr;

        arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, v);
        l.setAdapter(arr);







    }


    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String reponseDonne;
        }

    }




}