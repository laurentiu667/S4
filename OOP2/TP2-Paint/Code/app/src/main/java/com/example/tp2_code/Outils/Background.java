package com.example.tp2_code.Outils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.tp2_code.MainActivity;

public class Background extends Forme{
    private String hexColor;


    public Background(String hexColor) {
        super(Color.parseColor(hexColor));
    }
    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void dessiner(MainActivity.Surface surface) {
        surface.setBackgroundColor(Color.parseColor(getHexColor()));
    }

}
