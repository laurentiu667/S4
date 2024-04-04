package com.example.tp2_code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class couleurNuage extends Dialog {

    public couleurNuage(@NonNull Context context, MainActivity mainActivity) {
        super(context);

        this.mainActivity = mainActivity;
    }

    LinearLayout showColor;

    SeekBar R1, G2, B3;
    Ecouteur ec;

    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couleur_nuage);
        showColor = findViewById(R.id.colorPickerBG);
        R1 = findViewById(R.id.seekBarR);
        G2 = findViewById(R.id.seekBarG);
        B3 = findViewById(R.id.seekBarB);
        ec = new Ecouteur();
        R1.setOnSeekBarChangeListener(ec);
        G2.setOnSeekBarChangeListener(ec);
        B3.setOnSeekBarChangeListener(ec);
    }
    private class Ecouteur implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int r = R1.getProgress();
            int g = G2.getProgress();
            int b = B3.getProgress();

            showColor.setBackgroundColor(0xff000000 + r * 0x10000 + g * 0x100 + b);

            mainActivity.hexColor = String.format("#%02x%02x%02x", r, g, b);

            mainActivity.couleurActuelle.setBackgroundColor(Color.parseColor(mainActivity.hexColor));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

}