package com.eric.appsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

// Classe pour la gestion de la base de données SQLite
public class GestionBD extends SQLiteOpenHelper {

    // Instance unique de la classe Singleton GestionBD
    private static GestionBD instance;
    private SQLiteDatabase database;

    // Méthode pour obtenir une instance de la base de données
    public static GestionBD getInstance(Context context) {
        if (instance == null)
            instance = new GestionBD(context);
        return instance;
    }

    // Constructeur privé pour la classe Singleton GestionBD
    private GestionBD(Context context) {
        super(context, "db", null, 1); // Nom de la base de données et version
    }

    // Méthode appelée lors de la création de la base de données
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la table "inventeur" avec ses colonnes
        db.execSQL("CREATE TABLE inventeur (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nom TEXT, " +
                "Origine TEXT, " +
                "Invention TEXT, " +
                "Annee INTEGER)");

        // Ajout d'exemples d'inventeurs à la base de données lors de sa création
        ajouter_inventeur(new Inventeur("Laszlo Biro", "Hongrie", "Stylo à Bille", 1938), db);
        ajouter_inventeur(new Inventeur("Benjamin Franklin", "Etats-Unis", "Paratonnerre", 1752), db);
        ajouter_inventeur(new Inventeur("Mary Anderson", "Etats-Unis", "Essuie-glace", 1903), db);
        ajouter_inventeur(new Inventeur("Grace Hopper", "Etats-Unis", "Compilateur", 1952), db);
        ajouter_inventeur(new Inventeur("Benoit Rouquayrot", "France", "Scaphandre", 1864), db);
    }

    // Méthode pour ajouter un inventeur à la base de données
    public void ajouter_inventeur(Inventeur inventeur, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("Nom", inventeur.getNom());
        values.put("Origine", inventeur.getOrigine());
        values.put("Invention", inventeur.getInvention());
        values.put("Annee", inventeur.getAnnee());
        db.insert("inventeur", null, values);
    }

    // Méthode appelée lors d'une mise à jour de la base de données
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Suppression de la table "inventeur" si elle existe déjà
        db.execSQL("DROP TABLE IF EXISTS inventeur");
        // Recréation de la table après la suppression
        onCreate(db);
    }

    // Méthode pour ouvrir la base de données en lecture
    public void ouvrir_database() {
        database = this.getReadableDatabase();
    }

    // Méthode pour fermer la base de données
    public void fermer_database() {
        database.close();
    }

    // Méthode pour récupérer la liste des inventions depuis la base de données
    public Vector<String> select_invention() {
        Vector<String> v = new Vector<>();
        Cursor requete = database.rawQuery("select invention from inventeur", null);
        while (requete.moveToNext()) {
            v.add(requete.getString(0));
        }
        requete.close();
        return v;
    }

    // Méthode pour récupérer la liste des inventeurs depuis la base de données
    public Vector<String> select_inventeur() {
        Vector<String> v = new Vector<>();
        Cursor requete = database.rawQuery("select nom from inventeur", null);
        while (requete.moveToNext()) {
            v.add(requete.getString(0));
        }
        requete.close();
        return v;
    }

    // Méthode pour vérifier si une réponse est correcte (invention correspondant à l'inventeur)
    public boolean aBonneReponse(String nom, String invention) {
        String[] Tab = {nom, invention};
        Cursor cursor = database.rawQuery("select invention from inventeur where nom = ? and invention = ?", Tab);
        return cursor.moveToFirst(); // Retourne true si un enregistrement correspondant est trouvé, sinon false
    }
}
