package com.example.anes_.logmvvm.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anes_.logmvvm.DatabaseHelper;

public class SalleModel
{
    private String NumSalle;
    private String Capacite;

    public SalleModel() {
    }

    public SalleModel(String numSalle, String capacite) {
        NumSalle = numSalle;
        Capacite = capacite;
    }

    public String getNumSalle() {
        return NumSalle;
    }

    public void setNumSalle(String numSalle) {
        NumSalle = numSalle;
    }

    public String getCapacite() {
        return Capacite;
    }

    public void setCapacite(String capacite) {
        Capacite = capacite;
    }

    public boolean insertSalle (DatabaseHelper dbh, String numSalle , String capacite ){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumSalle",numSalle);
        contentValues.put("Capacite",capacite);

        long res = db.insert("Salle",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertSalle: INSERTEEEEEEEEED");
        return true;
    }

    public Cursor FindAll(DatabaseHelper dbh)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Salle" , null);
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

}
