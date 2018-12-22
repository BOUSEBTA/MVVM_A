package com.example.anes_.logmvvm.viewmodel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.model.Cours;
import com.example.anes_.logmvvm.model.Note;

import java.util.ArrayList;

public class EtudiantModel
{
    private String NumE;
    private String NomE;
    private String PrenomE;


    public EtudiantModel() {
    }

    public EtudiantModel(String numE, String nomE, String prenomE) {
        NumE = numE;
        NomE = nomE;
        PrenomE = prenomE;
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



    public boolean insertEtudiant (DatabaseHelper dbh, String numE , String nomE , String prenomE ){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumE",numE);
        contentValues.put("NomE",nomE);
        contentValues.put("PrenomE",prenomE);
        long res = db.insert("Etudiant",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertEtudiant: INSERTEEEEEEEEED");
        return true;
    }

    public boolean insertNote(DatabaseHelper dbh , String numE , String numCour, String note){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumE",numE);
        contentValues.put("NumCour",numCour);
        contentValues.put("Note",note);
        long res = db.insert("EtudiantCours",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertNote: INSERTEEEEEEEEED");
        return true;
    };

    public Cursor FindAll(DatabaseHelper dbh)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Etudiant" , null);
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

    public Cursor printEtudiant(DatabaseHelper dbh, String numEtudiant)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Etudiant where NumE = ? " , new String[] { numEtudiant});
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

    public Cursor printNoteEtudiant(DatabaseHelper dbh, String numEtudiant)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from EtudiantCours where NumE = ? " , new String[] { numEtudiant});
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

    public void deleteEtudiant(DatabaseHelper dbh, String numEtudiant)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.delete("Etudiant","NumE = ?", new String []{numEtudiant});
    }

    public void updateEtudiant(DatabaseHelper dbh, String numEtudiant,String nomEtudiant,String PrenomEtudiant )
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NomE",nomEtudiant);
        contentValues.put("PrenomE",PrenomEtudiant);
        db.update("Etudiant",contentValues,"NumE = ?", new String []{numEtudiant});

    }

    /*public boolean printNoteEtudiant(DatabaseHelper dbh)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from Note" , null);
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
            return false;
        }
        Note note = new Note();
        while(Cr.moveToNext()){
            note.setNumE(Cr.getString(0));
            note.setNumCour(Cr.getString(1));
            note.setNote(Cr.getString(2));
            Notes.add(note);
        }

        return true;
    }*/
}
