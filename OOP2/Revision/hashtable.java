package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

public class Annexe2 extends AppCompatActivity {

    Button boutonEnvoyerVar;
    Spinner spinnerNomCompteVar;
    TextView champSoldeVar;
    TextView mail;
    TextView argentdonner;

    Vector<String> choix;
    Hashtable<String, Compte> lesComptes;
    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annexe2);

        spinnerNomCompteVar = findViewById(R.id.spinnercompte);
        champSoldeVar = findViewById(R.id.champSolde);

        mail = findViewById(R.id.mail);
        argentdonner = findViewById(R.id.argentdonner);
        boutonEnvoyerVar = findViewById(R.id.envoyerButton);

        df = new DecimalFormat("0.00$", new DecimalFormatSymbols(Locale.US));

        lesComptes = new Hashtable<>();
        lesComptes.put("CHEQUE", new Compte("CHEQUE", 500.0));
        lesComptes.put("EPARGNE", new Compte("EPARGNE", 1000.0));
        lesComptes.put("EPARGNEPLUS", new Compte("EPARGNEPLUS", 1500.0));

        Set<String> ensCles = lesComptes.keySet();
        choix = new Vector<>(ensCles);

        boutonEnvoyerVar.setOnClickListener(new Ecouteur());
        spinnerNomCompteVar.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choix));
        spinnerNomCompteVar.setOnItemSelectedListener(new Ecouteur());

        for (String str : ensCles)
            System.out.println(str);

        for (Compte p : lesComptes.values())
            System.out.println(p.getSolde());
    }

    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {
        @Override
        public void onClick(View v) {
            if (v == boutonEnvoyerVar) {

                String nomCompte = spinnerNomCompteVar.getSelectedItem().toString();
                Compte c = lesComptes.get(nomCompte);
                c.TransfereSolde(Double.parseDouble(String.valueOf(argentdonner.getText())));
                champSoldeVar.setText(df.format(c.getSolde()));

                argentdonner.setText("");
            } else if (v == boutonEnvoyerVar && argentdonner.getText().toString().trim().isEmpty()) {
                // Affichage d'un toast simple avec un message court
                Toast.makeText(Annexe2.this, "Argent a donner vide", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
            String nomCompte = choix.get(pos);
            Compte c = lesComptes.get(nomCompte);
            champSoldeVar.setText(df.format(c.getSolde()));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            champSoldeVar.setText("");

        }

    }
}

