package com.example.anes_.logmvvm.model;

import java.util.Date;

public class Enseignant
{
    private String NumE;
    private String NomE;
    private String PrenomE;

    private String Grade;

    public Enseignant() {
    }

    public Enseignant(String numE, String nomE, String prenomE, String grade) {
        NumE = numE;
        NomE = nomE;
        PrenomE = prenomE;
        Grade = grade;
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
}
