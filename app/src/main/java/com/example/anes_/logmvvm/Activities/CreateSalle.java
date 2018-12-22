package com.example.anes_.logmvvm.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.SalleCreate;
import com.example.anes_.logmvvm.databinding.CreateSalleBinding;
import com.example.anes_.logmvvm.model.Salle;
import com.example.anes_.logmvvm.viewmodel.SalleModel;

import es.dmoral.toasty.Toasty;

public class CreateSalle extends AppCompatActivity
{
    public DatabaseHelper dbh;

    private CreateSalleBinding createSalleBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        dbh =  new DatabaseHelper(this);
        createSalleBinding = DataBindingUtil.setContentView(this, R.layout.create_salle);
        Salle salle = new Salle();
        SalleModel salleModel = new SalleModel();
        createSalleBinding.setCreateSalle(salleModel);
        createSalleBinding.setCreateSalleEvent(new SalleCreate() {
            @Override
            public void onClickCreate() {
                if(!createSalleBinding.NumDeSalle.getText().toString().matches("[1-3][0][9]")){
                    Toasty.error(getApplicationContext(),"Le Numero de salle doit commencer un entier allant de 1 a 3 suivi d'un entier compris entre 01 et 09",Toast.LENGTH_SHORT).show();
                }
                else{
                createSalleBinding.getCreateSalle().insertSalle(dbh,createSalleBinding.NumDeSalle.getText().toString(),createSalleBinding.CapaciteSalle.getText().toString());
                    Toasty.success(getApplicationContext(),"Salle Ajouté avec succès",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
