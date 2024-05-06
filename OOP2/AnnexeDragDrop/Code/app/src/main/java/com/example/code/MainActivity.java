package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout principal;
    private Drag ecouteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principal = findViewById(R.id.principal);
        ecouteur = new Drag();

        for(int i = 0; i < principal.getChildCount(); i++){
            LinearLayout colone = (LinearLayout) principal.getChildAt(i);
            colone.setOnDragListener(ecouteur);
            colone.getChildAt(0).setOnTouchListener(ecouteur);
        }
    }

    public class Drag implements View.OnDragListener, View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        }

        View jeton = null;

        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()){


                case DragEvent.ACTION_DROP:
                    jeton = (View) event.getLocalState();                           // Prendre le view du jetton selectionner
                    LinearLayout parent = (LinearLayout) jeton.getParent();         // Prendre le parent du jetton selectionner
                    parent.removeView(jeton);                                       // Retirer le jetton du parent de depart
                    LinearLayout conteneur = (LinearLayout) v;                      // Prendre le nouveau parent
                    conteneur.addView(jeton);                                       // Ajouter le jetton retirer au nouveau parent
                    jeton.setVisibility(View.VISIBLE);                              // Remettre la visibiliter au jetton selectionner
                    v.setBackground(getDrawable(R.drawable.background_contenant));  // Reset le drawable
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}