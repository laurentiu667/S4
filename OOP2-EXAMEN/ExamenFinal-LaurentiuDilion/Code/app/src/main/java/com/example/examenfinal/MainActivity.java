package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Drag ecouteur;

    LinearLayout conteneurG, conteneurC, conteneurD;
    TextView bleu, blanc, rouge, noir, jaune, texteQuestion;
    Button confirmer;
    SingletonDataBase instance;
    String paysretourner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecouteur = new Drag();
        conteneurG = findViewById(R.id.conteneurG);
        conteneurC = findViewById(R.id.conteneurC);
        conteneurD = findViewById(R.id.conteneurD);
        bleu = findViewById(R.id.bleu);
        blanc = findViewById(R.id.blanc);
        rouge = findViewById(R.id.rouge);
        noir = findViewById(R.id.noir);
        jaune = findViewById(R.id.jaune);
        texteQuestion = findViewById(R.id.texteQuestion);
        confirmer = findViewById(R.id.boutonConfirmer);

        bleu.setOnTouchListener(ecouteur);
        blanc.setOnTouchListener(ecouteur);
        rouge.setOnTouchListener(ecouteur);
        noir.setOnTouchListener(ecouteur);
        jaune.setOnTouchListener(ecouteur);

        confirmer.setOnClickListener(ecouteur);

        conteneurG.setOnDragListener(ecouteur);
        conteneurC.setOnDragListener(ecouteur);
        conteneurD.setOnDragListener(ecouteur);

        instance = SingletonDataBase.getInstance(this);
        instance.ouvrire_db();
        paysretourner = instance.retourner_pays_hasard();
        texteQuestion.setText("Dessinez le drapeau de la " + paysretourner);
    }

    public class Drag implements View.OnDragListener, View.OnTouchListener, View.OnClickListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            v.startDrag(null, new View.DragShadowBuilder(v), v, 0);
            return true;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DROP:
                    TextView couleurTextView = (TextView) event.getLocalState();
                    LinearLayout nouveauParentAttribuer = (LinearLayout) v;

                    // get tag
                    int couleur = Integer.valueOf((String) couleurTextView.getTag().toString());
                    if (couleur == -329737){
                        nouveauParentAttribuer.setTag("blanc");
                    } else if(couleur == -13022805){
                        nouveauParentAttribuer.setTag("bleu");
                    } else if(couleur == -849912){
                        nouveauParentAttribuer.setTag("rouge");
                    } else if(couleur == -16448251){
                        nouveauParentAttribuer.setTag("noir");
                    } else if(couleur == -19712){
                        nouveauParentAttribuer.setTag("jaune");
                    }
                    nouveauParentAttribuer.setBackgroundColor(couleur);
                    couleurTextView.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            if (v == confirmer){
                boolean drapeau_compo = instance.pays_verif(paysretourner, conteneurG.getTag().toString(), conteneurC.getTag().toString(), conteneurD.getTag().toString());
                if (drapeau_compo){
                    texteQuestion.setText("BRAVO");
                } else {
                    texteQuestion.setText("DSL");
                }
            }
        }
    }
}