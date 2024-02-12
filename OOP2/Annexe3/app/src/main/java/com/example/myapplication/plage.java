package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class plage extends AppCompatActivity {


    Ecouteur ec;
    ImageView avionImageVar, hotelImageVar;
    TextView avionTotal, hotelTotal;

    Button total_button;
    Commande commande = new Commande();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plage);
        avionImageVar = findViewById(R.id.avionImage);
        hotelImageVar = findViewById(R.id.hotelImage);
        total_button = findViewById(R.id.total_button);

        avionTotal = findViewById(R.id.avionTotal);
        hotelTotal = findViewById(R.id.hotelTotal);
        ec = new Ecouteur();
        avionImageVar.setOnClickListener(ec);
        hotelImageVar.setOnClickListener(ec);
        total_button.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{

        int sommeAvion = 0, sommeHotel = 0;


        @Override
        public void onClick(View v){
            if(v.getId() == R.id.avionImage){
                sommeAvion++;
                avionTotal.setText("Total: " + sommeAvion);
            }
            else if(v.getId() == R.id.hotelImage){
                sommeHotel++;
                hotelTotal.setText("Total: " + sommeHotel);
            }
            else if(v.getId() == R.id.total_button){
                commande.ajouterProduit(new BilletAvion(sommeAvion));
                commande.ajouterProduit(new HebergementHotel(sommeHotel));
                TextView total = findViewById(R.id.allTotal);
                total.setText("Total: " + commande.grandTotal());
                System.out.println(sommeAvion + " " + sommeHotel);
            }
        }
    }

}