package com.example.anes_.logmvvm.model;

public class Departement
{
    private String CodeD;
    private String NomD;

    public Departement() {
    }

    public Departement(String codeD, String nomD) {
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
}
