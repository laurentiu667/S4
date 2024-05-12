package com.example.code;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

public class GestionDB extends SQLiteOpenHelper {
    private static GestionDB instance;
    SQLiteDatabase database;
    Context context;

    public static GestionDB getInstance(Context context) {
        if (instance == null)
            instance = new GestionDB(context);
        return instance;
    }

    GestionDB(Context context) {
        super(context, "db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE score (_id INTEGER PRIMARY KEY AUTOINCREMENT, Score INTEGER);");
    }


    public void ajouter_score(Score score, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("Score", score.calculerScoreTotal());

        db.insert("score", null, values);

    }
    public void ajouter_new_score(Score score) {
        ouvrire_db(); // Assurez-vous que la base de données est ouverte avant l'insertion

        ajouter_score(score, database); // Utilisez le score créé pour l'ajouter à la base de données
        fermer_db(); // Fermez la base de données après l'insertion
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS score");
        onCreate(db);
    }

    public void ouvrire_db() {
        database = getWritableDatabase();
    }

    public void fermer_db() {
        database.close();
    }

    public Vector<Integer> retournerTousLesScores(){
        Vector<Integer> scores = new Vector<>();
        // Ouvrir la base de données
        SQLiteDatabase db = this.getReadableDatabase();
        // Définir la requête SQL
        String query = "SELECT * FROM score ORDER BY Score DESC;";
        // Exécuter la requête
        Cursor cursor = db.rawQuery(query, null);
        // Parcourir les résultats
        while (cursor.moveToNext()) {
            scores.add(cursor.getInt(1));
        }
        // Fermer le curseur et la base de données
        cursor.close();
        db.close();
        // Retourner la liste des scores
        return scores;
    }


}
