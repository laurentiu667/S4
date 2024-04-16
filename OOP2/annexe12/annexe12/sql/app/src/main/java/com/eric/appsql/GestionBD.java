package com.eric.appsql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;


public class GestionBD extends SQLiteOpenHelper {

    //instance unique de la classe Singleton GestionBD
    private static GestionBD instance;
    private SQLiteDatabase database;

    // méthode de base pour un Singleton
    public static GestionBD getInstance(Context contexte) {
        if (instance == null)
            instance = new GestionBD(contexte);
        return instance;
    }

    private GestionBD(Context context) {
        super(context, "db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE inventeur (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nom TEXT, " +
                "Origine TEXT, " +
                "Invention TEXT, " +
                "Annee INTEGER)");


        ajouter_inventeur(new Inventeur("Laszlo Biro", "Hongrie", "Stylo à Bille", 1938), db);
        ajouter_inventeur(new Inventeur("Benjamin Franklin", "Etats-Unis", "Paratonnerre", 1752), db);
        ajouter_inventeur(new Inventeur("Mary Anderson", "Etats-Unis", "Essuie-glace", 1903), db);
        ajouter_inventeur(new Inventeur("Grace Hopper", "Etats-Unis", "Compilateur", 1952), db);
        ajouter_inventeur(new Inventeur("Benoit Rouquayrot", "France", "Scaphandre", 1864), db);


    }
    public void ajouter_inventeur(Inventeur inventeur, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("Nom", inventeur.getNom());
        values.put("Origine", inventeur.getOrigine());
        values.put("Invention", inventeur.getInvention());
        values.put("Annee", inventeur.getAnnee());
        db.insert("inventeur", null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void ouvrir_database(){
        database = this.getReadableDatabase();
    }
    public void fermer_database(){
        database.close();
    }
    public Vector<String> select_inventeur(){
        Vector<String> v = new Vector<>();
        Cursor requete = database.rawQuery("select invention from inventeur", null);
        while (requete.moveToNext()){
            v.add(requete.getString(0));
        }
        requete.close();
        return v;
    }

    public boolean aBonneReponse(String nom, String invention){
        String[] Tab = {nom, invention};
        Cursor cursor = database.rawQuery("select invention from inventeur where nom = ? and invention = ?", null);
        return cursor.moveToFirst();
    }

}
