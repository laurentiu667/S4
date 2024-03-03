package com.example.tp1_laurentiu_dilion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Declaration des variables
    ImageView filtre, americano, glace, latte;
    Chip petit, moyen, grand;
    Button ajouterCaisse, effacerCaisse, passerCommande;
    TextView detailcommande, totalargent;
    LinearLayout ImageCaisseCaffe;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variables
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

        // écouteur pour chaque composants
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
                detailcommande.setText("Café " + nomProduit + " " + cafeChoisi.getCalorie() + " cal " + tailleProduit + " " + nombreFormate);
                return cafeChoisi;
            }
        }
        return null;
    }
    private class Ecouteur implements View.OnClickListener {
        String nomProduit = null;
        String tailleProduit = "petit";
        Commande commande = new Commande();
        ImageView enAttente = null;
        @Override
        public void onClick(View v) {


            // Recuperer le nom de l'image pour chaque
            if (v instanceof ImageView) {
                nomProduit = v.getTag().toString();
                Drawable drawable = ((ImageView) v).getDrawable();
                enAttente = new ImageView(MainActivity.this);

                enAttente.setImageDrawable(drawable);

            } else if (v instanceof Chip) {
                ((Chip) v).setChipIconResource(R.drawable.crochet);
                tailleProduit = v.getTag().toString();
            }
            // AjouterProduit retourne le bon café grâce à la clé de la hashtable et la stocke dans une variable cafe
            Cafe cafe = ajouterProduit(nomProduit, tailleProduit);
            if (v.getId() == R.id.ajouterCaisse) {
                // Ajouter le type de café ( produit ) pour calculer son prix + taxes + calories
                commande.ajouter_boisson(cafe);
                totalargent.setText(String.format("%.2f $", commande.totalTaxes()));

                // Reinitialisation de la taille du café
                petit.setChecked(true);
                tailleProduit = "petit";

                // Ajout du café ( image ) dans la caisse

                ImageView img = new ImageView(MainActivity.this);
                img.setImageDrawable(enAttente.getDrawable());
                img.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
                img.setPadding(10, 10, 10, 10);

                // Ajoutez l'image au LinearLayout parent
                ImageCaisseCaffe.addView(img);
                ajouterProduit(nomProduit, tailleProduit);

            } else if (v.getId() == R.id.effacerCaisse) {
                commande.reset();

                totalargent.setText(commande.getTotal() + " $");
                detailcommande.setText("Détails de la commande");
                ajouterCaisse.setEnabled(false);
                ajouterCaisse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#96B1A8")));
                petit.setChipIconResource(R.drawable.crochet);
                ImageCaisseCaffe.removeAllViews();
                petit.setChecked(true);
                tailleProduit = "petit";
            } else if (v.getId() == R.id.passerCommande) {
                ImageCaisseCaffe.removeAllViews();
                ajouterCaisse.setEnabled(false);
                ajouterCaisse.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#96B1A8")));
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Commande envoyée");
                builder.setMessage("paiement de " + String.format("%.2f $", commande.getTotal()) + " en cours");
                builder.show();
            }

        }
    }
}