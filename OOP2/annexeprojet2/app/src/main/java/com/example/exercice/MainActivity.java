package com.example.exercice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.graphics.Path;

public class MainActivity extends AppCompatActivity {

    LinearLayout parent;
    Path p;
    EditText PointA;
    EditText PointB;
    Button ajouter;

    Surface surface;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        PointA = findViewById(R.id.PointA);
        PointB = findViewById(R.id.PointB);
        ajouter = findViewById(R.id.ajouter);

        surface = new Surface(this);
        surface.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        surface.setBackgroundColor(Color.WHITE);
        p = new Path();
        Ecouteur ec = new Ecouteur();
        parent.addView(surface);
        ajouter.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // récupérer les coordonnées
            x = Integer.parseInt(PointA.getText().toString());
            y = Integer.parseInt(PointB.getText().toString());
            // ajouter les coordonnées à la liste
            if (p.isEmpty()) {
                 p.moveTo(x, y);
            } else {
                p.lineTo(x, y);
            }

            // redessiner la surface de dessin
            surface.invalidate();
        }

    }

    private class Surface extends View{

        Paint paint;
        public Surface(Context context){
            super(context);


            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(15);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(p, paint);

        }
    }
}

