package com.example.anes_.logmvvm.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.EtudiantCreate;
import com.example.anes_.logmvvm.databinding.CreateEtudiantBinding;
import com.example.anes_.logmvvm.model.Etudiant;
import com.example.anes_.logmvvm.viewmodel.CoursModel;
import com.example.anes_.logmvvm.viewmodel.EtudiantModel;
import com.example.anes_.logmvvm.viewmodel.NoteModel;

import java.util.ArrayList;
import java.util.Iterator;

import es.dmoral.toasty.Toasty;

public class CreateEtudiant extends Activity
{
    public DatabaseHelper dbh;
    private Spinner Cours ;
    private CreateEtudiantBinding createEtudiantBinding;
    EtudiantModel etudiantModel = new EtudiantModel();
    ArrayList<NoteModel> Notes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbh =  new DatabaseHelper(this);
        createEtudiantBinding= DataBindingUtil.setContentView(this, R.layout.create_etudiant);

        Etudiant etudiant = new Etudiant();

        NoteModel noteModel = new NoteModel();

        createEtudiantBinding.setCreateNoteEtudiant(noteModel);
        createEtudiantBinding.setCreateEtudiant(etudiantModel);

        addItemsOnCours();


        createEtudiantBinding.setCreateEtudiantEvent(new EtudiantCreate() {
            @Override
            public void onClickCreate() {
                if(createEtudiantBinding.NumEtudiant.getText().toString().length() != 8){

                    Toasty.error(getApplicationContext(),"Le numero etudiant doit contenir 8 caractère",Toast.LENGTH_SHORT).show();

                }
                if(createEtudiantBinding.NomEtudiant.getText().toString().isEmpty()){
                    Toasty.error(getApplicationContext(),"Le Nom de l'étudiant est vide",Toast.LENGTH_SHORT).show();
                }
                else if (!createEtudiantBinding.NomEtudiant.getText().toString().matches("[a-zA-Z]+")){
                    Toasty.error(getApplicationContext(),"Le Nom de l'étudiant ne doit pas contenir des chiffres",Toast.LENGTH_SHORT).show();
                }
                if(createEtudiantBinding.PrenomEtudiant.getText().toString().isEmpty()){
                    Toasty.error(getApplicationContext(),"Le Prenom de l'étudiant est vide",Toast.LENGTH_SHORT).show();
                }
                else if (!createEtudiantBinding.PrenomEtudiant.getText().toString().matches("[a-zA-Z]+")){
                    Toasty.error(getApplicationContext(),"Le Prenom de l'étudiant ne doit pas contenir des chiffres",Toast.LENGTH_SHORT).show();
                }
                else{
                createEtudiantBinding.getCreateEtudiant().insertEtudiant(dbh,createEtudiantBinding.NumEtudiant.getText().toString(),createEtudiantBinding.NomEtudiant.getText().toString(),createEtudiantBinding.PrenomEtudiant.getText().toString());
                Toasty.success(getApplicationContext(),"Etudiant Ajouté avec succès",Toast.LENGTH_SHORT).show();}
            }

            @Override
            public void onClickNoteCreate() {
                createEtudiantBinding.getCreateEtudiant().insertNote(dbh,createEtudiantBinding.NumEtudiant.getText().toString(),String.valueOf(Cours.getSelectedItem()),createEtudiantBinding.NoteEtudiant.getText().toString());

                NoteModel etudiant = new NoteModel();
                etudiant.setNumCour(String.valueOf(Cours.getSelectedItem()));
                etudiant.setNumE(createEtudiantBinding.NumEtudiant.getText().toString());
                etudiant.setNote(createEtudiantBinding.NoteEtudiant.getText().toString());
                Notes.add(etudiant);
                addData();
                int i;
                for(i=0;i<Notes.size();i++){
                    Log.d("A==================A", Notes.get(i).getNumE());
                }
                Log.d("a", "--------Fin----- ");
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

            @Override
            public void onClickPrintNote() {

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

    public void addData(){
        TableLayout tl=(TableLayout)findViewById(R.id.NOTES_ETUDIANT);

            TableRow tr1 = new TableRow(this);
            tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
            TextView textview = new TextView(this);
            textview.setPadding(50, 00, 50, 50);
            textview.setText(Notes.get(Notes.size()-1).getNumE());
            textview.setTextColor(Color.BLACK);

            TextView textview1 = new TextView(this);
            textview1.setPadding(50, 0, 50, 50);
            textview1.setText(Notes.get(Notes.size()-1).getNumCour());
            textview1.setTextColor(Color.BLACK);

            TextView textview2 = new TextView(this);
            textview2.setPadding(50, 0, 50, 50);
            textview2.setText(Notes.get(Notes.size()-1).getNote());
            textview2.setTextColor(Color.BLACK);

            tr1.addView(textview);
            tr1.addView(textview1);
            tr1.addView(textview2);
            tl.addView(tr1);



    }
}
