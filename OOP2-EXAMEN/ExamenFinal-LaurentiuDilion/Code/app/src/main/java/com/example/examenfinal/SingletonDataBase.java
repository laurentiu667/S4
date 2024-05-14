package com.example.examenfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;


public class SingletonDataBase extends SQLiteOpenHelper {
    private static SingletonDataBase instance;
    public SQLiteDatabase database;
    Context context;

    public static SingletonDataBase getInstance(Context context){
        if (instance == null)
            instance = new SingletonDataBase(context);
        return instance;
    }

    public SingletonDataBase(Context context) {
        super(context, "app", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table drapeau ( _id INTEGER PRIMARY KEY AUTOINCREMENT, couleurG TEXT, couleurC TEXT, couleurD TEXT, pays TEXT);");
        insererDrapeau(sqLiteDatabase, new Drapeau("bleu", "blanc", "rouge", "France"));
        insererDrapeau(sqLiteDatabase, new Drapeau("rouge", "blanc", "rouge", "Pérou"));
        insererDrapeau(sqLiteDatabase, new Drapeau("bleu", "jaune", "rouge", "Roumanie"));
        insererDrapeau(sqLiteDatabase, new Drapeau("noir", "jaune", "rouge", "Belgique"));
    }

    public void insererDrapeau (SQLiteDatabase sqLiteDatabase, Drapeau d )
    {
        ContentValues cv = new ContentValues();
        cv.put("couleurG", d.getCouleur_gauche());
        cv.put("couleurC", d.getCouleur_centre());
        cv.put("couleurD", d.getCouleur_droite());
        cv.put("pays", d.getNom_pays());
        sqLiteDatabase.insert("drapeau", null, cv );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table drapeau");
        onCreate(sqLiteDatabase);
    }

    public String retourner_pays_hasard(){
        Random r = new Random();
        String query = "SELECT COUNT(*) FROM drapeau";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        int randomRow = r.nextInt(count);
        query = "SELECT pays FROM drapeau LIMIT 1 OFFSET " + randomRow;
        cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public boolean pays_verif(String pays, String cg, String cc, String cd){
        String query = "SELECT * FROM drapeau WHERE pays = ? AND couleurG = ? AND couleurC = ? AND couleurD = ?";
        Cursor cursor = database.rawQuery(query, new String[]{pays, cg, cc, cd});
        boolean match = cursor.moveToFirst();
        cursor.close();
        return match;
    }
    public void ouvrire_db() {
        database = getWritableDatabase();
    }

    public void fermer_db() {
        database.close();
    }

}
