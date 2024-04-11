package com.example.tp2_code;

import androidx.annotation.NonNull;

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

        seekBar = findViewById(R.id.nomImage);
        seekBar.setMax(300);
        seekBar.setProgress(10);
        tailleTrait = findViewById(R.id.taille_int);
        confirmerTrait = findViewById(R.id.confirmer_taille);
        ec = new Ecouteur();

        confirmerTrait.setOnClickListener(ec);
        tailleTrait.setText(String.valueOf(seekBar.getProgress()));

    }
    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tailleTrait.setText(String.valueOf(seekBar.getProgress()));
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            if (v instanceof Button) {
                dismiss();
            }
        }

    }
    public int retournerTaille(){
        return seekBar.getProgress();
    }
}
