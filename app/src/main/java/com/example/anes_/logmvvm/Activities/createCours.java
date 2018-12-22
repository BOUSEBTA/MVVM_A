package com.example.anes_.logmvvm.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.CoursCreate;
import com.example.anes_.logmvvm.commands.DepartementCreate;
import com.example.anes_.logmvvm.databinding.CreateCoursBinding;
import com.example.anes_.logmvvm.model.Cours;
import com.example.anes_.logmvvm.viewmodel.CoursModel;

import es.dmoral.toasty.Toasty;

public class createCours extends AppCompatActivity
{
    public DatabaseHelper dbh;

    private CreateCoursBinding createCoursBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        dbh =  new DatabaseHelper(this);
        createCoursBinding=DataBindingUtil.setContentView(this, R.layout.create_cours);
        Cours cours = new Cours();
        CoursModel coursModel = new CoursModel();
        createCoursBinding.setCreateCours(coursModel);
        createCoursBinding.setCreateCoursEvent(new CoursCreate() {
            @Override
            public void onClickCreate() {
                if(!createCoursBinding.NumDeCour.getText().toString().matches("[C][0-9]{3}")){
                    Toasty.error(getApplicationContext(),"Le Numero du cour doit commencer par la lettre C suivi de 3 entiers",Toast.LENGTH_SHORT).show();
                }
                else {
                    createCoursBinding.getCreateCours().insertCours(dbh, createCoursBinding.NumDeCour.getText().toString(), createCoursBinding.LibelleCours.getText().toString());
                    Toasty.success(getApplicationContext(),"Cours Ajouté avec succès",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
