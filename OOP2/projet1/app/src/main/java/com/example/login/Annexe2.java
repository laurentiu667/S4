package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Locale;
import java.util.Vector;

public class Annexe2 extends AppCompatActivity {
    Ecouteur ec;

    Button boutonValiderVar;
    EditText champNomCompteVar;
    TextView champSoldeVar;

    TextView mail;
    TextView Atransfer;
    Button boutonEnvoyerVar;
    Vector<String> choix;
    Double solde_global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annexe2);

        // initialiser les variables
        boutonValiderVar = findViewById(R.id.validebutton);
        champNomCompteVar = findViewById(R.id.champNomCompte);
        champSoldeVar = findViewById(R.id.champSolde);

        choix = new Vector();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");


        mail = findViewById(R.id.mail);
        Atransfer = findViewById(R.id.transferA);

        boutonEnvoyerVar = findViewById(R.id.envoyerButton);

        // 1 er etape : creation de l ecouteur
        ec = new Ecouteur();
        boutonValiderVar.setOnClickListener(ec);
        boutonEnvoyerVar.setOnClickListener(ec);



        // 2e etape : inscire la source a l ecouteur
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            solde_global = 500.0;

            if (v == boutonValiderVar){
                String nom = String.valueOf(champNomCompteVar.getText());
                nom = nom.trim().toUpperCase();
                if (choix.contains(nom)){
                    champSoldeVar.setText(String.valueOf(solde_global));
                }
                else {
                    champSoldeVar.setText("Pas le bon compte");
                    champNomCompteVar.setText("");
                }
            } else if (v == boutonEnvoyerVar){
                if (mail.length() != 0 && Atransfer.length() != 0){
                    solde_global -= solde_global;
                    champSoldeVar.setText(String.valueOf(solde_global));
                    mail.setText("");
                    Atransfer.setText("");
                }
            }


        }




    }


}