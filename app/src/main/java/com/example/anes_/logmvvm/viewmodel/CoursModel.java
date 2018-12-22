package com.example.anes_.logmvvm.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anes_.logmvvm.DatabaseHelper;

public class CoursModel {

    private String NumCour;
    private String Libelle;

    public CoursModel() {
    }

    public CoursModel(String numCour, String libelle) {
        NumCour = numCour;
        Libelle = libelle;
    }

    public String getNumCour() {
        return NumCour;
    }

    public void setNumCour(String numCour) {
        NumCour = numCour;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public boolean insertCours (DatabaseHelper dbh, String numCour , String libelle ){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumCour",numCour);
        contentValues.put("Libelle",libelle);

        long res = db.insert("Cours",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertCours: INSERTEEEEEEEEED");
        return true;
    }

    public Cursor FindAll(DatabaseHelper dbh)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Cours" , null);
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }
}
