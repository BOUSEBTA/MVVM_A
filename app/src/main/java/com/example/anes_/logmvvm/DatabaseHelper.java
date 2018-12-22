package com.example.anes_.logmvvm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.anes_.logmvvm.viewmodel.UserModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context,"APP.db",null,1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (id INTEGER primary key autoincrement , username text ,password text )");
        db.execSQL("create table Enseignant (NumE text primary key, NomE text ,PrenomE text , Grade text ,NumCour text,NumSalle text , CONSTRAINT FK_ENSEIGNANT_COURS FOREIGN KEY(NumCour) REFERENCES Cours (NumCour), CONSTRAINT FK_ENSEIGNANT_SALLE FOREIGN KEY(NumSalle) REFERENCES Salle (NumSalle))");
        db.execSQL("create table Departement (CodeD text primary key, NomD text )");
        db.execSQL("create table Cours (NumCour  text primary key, Libelle text )");
        db.execSQL("create table Salle (NumSalle  text primary key, Capacite text )");
        db.execSQL("create table Etudiant(NumE text primary key, NomE text ,PrenomE text )");
        db.execSQL("create table EtudiantCours(NumE text , NumCour text ,Note INTEGER , CONSTRAINT PK_Note PRIMARY KEY (NumE,NumCour))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS Enseignant");
        db.execSQL("DROP TABLE IF EXISTS Departement");
        db.execSQL("DROP TABLE IF EXISTS Cours");
        db.execSQL("DROP TABLE IF EXISTS Salle");
        db.execSQL("DROP TABLE IF EXISTS Etudiant");
        db.execSQL("DROP TABLE IF EXISTS Note");
        onCreate(db);
    }


}