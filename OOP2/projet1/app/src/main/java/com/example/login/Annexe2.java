package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;

public class Annexe2 extends AppCompatActivity {
    Ecouteur ec;

    Button boutonValiderVar;
    EditText champNomCompteVar;
    TextView champSoldeVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annexe2);

        // initialiser les variables
        boutonValiderVar = findViewById(R.id.validebutton);
        champNomCompteVar = findViewById(R.id.champNomCompte);
        champSoldeVar = findViewById(R.id.champSolde);

        // 1 er etape : creation de l ecouteur
        ec = new Ecouteur();
        boutonValiderVar.setOnClickListener(ec);



        // 2e etape : inscire la source a l ecouteur
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String valeurText = champNomCompteVar.getText().toString();
            System.out.println(valeurText);
            if (valeurText.equals("Cheque") || valeurText.equals("Epargne") || valeurText.equals("EpargnePlus")){
                champSoldeVar.setText("21312");
            } else {
                champSoldeVar.setText("faux");
            }
        }




    }


}