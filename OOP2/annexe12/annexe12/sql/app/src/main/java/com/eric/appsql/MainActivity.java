package com.eric.appsql;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GestionBD instance = GestionBD.getInstance(getApplicationContext());

        // Ouvrire la data base
        instance.ouvrir_database();

        Vector<String> v = instance.select_inventeur();

        for (String s: v){
            System.out.println(s);
        }






    }





}