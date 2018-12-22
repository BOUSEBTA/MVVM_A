package com.example.anes_.logmvvm.model;

public class Salle
{
    private String NumSalle;

    private String Capacite;

    public Salle() {
    }

    public Salle(String numSalle, String capacite) {
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
}
