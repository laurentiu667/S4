package com.example.annexe6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {


    ConstraintLayout parent;
    Surface surf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = findViewById(R.id.parent);

        // 1 ere etape
        surf = new Surface(this);
        // 2 eme etape
//        surf.setLayoutParams(new ConstraintLayout.LayoutParams(convertirEnPixels(400), convertirEnPixels(500)));

        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        surf.setBackgroundColor(Color.RED);
        // 3 eme etape
        parent.addView(surf);
    }


    public int convertirEnPixels ( int dp) {
        float densiteTel = this.getResources().getDisplayMetrics().density;
        return Math.round(dp * densiteTel);
    }
    private class Surface extends View {

        Paint crayon;

        public Surface(Context context) {
            super(context);
            // ADOUCIR LES COURBES, anticrenelage
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.BLACK);
            crayon.setStyle(Paint.Style.STROKE); //  par default le style est plein ( FILL )
            crayon.setStrokeWidth(30);// EN PIXEL

        }



        // appeler au depart et a chaque fois que j appele la mathode invalidate
        // appeler egalemtn lorsqu el app est endommagee ( lorsque une autre app apprait par exemple lors d un appel)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(200,200,100, crayon);

            crayon.setColor(Color.BLUE);
            crayon.setStyle(Paint.Style.FILL);

            canvas.drawCircle(450,200,100, crayon);

            crayon.setColor(Color.BLUE);
            crayon.setStyle(Paint.Style.FILL);

            canvas.drawArc(100,400,500,800,0,90,true,crayon);
            crayon.setColor(Color.WHITE);
            canvas.drawArc(100,400,500,800,90,90,true,crayon);
            crayon.setColor(ContextCompat.getColor(MainActivity.this, R.color.purple_500));
            canvas.drawArc(100,400,500,800,180,90,true,crayon);

            crayon.setColor(Color.WHITE);
            canvas.drawArc(100,400,500,800,270,90,true,crayon);

        }
    }

}