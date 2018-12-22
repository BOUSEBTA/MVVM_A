package com.example.anes_.logmvvm.model;

import java.util.Date;

public class Etudiant
{
    private String NumE;
    private String NomE;
    private String PrenomE;

    public Etudiant() {
    }

    public Etudiant(String numE, String nomE, String prenomE) {
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

}
