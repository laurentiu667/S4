package com.example.biere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;

    Button addEval, listBiere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addEval = findViewById(R.id.addEval);
        listBiere = findViewById(R.id.listBiere);

        ec = new Ecouteur();
        addEval.setOnClickListener(ec);
        listBiere.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == addEval){
                Intent intent = new Intent(MainActivity.this, EnregistrementBiere.class);
                startActivity(intent);


            }
            if(v == listBiere){
                Intent intent = new Intent(MainActivity.this, ListeBiere.class);
                startActivity(intent);
            }
        }
    }
}