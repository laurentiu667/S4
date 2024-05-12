package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BeginPage extends AppCompatActivity {

    LinearLayout jouer, classement;
    Button suppbd;
    Ecouteur ec;
    GestionDB instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_page);

        jouer = findViewById(R.id.jouer);
        classement = findViewById(R.id.classement);
        suppbd = findViewById(R.id.suppbd);

        ec = new Ecouteur();
        instance = GestionDB.getInstance(getApplicationContext());

        jouer.setOnClickListener(ec);
        classement.setOnClickListener(ec);
        suppbd.setOnClickListener(ec);
    }

    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v == jouer){
                Intent intent = new Intent(BeginPage.this, MainActivity.class);
                startActivity(intent);
            }
            if(v == classement){
                Intent intent = new Intent(BeginPage.this, scorepage.class);
                startActivity(intent);
            }
            if (v == suppbd){
                instance.onUpgrade(instance.getWritableDatabase(), 1, 2);
            }
        }
    }
}