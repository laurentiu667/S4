package com.example.tp2_code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp2_code.Outils.Background;
import com.example.tp2_code.Outils.Efface;
import com.example.tp2_code.Outils.Forme;
import com.example.tp2_code.Outils.Ovale;
import com.example.tp2_code.Outils.Pipelet;
import com.example.tp2_code.Outils.Rectangle;
import com.example.tp2_code.Outils.Trait;
import com.example.tp2_code.Outils.Triangle;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Composants
    LinearLayout parent, choixImage;
    TextView couleurActuelle;
    TableRow couleur;
    Surface surface;
    String hexColor = "#000000";

    // Sous classes
    Forme forme;
    Background bg;
    ColorDrawable color;
    trait trait;

    couleurNuage couleurNuage;
    Ecouteur ec;

    // liste des formes
    List<Forme> listeFormes = new ArrayList<>();
    // liste des formes efface
    List<Forme> listeFormesEfface = new ArrayList<>();

    String nomImage = "image";
    int chiffreimage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // initialiser les composants
        parent = findViewById(R.id.parent);
        couleur = findViewById(R.id.tableCouleur);
        choixImage = findViewById(R.id.choixImage);

        trait = new trait(this);
        couleurNuage = new couleurNuage(this, this);
        forme = new Trait(Color.parseColor(hexColor), recupererTaille());

        // initialiser la surface
        surface = new Surface(this);
        surface.setBackgroundColor(Color.parseColor("#FFFFFF"));
        parent.addView(surface);

        // initialiser les ecouteurs
        ec = new Ecouteur();
        surface.setOnTouchListener(ec);
        couleurActuelle = findViewById(R.id.couleurActuelle);
        couleurActuelle.setBackgroundColor(Color.parseColor(hexColor));

        // mettre les ecouteurs sur les boutons
        for (int i = 0; i < couleur.getChildCount(); i++) {
            couleur.getChildAt(i).setOnClickListener(ec);
        }
        // mettre les ecouteurs sur les images
        for (int i = 0; i < choixImage.getChildCount(); i++) {
            choixImage.getChildAt(i).setOnClickListener(ec);
        }

    }

    // Fonctions permettant d'ouvrir les dialogues
    private void dialog(){
        trait.show();
    }
    private void dialogCouleur(){
        couleurNuage.show();
    }

    // Fonction permettant de recuperer la taille du trait
    private int recupererTaille(){
        try {
            return trait.retournerTaille();
        } catch (Exception e){
            return 20;
        }

    }
    private class Ecouteur implements View.OnTouchListener, View.OnClickListener {

        // Variables pour toutes les formes
        private int x1, y1, x2, y2;
        // variable pour le triangle
        boolean cliquerTriangleVertex = false;

        @Override
        public void onClick(View v) {
            color = (ColorDrawable) surface.getBackground();
            // Si on clique sur un TextView on recupere la couleur
            if (v instanceof TextView) {
                hexColor = v.getTag().toString();
                couleurActuelle.setBackgroundColor(Color.parseColor(hexColor));
            }
            // Si on clique sur un ImageView on effectue creer une forme
            else if (v instanceof ImageView) {
                switch (v.getId()) {
                    case R.id.remplissage:
                        bg = new Background();
                        bg.setHexColor(hexColor);
                        bg.dessiner(surface);
                        break;
                    case R.id.effacer:
                        forme = new Efface(color.getColor(), recupererTaille());
                        break;
                    case R.id.crayon:
                        forme = new Trait(Color.parseColor(hexColor), recupererTaille());
                        break;
                    case R.id.rectangle:
                        forme = new Rectangle(Color.parseColor(hexColor), recupererTaille());
                        break;
                    case R.id.triangle:
                        forme = new Triangle(Color.parseColor(hexColor), recupererTaille());
                        break;
                    case R.id.ovale:
                        forme = new Ovale(Color.parseColor(hexColor), recupererTaille());
                        break;
                    case R.id.trait:
                        // Ouvrir le dialog pour choisir la taille du trait
                        dialog();
                        break;
                    case R.id.undo:
                        if (listeFormes.size() > 0) {
                            int lastIndex = listeFormes.size() - 1;
                            listeFormesEfface.add(listeFormes.get(lastIndex));
                            listeFormes.remove(lastIndex);
                            surface.invalidate();
                        }
                        break;
                    case R.id.redo:
                        if (listeFormesEfface.size() > 0) {
                            int lastIndex = listeFormesEfface.size() - 1;
                            listeFormes.add(listeFormesEfface.get(lastIndex));
                            listeFormesEfface.remove(lastIndex);
                            surface.invalidate();
                        }
                        break;
                    case R.id.pipelet:
                        Bitmap bitmapImage = surface.getBitmapImage();
                        forme = new Pipelet(Color.parseColor(hexColor), recupererTaille(), bitmapImage);
                        break;
                    case R.id.enregistrer:
                        Bitmap bitmap = surface.getBitmapImage();
                        try {
                            File directory = new File(Environment.getExternalStorageDirectory(), "Pictures");
                            if (!directory.exists()) {
                                directory.mkdirs(); //
                            }
                            File file = new File(directory, nomImage + ".png");
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            fos.close();
                            Toast.makeText(MainActivity.this, nomImage + "est enregistré", Toast.LENGTH_LONG).show();
                            // incrementer le chiffre de l'image
                            chiffreimage++;
                            nomImage = "image" + chiffreimage;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "erreur de path", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.palette:
                        // Ouvrire le dialog pour choisir la couleur en RGB
                        dialogCouleur();
                        break;
                }
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    x1 = (int) event.getX();
                    y1 = (int) event.getY();

                    // forme contient la forme cliquée lors de l'evenement onClick qui permet de creer une forme choisie
                   if (forme instanceof Trait) {
                        forme = new Trait(Color.parseColor(hexColor), recupererTaille());
                        ((Trait) forme).move_to(x1, y1);

                    } else if (forme instanceof Rectangle) {
                        forme = new Rectangle(Color.parseColor(hexColor), recupererTaille(), x1, y1, x1, y1);
                    } else if (forme instanceof Ovale) {
                        forme = new Ovale(Color.parseColor(hexColor), recupererTaille(), x1, y1, x1, y1);
                    } else if (forme instanceof Efface) {
                        forme = new Efface(color.getColor(), recupererTaille());
                        ((Efface) forme).move_to(x1, y1);
                    } else if (forme instanceof Pipelet) {
                        int pickedColor = ((Pipelet) forme).pickColor(x1, y1);
                        hexColor = String.format("#%06X", (0xFFFFFF & pickedColor));
                        couleurActuelle.setBackgroundColor(pickedColor);
                    } else if (forme instanceof Triangle) {
                        x1 = (int) event.getX();
                        y1 = (int) event.getY();
                        x2 = (int) event.getX();
                        y2 = (int) event.getY();
                        // si le click n est pas un vertex
                        if (cliquerTriangleVertex == false) {
                            cliquerTriangleVertex = true;
                        } else {
                            ((Triangle) forme).mettreAjourCoordonnee(x2, y2);
                            cliquerTriangleVertex = false;
                        }
                    }

                    // on ajoute les formes ici car on veut que la forme soit ajoutée seulement lorsqu'on clique
                    listeFormes.add(forme);
                    surface.invalidate();

                    return true;
                case MotionEvent.ACTION_MOVE:
                    x2 = (int) event.getX();
                    y2 = (int) event.getY();

                    // permet de dessiner la forme en fonction du mouvement
                    if (forme instanceof Trait) {
                        ((Trait) forme).line_to(x2, y2);
                    } else if (forme instanceof Rectangle) {
                        ((Rectangle) forme).mettreAjourCoordonnee(x1, y1, x2, y2);
                    } else if (forme instanceof Ovale) {
                        ((Ovale) forme).mettreAjourCoordonnee(x1, y1, x2, y2);
                    } else if (forme instanceof Efface) {
                        ((Efface) forme).line_to(x2, y2);
                    } else if (forme instanceof Triangle) {
                        x2 = (int) event.getX();
                        y2 = (int) event.getY();
                        forme = new Triangle(Color.parseColor(hexColor), recupererTaille(), x1, y1, x2, y2);
                    }
                    surface.invalidate();
                    return true;
                case MotionEvent.ACTION_UP:
                    // permet de choisir la couleur du pipelet lorsque quand c est relacher
                    if (forme instanceof Pipelet){
                        forme = new Trait(Color.parseColor(hexColor), recupererTaille());
                    }
                    return true;
            }
            return false;
        }
    }

    public class Surface extends View {

        public Surface(Context context) {
            super(context);
        }

        // fonction permettant de retourner la couleur choisie au pixel
        public Bitmap getBitmapImage() {
            this.buildDrawingCache();
            Bitmap bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();

            return bitmapImage;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (forme != null) {
                for (Forme forme : listeFormes) {
                    if (forme instanceof Efface) {
                        // changer la couleur de l efface en fonction de la couleur du fond
                        forme.setCouleur(color.getColor());
                    }
                    forme.dessiner(canvas);
                }
            }
        }
    }
}