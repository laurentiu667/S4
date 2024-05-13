package com.example.code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Menu extends Dialog {

    Context context;
    public Menu(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    ImageView quittermenu;
    LinearLayout nouvellepartie, classementmenu, quitterjeux;
    Ecouteur ecouteur;
    Animation slideInAnimation, slideOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ecouteur = new Ecouteur();

        quittermenu = findViewById(R.id.sortirMenu);

        nouvellepartie = findViewById(R.id.partiemenu);
        classementmenu = findViewById(R.id.classementmenu);
        quitterjeux = findViewById(R.id.quittermenu);

        quittermenu.setOnClickListener(ecouteur);
        nouvellepartie.setOnClickListener(ecouteur);
        classementmenu.setOnClickListener(ecouteur);
        quitterjeux.setOnClickListener(ecouteur);



        slideInAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in);
        slideOutAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_out);
        quittermenu.startAnimation(slideInAnimation);
        nouvellepartie.startAnimation(slideInAnimation);
        classementmenu.startAnimation(slideInAnimation);
        quitterjeux.startAnimation(slideInAnimation);

    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == quittermenu){

                quittermenu.startAnimation(slideOutAnimation);
                nouvellepartie.startAnimation(slideOutAnimation);
                classementmenu.startAnimation(slideOutAnimation);
                quitterjeux.startAnimation(slideOutAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 500);
            } else if (v == nouvellepartie){
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            } else if (v == classementmenu){
                Intent intent = new Intent(context, scorepage.class);
                context.startActivity(intent);
            } else if (v == quitterjeux){
                Intent intent = new Intent(context, BeginPage.class);
                context.startActivity(intent);
            }
        }
    }

}