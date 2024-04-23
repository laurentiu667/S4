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

    public Vector<Biere> select_biere() {
        Vector<Biere> v = new Vector();
        Cursor c = database.rawQuery("SELECT * FROM biere", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            v.add(new Biere(c.getString(1), c.getString(2), c.getFloat(3)));
            c.moveToNext();
        }
        c.close();
        return v;
    }



    public Vector<String> retournerMeilleur() throws Exception{
        Vector<String> v = new Vector();

        return v;
    }
}
