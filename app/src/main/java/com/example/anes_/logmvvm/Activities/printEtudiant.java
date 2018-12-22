package com.example.anes_.logmvvm.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.EnseignantCreate;
import com.example.anes_.logmvvm.commands.EtudiantCreate;
import com.example.anes_.logmvvm.databinding.PrintEnseignantBinding;
import com.example.anes_.logmvvm.databinding.PrintEtudiantBinding;
import com.example.anes_.logmvvm.model.Enseignant;
import com.example.anes_.logmvvm.model.Etudiant;
import com.example.anes_.logmvvm.viewmodel.EnseignantModel;
import com.example.anes_.logmvvm.viewmodel.EtudiantModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class printEtudiant extends Activity
{
    public DatabaseHelper dbh;
    private Spinner Etudiants ;
    private PrintEtudiantBinding printEtudiantBinding ;
    private EtudiantModel etudiantModel = new EtudiantModel();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        dbh =  new DatabaseHelper(this);

        printEtudiantBinding= DataBindingUtil.setContentView(this, R.layout.print_etudiant);

        Etudiants = (Spinner) findViewById(R.id.Etudiant);
        Etudiant etudiant = new Etudiant();
        //EnseignantModel enseignantmodel = new EnseignantModel();
        //String a = createEnseignantBinding.NomEnseignant.getText().toString();
        printEtudiantBinding.setPrintEtudiant(etudiantModel);
        addItemsOnEtudiants();


        printEtudiantBinding.setPrintEtudiantEvent(new EtudiantCreate() {
            @Override
            public void onClickCreate() {

            }

            @Override
            public void onClickNoteCreate() {

            }

            @Override
            public void onClickPrint() {
                Cursor cr = printEtudiantBinding.getPrintEtudiant().printEtudiant(dbh,String.valueOf(Etudiants.getSelectedItem()));


                StringBuffer buffer = new StringBuffer();
                while (cr.moveToNext()) {
                    buffer.append("N°Etudiant :"+ cr.getString(0)+"\n");
                    buffer.append("Nom :"+ cr.getString(1)+"\n");
                    buffer.append("Prenom :"+ cr.getString(2)+"\n");

                }
                showMessage("Data",buffer.toString());
            }

            @Override
            public void onClickDelete() {
                printEtudiantBinding.getPrintEtudiant().deleteEtudiant(dbh,String.valueOf(Etudiants.getSelectedItem()));
                Toasty.success(getApplicationContext(),"Etudiant Supprimé avec succès",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(printEtudiant.this,printEtudiant.class);
                startActivity(intent);
            }

            @Override
            public void onClickUpdate() {
                printEtudiantBinding.getPrintEtudiant().updateEtudiant(dbh,String.valueOf(Etudiants.getSelectedItem()),printEtudiantBinding.NomEtudiant.getText().toString(),printEtudiantBinding.PrenomEtudiant.getText().toString());
                Toasty.success(getApplicationContext(),"Etudiant Modifié avec succès",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickPrintNote() {
                Cursor cr = printEtudiantBinding.getPrintEtudiant().printNoteEtudiant(dbh,String.valueOf(Etudiants.getSelectedItem()));

                StringBuffer buffer = new StringBuffer();
                while (cr.moveToNext()) {

                    buffer.append("Cours :"+ cr.getString(1)+"\n");
                    buffer.append("Note :"+ cr.getString(2)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
        });




    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void addItemsOnEtudiants() {
        EtudiantModel etudiantModel = new EtudiantModel();
        Etudiants = (Spinner) findViewById(R.id.Etudiant);
        Cursor cr = etudiantModel.FindAll(dbh);
        ArrayList<String> list = new ArrayList<String>();
        while(cr.moveToNext()){list.add(cr.getString(0));}
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Etudiants.setAdapter(dataAdapter);
    }
}
