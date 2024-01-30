package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Annexe2 extends AppCompatActivity {
    Ecouteur ec;

    Button boutonValider;
    EditText champNomCompte;
    TextView champSolde;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annexe2);

        // initialiser les variables
        boutonValider = findViewById(R.id.validebutton);
        champNomCompte = findViewById(R.id.champNomCompte);
        champSolde = findViewById(R.id.champSolde);

        // 1 er etape : creation de l ecouteur
        ec = new Ecouteur();
        boutonValider.setOnClickListener(ec);



        // 2e etape : inscire la source a l ecouteur
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            champSolde.setText(234324);
        }
    }

}