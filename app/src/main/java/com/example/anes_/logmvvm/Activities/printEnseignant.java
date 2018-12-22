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
import com.example.anes_.logmvvm.databinding.PrintEnseignantBinding;
import com.example.anes_.logmvvm.model.Enseignant;

import com.example.anes_.logmvvm.viewmodel.EnseignantModel;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class printEnseignant extends Activity
{
    public DatabaseHelper dbh;
    private Spinner Enseignants ;
    private PrintEnseignantBinding printEnseignantBinding;
    private EnseignantModel enseignantmodel = new EnseignantModel();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        dbh =  new DatabaseHelper(this);
        printEnseignantBinding = DataBindingUtil.setContentView(this, R.layout.print_enseignant);

        Enseignants = (Spinner) findViewById(R.id.Enseignant);
        Enseignant enseignant = new Enseignant();
        //EnseignantModel enseignantmodel = new EnseignantModel();
        //String a = createEnseignantBinding.NomEnseignant.getText().toString();
        printEnseignantBinding.setPrintEnseignant(enseignantmodel) ;
        addItemsOnEnseignants();


        printEnseignantBinding.setPrintEnseignantEvent(new EnseignantCreate() {
            @Override
            public void onClickCreate() {

                //createEnseignantBinding.getCreateEnseignant().insertEnseignant(dbh,createEnseignantBinding.NumEnseignant.getText().toString(),createEnseignantBinding.NomEnseignant.getText().toString(),createEnseignantBinding.PrenomEnseignant.getText().toString(),createEnseignantBinding.GradeEnseignant.getText().toString(),String.valueOf(Cours.getSelectedItem()),String.valueOf(Salle.getSelectedItem()));
            }

            @Override
            public void onClickPrint() {

                Cursor cr = printEnseignantBinding.getPrintEnseignant().printEnseignant(dbh,String.valueOf(Enseignants.getSelectedItem()));

                StringBuffer buffer = new StringBuffer();
                while (cr.moveToNext()) {
                    buffer.append("N°Enseignant :"+ cr.getString(0)+"\n");
                    buffer.append("Nom :"+ cr.getString(1)+"\n");
                    buffer.append("Prenom :"+ cr.getString(2)+"\n");
                    buffer.append("Grade :"+ cr.getString(3)+"\n");
                    buffer.append("Cour :"+ cr.getString(4)+"\n");
                    buffer.append("Salle :"+ cr.getString(5)+"\n");
                }
                showMessage("Data",buffer.toString());
            }

            @Override
            public void onClickDelete() {
                printEnseignantBinding.getPrintEnseignant().deleteEnseignant(dbh,String.valueOf(Enseignants.getSelectedItem()));
                Toasty.success(getApplicationContext(),"Enseignant Supprimé avec succès",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(printEnseignant.this,printEnseignant.class);
                startActivity(intent);
            }

            @Override
            public void onClickUpdate() {
                printEnseignantBinding.getPrintEnseignant().updateEnseignant(dbh,String.valueOf(Enseignants.getSelectedItem()),printEnseignantBinding.NomEnseignant.getText().toString(),printEnseignantBinding.PrenomEnseignant.getText().toString(),printEnseignantBinding.GradeEnseignant.getText().toString());
                Toasty.success(getApplicationContext(),"Enseignant Modifié avec succès",Toast.LENGTH_SHORT).show();

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

    public void addItemsOnEnseignants() {
        EnseignantModel enseignantModel = new EnseignantModel();
        Enseignants = (Spinner) findViewById(R.id.Enseignant);
        Cursor cr = enseignantModel.FindAll(dbh);
        ArrayList<String> list = new ArrayList<String>();
        while(cr.moveToNext()){list.add(cr.getString(0));}
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Enseignants.setAdapter(dataAdapter);
    }




}
