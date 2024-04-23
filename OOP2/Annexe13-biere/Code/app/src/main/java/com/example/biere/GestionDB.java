package com.example.biere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

public class GestionDB extends SQLiteOpenHelper {
    private static GestionDB instance;
    private SQLiteDatabase database;

    public static GestionDB getInstance(Context context) {
        if (instance == null)
            instance = new GestionDB(context);
        return instance;
    }

    private GestionDB(Context context) {
        super(context, "db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE biere (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nom TEXT, " +
                "Brasserie TEXT," +
                "Note FLOAT)");
    }

    public void ajouter_biere(Biere biere, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("Nom", biere.getNom());
        values.put("Brasserie", biere.getBrasserie());
        values.put("Note", biere.getNote());
        db.insert("biere", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS biere");
        onCreate(db);
    }

    public void ouvrire_db() {
        database = getWritableDatabase();
    }

    public void fermer_db() {
        database.close();
    }

    public void ajouter_new_biere( String nom, String brasserie, float note) {
        ajouter_biere(new Biere(nom, brasserie, note), database);
    }

    public Vector<String> select_biere() {
        Vector<String> v = new Vector<>();
        Cursor requete = database.rawQuery("select Nom from biere", null);
        while (requete.moveToNext()) {
            v.add(requete.getString(0));
        }
        requete.close();
        return v;
    }

    public Vector<String> select_brasserie() {
        Vector<String> v = new Vector<>();
        Cursor requete = database.rawQuery("select Brasserie from biere", null);
        while (requete.moveToNext()) {
            v.add(requete.getString(0));
        }
        requete.close();
        return v;
    }

    public Vector<String> select_note() {
        Vector<String> v = new Vector<>();
        Cursor requete = database.rawQuery("select Note from biere", null);
        while (requete.moveToNext()) {
            v.add(requete.getString(0));
        }
        requete.close();
        return v;
    }

    public Vector<String> retournerMeilleur() {
        Vector<String> top3Biere = new Vector<>();
        // Ouvrir la base de données
        SQLiteDatabase db = this.getReadableDatabase();
        // Définir la requête SQL
        String query = "SELECT * FROM Biere ORDER BY note DESC LIMIT 3";
        // Exécuter la requête
        Cursor cursor = db.rawQuery(query, null);
        // Parcourir les résultats
        while (cursor.moveToNext()) {
            top3Biere.add(cursor.getString(1));
        }
        // Fermer le curseur et la base de données
        cursor.close();
        db.close();
        // Retourner la liste des 3 meilleures bières
        return top3Biere;
    }
}
