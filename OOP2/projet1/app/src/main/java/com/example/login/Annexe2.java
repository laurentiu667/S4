package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Vector;

import com.example.login.Compte;

public class Annexe2 extends AppCompatActivity {
    Ecouteur ec;

    Button boutonValiderVar;
    TextView champSoldeVar;
    Spinner spinnerNomCompteVar;

    TextView mail;
    TextView Atransfer;
    Button boutonEnvoyerVar;
    Vector<String> choix;
    Double solde_global;

    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annexe2);

        // initialiser les variables
        spinnerNomCompteVar = findViewById(R.id.spinnercompte);
        champSoldeVar = findViewById(R.id.champSolde);

        // obligatoirement deux chiffres apres la virgule
        df = new DecimalFormat("0.00$", new DecimalFormatSymbols(Locale.US));

        choix = new Vector();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");


        mail = findViewById(R.id.mail);
        Atransfer = findViewById(R.id.transferA);

        boutonEnvoyerVar = findViewById(R.id.envoyerButton);

        // 1 er etape : creation de l ecouteur
        ec = new Ecouteur();
        boutonEnvoyerVar.setOnClickListener(ec);



        // Spinner
        // this parce que on est dans une classe anonyme
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix);
        spinnerNomCompteVar.setAdapter(adapter);

        spinnerNomCompteVar.setOnItemSelectedListener(ec);

        Compte cheque = new Compte("CHEQUE", 500);
        Compte epargne = new Compte("EPARGE", 1000);
        Compte epargneplus = new Compte("EPARGNEPLUS", 1500);

    }

    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        @Override
        public void onClick(View v) {
            solde_global = 500.0;




        }


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
            String nomCompte = choix.get(pos);
            champSoldeVar.setText(nomCompte);

//            // autre facon
//            TextView temp = (TextView) view;
//            String nomCompte2 = temp.getText().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}