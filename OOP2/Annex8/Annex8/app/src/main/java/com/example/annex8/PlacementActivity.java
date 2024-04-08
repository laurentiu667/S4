package com.example.annex8;;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PlacementActivity extends AppCompatActivity {

    private EditText champMontant;
    private NumberPicker numberPicker;
    private TextView labelReponse;
    private Button bouton;
    private Ecouteur ec;




    public DecimalFormat d = new DecimalFormat("0.00$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        champMontant =  findViewById(R.id.champMontant);
        numberPicker = findViewById(R.id.numberPicker);
        labelReponse =  findViewById(R.id.labelReponse);
        bouton = findViewById(R.id.bouton);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 12;
                return "" + temp;
            }
        };


        numberPicker.setFormatter(formatter);
        
        // 3 étapes
        ec = new Ecouteur();
        bouton.setOnClickListener(ec);


    }

    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            try {
                Placement pl = new Placement(Double.parseDouble(champMontant.getText().toString()), numberPicker.getValue() * 12);
                Double rep = pl.calculerMontantFinal();
                labelReponse.setText(d.format(rep));
            }
            catch (NumberFormatException nfe){
                champMontant.setText("");
                creerAlertDialog("Entrez un nombre " + champMontant.getText().toString() + " n'est pas un nombre");
                champMontant.setHint("exemple: ecrire 1000");
            }

        }
    }
    //pour créer une boite de dialogue simple
    public void creerAlertDialog(String message) {


        AlertDialog.Builder builder = new AlertDialog.Builder(PlacementActivity.this);

        //on peut faire ca !!
        builder.setMessage(message)
                .setTitle("Erreur");


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}








