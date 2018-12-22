package com.example.anes_.logmvvm.viewmodel;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anes_.logmvvm.DatabaseHelper;

public class DepartementModel
{
    private String CodeD;
    private String NomD;

    public DepartementModel() {
    }

    public DepartementModel(String codeD, String nomD) {
        CodeD = codeD;
        NomD = nomD;
    }

    public String getCodeD() {
        return CodeD;
    }

    public void setCodeD(String codeD) {
        CodeD = codeD;
    }

    public String getNomD() {
        return NomD;
    }

    public void setNomD(String nomD) {
        NomD = nomD;
    }

    public boolean insertDepartement (DatabaseHelper dbh, String codeD , String nomD ){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CodeD",codeD);
        contentValues.put("NomD",nomD);

        long res = db.insert("Departement",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertDepartement: INSERTEEEEEEEEED");
        return true;
    }
}
