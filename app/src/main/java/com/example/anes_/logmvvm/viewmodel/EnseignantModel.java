package com.example.anes_.logmvvm.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.model.Enseignant;

import java.util.Date;

public class EnseignantModel
{
    private String NumE;
    private String NomE;
    private String PrenomE;
    private String Grade;
    private String NumCour;

    public EnseignantModel() {
    }

    public EnseignantModel(String numE, String nomE, String prenomE, String grade, String numCour) {
        NumE = numE;
        NomE = nomE;
        PrenomE = prenomE;
        Grade = grade;
        NumCour = numCour;
    }

    public String getNumE() {
        return NumE;
    }

    public void setNumE(String numE) {
        NumE = numE;
    }

    public String getNomE() {
        return NomE;
    }

    public void setNomE(String nomE) {
        NomE = nomE;
    }

    public String getPrenomE() {
        return PrenomE;
    }

    public void setPrenomE(String prenomE) {
        PrenomE = prenomE;
    }




    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getNumCour() {
        return NumCour;
    }

    public void setNumCour(String numCour) {
        NumCour = numCour;
    }

    public boolean insertEnseignant (DatabaseHelper dbh, String numE , String nomE , String prenomE , String grade, String numCour,String numSalle){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumE",numE);
        contentValues.put("NomE",nomE);
        contentValues.put("PrenomE",prenomE);
        contentValues.put("Grade",grade);
        contentValues.put("NumCour",numCour);
        contentValues.put("NumSalle",numSalle);
        long res = db.insert("Enseignant",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertEnseignant: INSERTEEEEEEEEED");
        return true;
    }

    public Cursor FindAll(DatabaseHelper dbh)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Enseignant" , null);
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

    public Cursor printEnseignant(DatabaseHelper dbh, String numEnseignant)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Enseignant where NumE = ? " , new String[] { numEnseignant});
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

    public void deleteEnseignant(DatabaseHelper dbh, String numEnseignant)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
       db.delete("Enseignant","NumE = ?", new String []{numEnseignant});
    }

    public void updateEnseignant(DatabaseHelper dbh, String numEnseignant,String nomEnseignant,String PrenomEnseignant , String gradeEnseignant)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NomE",nomEnseignant);
        contentValues.put("PrenomE",PrenomEnseignant);
        contentValues.put("Grade",gradeEnseignant);
        db.update("Enseignant",contentValues,"NumE = ?", new String []{numEnseignant});

    }

}
