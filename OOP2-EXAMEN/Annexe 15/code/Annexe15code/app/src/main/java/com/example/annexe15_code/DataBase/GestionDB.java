package com.example.annexe15_code.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.annexe15_code.Equipe;

import java.util.Vector;

public class GestionDB extends SQLiteOpenHelper {

    private static GestionDB instance;
    private SQLiteDatabase database;

    public static GestionDB getInstance(Context context){
        if (instance == null){
            instance = new GestionDB(context);
        }
        return instance;
    }

    private GestionDB(Context context){
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE EquipesLHJMQ (_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, Division TEXT, Arena TEXT, Capacite INTEGER)");

        inseration_data(new Equipe("Tigres de Victoriavillle", "Est", "Colisée Desjardins", 1900), db);
        inseration_data(new Equipe("Cataractes de Shawinigan", "Est", "Centre Gervais Auto", 4000), db);
        inseration_data(new Equipe("Olympiques de Gatineau", "Ouest", "Centre Slush Puppie", 4200), db);
        inseration_data(new Equipe("Foreurs de Val d’Or", "Ouest", "Centre Agnico Eagle", 2600), db);
        inseration_data(new Equipe("Armada de Blainville", "Ouest", "Centre Rousseau", 3000), db);
    }

    // Méthode appelée lors d'une mise à jour de la base de données
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Suppression de la table "inventeur" si elle existe déjà
        db.execSQL("DROP TABLE IF EXISTS EquipesLHJMQ");
        // Recréation de la table après la suppression
        onCreate(db);
    }

    public void inseration_data(Equipe e, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", e.getNom());
        contentValues.put("Division", e.getDivision());
        contentValues.put("Arena", e.getArena());
        contentValues.put("Capacite", e.getCapacite());
        db.insert("EquipesLHJMQ", null, contentValues);
    }
    public double moyenne_capacite(){
        double moyenne = 0.0;
        SQLiteDatabase db = this.getReadableDatabase();
        db.rawQuery("SELECT AVG(Capacite) FROM EquipesLHJMQ", null);
        Cursor cursor = db.rawQuery("SELECT Capacite FROM EquipesLHJMQ", null);
        while (cursor.moveToNext()){
            moyenne += cursor.getInt(0);
        }
        return moyenne / cursor.getCount();
    }
    public String returnerNomEquipeEnPrenantLArene(String nomArene){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT nom FROM EquipesLHJMQ WHERE Arena = ?", new String[]{nomArene});
        if (cursor.moveToNext()){
            return cursor.getString(0);
        }
        return "Aucune équipe trouvée pour cette arène";
    }

    public Vector<String> retournerLesArena(){
        Vector<String> lesArena = new Vector<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Arena FROM EquipesLHJMQ", null);
        while (cursor.moveToNext()){
            lesArena.add(cursor.getString(0));
        }
        return lesArena;
    }
    public int nombreDeOuest(){
        int nombre = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Division FROM EquipesLHJMQ WHERE Division = 'Ouest'", null);
        while (cursor.moveToNext()){
            nombre++;
        }
        cursor.close();
        return nombre;
    }
    // Méthode pour ouvrir la base de données en lecture
    public void ouvrir_database() {
        database = this.getReadableDatabase();
    }

    // Méthode pour fermer la base de données
    public void fermer_database() {
        database.close();
    }

}