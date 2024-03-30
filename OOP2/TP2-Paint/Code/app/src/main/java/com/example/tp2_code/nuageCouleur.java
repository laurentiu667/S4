//package com.example.tp2_code;
//
//import androidx.annotation.NonNull;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.SeekBar;
//
//public class nuageCouleur extends Dialog {
//
//    LinearLayout background;
//    SeekBar R, G, B;
//    Ecouteur ec;
//
//    public nuageCouleur(@NonNull Context context) {
//        super(context);
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nuage_couleur);
//        ec = new Ecouteur();
//
//        background = findViewById(R.id.colorPickerBG);
//
//        R = findViewById(R.id.seekBarR);
//        G = findViewById(R.id.seekBarG);
//        B = findViewById(R.id.seekBarB);
//    }
////    private class Ecouteur implements View.OnClickListener {
////
////        @Override
////        public void onClick(View v) {
////
////        }
////    }
//}
