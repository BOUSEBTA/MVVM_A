package com.example.anes_.logmvvm.model;

public class Cours
{
    private String NumCour;
    private String Libelle;

    public Cours() {
    }

    public Cours(String numCour, String libelle) {
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
}
