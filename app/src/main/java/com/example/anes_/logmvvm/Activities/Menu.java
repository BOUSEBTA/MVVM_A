package com.example.anes_.logmvvm.Activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.databinding.MenuBinding;


public class Menu extends AppCompatActivity
{
    Intent intent;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.menu1);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        nav = (NavigationView) findViewById(R.id.nav);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.P_Enseignant_PRINT :
                         intent = new Intent(Menu.this,printEnseignant.class);
                        startActivity(intent);break;
                    case R.id.P_Enseignant_CREATE :
                        intent = new Intent(Menu.this,CreateEnseignant.class);
                        startActivity(intent);break;
                    case R.id.P_Etudiant_CREATE :
                        intent = new Intent(Menu.this,CreateEtudiant.class);
                        startActivity(intent);break;
                    case R.id.P_Etudiant_PRINT:
                        intent = new Intent(Menu.this,printEtudiant.class);
                        startActivity(intent);break;
                    case R.id.P_Cour_CREATE:
                        intent = new Intent(Menu.this,createCours.class);
                        startActivity(intent);break;
                    case R.id.P_Salle_CREATE:
                        intent = new Intent(Menu.this,CreateSalle.class);
                        startActivity(intent);break;
                    case R.id.P_Departement_CREATE:
                        intent = new Intent(Menu.this,CreateDepartement.class);
                        startActivity(intent);break;

                }
                    return false;
             }
        });
    }


    /*public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.drawermenu, (android.view.Menu) menu);
        return true;
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
