package com.example.anes_.logmvvm.viewmodel;

public class NoteModel
{
    private String NumE;
    private String NumCour;
    private String note ;

    public NoteModel() {
    }

    public NoteModel(String numE, String numCour , String note) {
        NumE = numE;
        NumCour = numCour;
        this.note = note;
    }

    public String getNumE() {
        return NumE;
    }

    public void setNumE(String numE) {
        NumE = numE;
    }

    public String getNumCour() {
        return NumCour;
    }

    public void setNumCour(String numCour) {
        NumCour = numCour;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
