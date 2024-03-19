package com.example.tp2_code;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout parent;
    Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = findViewById(R.id.parent);

        surface = new Surface(this);
        surface.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));

        surface.setBackgroundColor(getResources().getColor(R.color.black));


        parent.addView(surface);

        Ecouteur ec = new Ecouteur();
        surface.setOnTouchListener(ec);
    }

    private class Ecouteur implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            return false;
        }

    }

    private class Surface extends View {
        Paint paint = new Paint();

        public Surface(Context context){
            super(context);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(15);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);


        }
    }


}