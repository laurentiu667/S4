package com.example.annexe7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout parent;
    Surface surface;
    float startX, startY, endX, endY;

    TextView textView;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = findViewById(R.id.parent);

        surface = new Surface(this);
        surface.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        surface.setBackgroundResource(R.drawable.carte);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setCornerRadius(20);



        LinearLayout info = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        info.setLayoutParams(params);
        info.setOrientation(LinearLayout.VERTICAL);
        info.setBackground(gradientDrawable);
        info.setForegroundGravity(Gravity.CENTER);

        textView = new TextView(this);
        textView2 = new TextView(this);
        info.addView(textView);
        info.addView(textView2);


        parent.addView(surface);
        parent.addView(info);



        Ecouteur ec = new Ecouteur();
        surface.setOnTouchListener(ec);
    }

    private class Ecouteur implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
           if (event.getAction() == MotionEvent.ACTION_DOWN){
               startX = event.getX();
               startY = event.getY();
               surface.invalidate();
           } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE){
               endX = event.getX();
               endY = event.getY();
               surface.invalidate();
           }

            textView.setText(endX + " | " + endY);
            textView2.setText(startX + " | " + startY);
            return true;
        }



    }

    private class Surface extends View{
        Paint paint = new Paint();

        public Surface(Context context){
            super(context);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(15);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawLine(startX, startY, endX, endY, paint);
            canvas.drawRect(startX - 20, startY - 20, startX + 20, startY + 20, paint);
            canvas.drawRect(endX - 20, endY - 20, endX + 20, endY + 20, paint);

        }
    }
}
