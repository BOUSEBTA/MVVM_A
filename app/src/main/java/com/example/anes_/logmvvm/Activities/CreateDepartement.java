package com.example.anes_.logmvvm.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.DepartementCreate;
import com.example.anes_.logmvvm.databinding.CreateDepartementBinding;
import com.example.anes_.logmvvm.model.Departement;
import com.example.anes_.logmvvm.viewmodel.DepartementModel;

import es.dmoral.toasty.Toasty;

public class CreateDepartement extends AppCompatActivity
{
    public DatabaseHelper dbh;

    private CreateDepartementBinding createDepartementBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        dbh =  new DatabaseHelper(this);
        createDepartementBinding= DataBindingUtil.setContentView(this, R.layout.create_departement);
        Departement departement= new Departement();
        DepartementModel departementModel= new DepartementModel();
        //String a = createEnseignantBinding.NomEnseignant.getText().toString();
        createDepartementBinding.setCreateDepartement(departementModel);
        createDepartementBinding.setCreateDepartementEvent(new DepartementCreate() {
            @Override
            public void onClickCreate() {
                if(!createDepartementBinding.CodeDepartement.getText().toString().matches("[D][0-9]{3}")){
                    Toasty.error(getApplicationContext(),"Le Numero de departement doit commencer par la lettre D suivi de 3 entiers",Toast.LENGTH_SHORT).show();
                }
                else{
                createDepartementBinding.getCreateDepartement().insertDepartement(dbh,createDepartementBinding.CodeDepartement.getText().toString(),createDepartementBinding.NomDepartement.getText().toString());
                    Toasty.success(getApplicationContext(),"Departement  Ajouté avec succès",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
