package com.example.anes_.logmvvm.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.EnseignantCreate;
import com.example.anes_.logmvvm.commands.UserLogin;
import com.example.anes_.logmvvm.databinding.CreateEnseignantBinding;
import com.example.anes_.logmvvm.model.Enseignant;
import com.example.anes_.logmvvm.viewmodel.CoursModel;
import com.example.anes_.logmvvm.viewmodel.EnseignantModel;
import com.example.anes_.logmvvm.viewmodel.SalleModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class CreateEnseignant extends Activity
{
    public DatabaseHelper dbh;
    private Spinner Cours ;
    private Spinner Salle ;
    private CreateEnseignantBinding createEnseignantBinding;
    private EnseignantModel enseignantmodel = new EnseignantModel();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        dbh =  new DatabaseHelper(this);
        createEnseignantBinding= DataBindingUtil.setContentView(this, R.layout.create_enseignant);

        Cours = (Spinner) findViewById(R.id.Cours);
        Enseignant enseignant = new Enseignant();
        //EnseignantModel enseignantmodel = new EnseignantModel();
        //String a = createEnseignantBinding.NomEnseignant.getText().toString();
        createEnseignantBinding.setCreateEnseignant(enseignantmodel);
        addItemsOnCours();
        addItemsOnSalle();

        createEnseignantBinding.setCreateEnseignantEvent(new EnseignantCreate() {
            @Override
            public void onClickCreate() {
                if(createEnseignantBinding.NumEnseignant.getText().toString().length() != 8){

                    Toasty.error(getApplicationContext(),"Le numero Enseignant doit contenir 8 caractère",Toast.LENGTH_SHORT).show();

                }
                if(createEnseignantBinding.NomEnseignant.getText().toString().isEmpty()){
                    Toasty.error(getApplicationContext(),"Le Nom de l'Enseignant est vide",Toast.LENGTH_SHORT).show();
                }
                else if (!createEnseignantBinding.NomEnseignant.getText().toString().matches("[a-zA-Z]+")){
                    Toasty.error(getApplicationContext(),"Le Nom de l'enseignant ne doit pas contenir des chiffres",Toast.LENGTH_SHORT).show();
                }
                if(createEnseignantBinding.PrenomEnseignant.getText().toString().isEmpty()){
                    Toasty.error(getApplicationContext(),"Le Prenom de l'enseignant est vide",Toast.LENGTH_SHORT).show();
                }
                else if (!createEnseignantBinding.PrenomEnseignant.getText().toString().matches("[a-zA-Z]+")){
                    Toasty.error(getApplicationContext(),"Le Prenom de l'énseignant ne doit pas contenir des chiffres",Toast.LENGTH_SHORT).show();
                }
                else{
                createEnseignantBinding.getCreateEnseignant().insertEnseignant(dbh,createEnseignantBinding.NumEnseignant.getText().toString(),createEnseignantBinding.NomEnseignant.getText().toString(),createEnseignantBinding.PrenomEnseignant.getText().toString(),createEnseignantBinding.GradeEnseignant.getText().toString(),String.valueOf(Cours.getSelectedItem()),String.valueOf(Salle.getSelectedItem()));
                Toasty.success(getApplicationContext(),"Enseignant Ajouté avec succès",Toast.LENGTH_SHORT).show();}

            }

            @Override
            public void onClickPrint() {

            }

            @Override
            public void onClickDelete() {

            }

            @Override
            public void onClickUpdate() {

            }
        });



    }




    public void addItemsOnCours() {
        CoursModel courmodel = new CoursModel();
        Cours = (Spinner) findViewById(R.id.Cours);
        Cursor cr = courmodel.FindAll(dbh);
        ArrayList<String> list = new ArrayList<String>();
        while(cr.moveToNext()){list.add(cr.getString(0));}
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cours.setAdapter(dataAdapter);
    }

    public void addItemsOnSalle() {
        SalleModel sallemodel = new SalleModel();
        Salle = (Spinner) findViewById(R.id.Salle);
        Cursor cr = sallemodel.FindAll(dbh);
        ArrayList<String> list = new ArrayList<String>();
        while(cr.moveToNext()){list.add(cr.getString(0));}
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Salle.setAdapter(dataAdapter);
    }




}
