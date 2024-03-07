package com.example.examen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import kotlin.reflect.KType;

public class MainActivity extends AppCompatActivity {

    EditText matriculemain, nmb_lancer_reussi;

    TextView etudiant_evaluer, moyennetoutlemonde, meilleuretudiant;
    Spinner spinner;
    Button buttonreussi, enregistrement;

    Ecouteur ec;

    Groupe g = new Groupe();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        matriculemain = findViewById(R.id.matricule);
        nmb_lancer_reussi = findViewById(R.id.servicereussi);
        etudiant_evaluer = findViewById(R.id.etudiantevalue);
        moyennetoutlemonde = findViewById(R.id.moyenne);
        meilleuretudiant = findViewById(R.id.meilleuretudiant);
        spinner = findViewById(R.id.spinner);
        buttonreussi = findViewById(R.id.buttonreussi);
        enregistrement = findViewById(R.id.enregistrementbutton);


        ec = new Ecouteur();

        buttonreussi.setOnClickListener(ec);
        enregistrement.setOnClickListener(ec);


        Vector<String> typeetudiant = new Vector<>();
        typeetudiant.add("Regulier");
        typeetudiant.add("Avancé");
        typeetudiant.add("Étudiant-Athlète");

        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, typeetudiant));

    }
    private class Ecouteur implements View.OnClickListener {
        int clicked = 0;
        int nbr_etudiant = 0;
        double moyenne = 0;
        @Override
        public void onClick(View v) {
            if (v == buttonreussi) {
                clicked++;
                nmb_lancer_reussi.setText(String.valueOf(clicked));
            } else if (v == enregistrement && matriculemain.length() >= 7) {
                Evaluation e = new Evaluation(matriculemain.getText().toString(), clicked, spinner.getSelectedItem().toString());
                e.setNbr_service_reussi(Integer.valueOf(nmb_lancer_reussi.getText().toString()));
                g.ajouter_evaluation(e);
                Toast.makeText(MainActivity.this, "L'évaluation à été enregistrer", Toast.LENGTH_SHORT).show();
                resetFields();

                nbr_etudiant++;
                updateUI();
            } else {
                showErrorDialog();
            }
        }

        private void showErrorDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("erreur");
            builder.setMessage("il te faut 7 chiffre");
            builder.show();
            matriculemain.setText("");
        }

        private void resetFields() {
            matriculemain.setText("");
            nmb_lancer_reussi.setText("");
        }

        private void updateUI() {
            etudiant_evaluer.setText(String.valueOf(nbr_etudiant));
            moyenne = g.moyenne_nbr_services(nbr_etudiant);
            moyennetoutlemonde.setText(String.valueOf(moyenne));
            meilleuretudiant.setText(g.meilleur_matricule_service());
        }



    }

}