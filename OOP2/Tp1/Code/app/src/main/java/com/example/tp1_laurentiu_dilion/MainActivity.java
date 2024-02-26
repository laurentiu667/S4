package com.example.tp1_laurentiu_dilion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.example.tp1_laurentiu_dilion.ListeProduits;
import com.example.tp1_laurentiu_dilion.Commande;

import java.text.DecimalFormat;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    // Declaration des variables
    ImageView filtre, americano, glace, latte, enAttente;
    Chip petit, moyen, grand;
    Button ajouterCaisse, effacerCaisse, passerCommande;
    TextView detailcommande, totalargent;
    LinearLayout ImageCaisseCaffe;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of variables
        filtre = findViewById(R.id.filtre);
        americano = findViewById(R.id.americano);
        glace = findViewById(R.id.glace);
        latte = findViewById(R.id.latte);
        petit = findViewById(R.id.petit);
        moyen = findViewById(R.id.moyen);
        grand = findViewById(R.id.grand);
        ajouterCaisse = findViewById(R.id.ajouterCaisse);
        effacerCaisse = findViewById(R.id.effacerCaisse);
        passerCommande = findViewById(R.id.passerCommande);
        detailcommande = findViewById(R.id.detailcommande);
        ImageCaisseCaffe = findViewById(R.id.ImageCaisseCaffe);
        totalargent = findViewById(R.id.totalargent);

        // Adding listeners
        ec = new Ecouteur();
        filtre.setOnClickListener(ec);
        americano.setOnClickListener(ec);
        glace.setOnClickListener(ec);
        latte.setOnClickListener(ec);
        petit.setOnClickListener(ec);
        moyen.setOnClickListener(ec);
        grand.setOnClickListener(ec);
        ajouterCaisse.setOnClickListener(ec);
        effacerCaisse.setOnClickListener(ec);
        passerCommande.setOnClickListener(ec);
        ajouterCaisse.setEnabled(false);
    }
    private void retirerChipIcon(Chip chip) {
        chip.setChipIcon(null);
    }
    private Cafe ajouterProduit(String nomProduit, String tailleProduit) {
        DecimalFormat df = new DecimalFormat("#.##$");
        ListeProduits listeProduits = new ListeProduits();

        if (nomProduit != null) {
            ajouterCaisse.setEnabled(true);
            ajouterCaisse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00704A")));

            if (tailleProduit != null) {
                String cle = nomProduit + " " + tailleProduit;
                Cafe cafeChoisi = listeProduits.getCafe(cle);
                String nombreFormate = df.format(cafeChoisi.getPrix());
                detailcommande.setText("Café " + nomProduit + " " + cafeChoisi.getCalorie() + " cal " + nombreFormate );
                return cafeChoisi;
            }
        }

        return null;
    }
    private class Ecouteur implements View.OnClickListener {
        String nomProduit = null;

        String tailleProduit = "petit";
        Commande commande = new Commande();
        ImageView enAttente = new ImageView(MainActivity.this);
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.filtre) {
                nomProduit = "filtre";
                if (v instanceof ImageView) {
                    Drawable drawable = ((ImageView) v).getDrawable();
                    if (drawable != null) {

                        enAttente.setImageDrawable(drawable);
                    }
                }
            } else if (v.getId() == R.id.americano) {
                nomProduit = "americano";
                if (v instanceof ImageView) {
                    Drawable drawable = ((ImageView) v).getDrawable();
                    if (drawable != null) {

                        enAttente.setImageDrawable(drawable);
                    }
                }
            } else if (v.getId() == R.id.glace) {
                nomProduit = "glace";
                if (v instanceof ImageView) {
                    Drawable drawable = ((ImageView) v).getDrawable();
                    if (drawable != null) {

                        enAttente.setImageDrawable(drawable);
                    }
                }
            } else if (v.getId() == R.id.latte) {
                nomProduit = "latte";
                if (v instanceof ImageView) {
                    Drawable drawable = ((ImageView) v).getDrawable();
                    if (drawable != null) {

                        enAttente.setImageDrawable(drawable);
                    }
                }
            } else if (v.getId() == R.id.petit) {
                petit.setChipIconResource(R.drawable.crochet);
                retirerChipIcon(moyen);
                retirerChipIcon(grand);
                tailleProduit = "petit";
            } else if (v.getId() == R.id.moyen) {
                moyen.setChipIconResource(R.drawable.crochet);
                retirerChipIcon(petit);
                retirerChipIcon(grand);
                tailleProduit = "moyen";
            } else if (v.getId() == R.id.grand) {
                grand.setChipIconResource(R.drawable.crochet);
                retirerChipIcon(petit);
                retirerChipIcon(moyen);
                tailleProduit = "grand";
            }


            Cafe cafe = ajouterProduit(nomProduit, tailleProduit);
            if (v.getId() == R.id.ajouterCaisse) {
                commande.ajouter_boisson(cafe);
                totalargent.setText(String.format("%.2f $", commande.totalTaxes()));

                petit.setChipIconResource(R.drawable.crochet);
                retirerChipIcon(moyen);
                retirerChipIcon(grand);
                tailleProduit = "petit";

            }
            else if (v.getId() == R.id.effacerCaisse) {
                commande.reset();
                totalargent.setText(commande.getTotal() + " $");
                detailcommande.setText("Détails de la commande");
                ajouterCaisse.setEnabled(false);
                ajouterCaisse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#96B1A8")));
                petit.setChipIconResource(R.drawable.crochet);
                retirerChipIcon(moyen);
                retirerChipIcon(grand);
                tailleProduit = "petit";
            } else if (v.getId() == R.id.passerCommande) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Commande envoyée");
                builder.setMessage("paiement de " + String.format("%.2f $", commande.getTotal()) + " en cours");
                builder.show();
            }


            if (v.getId() != R.id.effacerCaisse){
                ajouterProduit(nomProduit, tailleProduit);
            }

        }


    }
}

