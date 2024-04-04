package com.example.tp2_code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnregistrerImage extends Dialog {

    public EnregistrerImage(@NonNull Context context) {
        super(context);
    }

    EditText nomImage;
    Button confirmer;
    String nomImageStr;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_image);
        nomImage = findViewById(R.id.nomImage);
        confirmer = findViewById(R.id.confirmer_taille);
        ec = new Ecouteur();
        confirmer.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == confirmer) {
                nomImageStr = nomImage.getText().toString();
                dismiss();
            }
        }
    }

}