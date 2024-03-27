package com.example.tp2_code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class trait extends Dialog {

    SeekBar seekBar;
    TextView tailleTrait;
    Button confirmerTrait;
    Ecouteur ec;

    public trait(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trait);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(200);
        seekBar.setProgress(10);
        tailleTrait = findViewById(R.id.taille_int);
        confirmerTrait = findViewById(R.id.confirmer_taille);
        ec = new Ecouteur();

        confirmerTrait.setOnClickListener(ec);



    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (v == confirmerTrait) {
                tailleTrait.setText(seekBar.getProgress());
                dismiss();
            }
        }

    }
    public int retournerTaille(){
        return seekBar.getProgress();
    }
}
