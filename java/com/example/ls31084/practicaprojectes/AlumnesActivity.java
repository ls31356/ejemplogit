package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//Parte de Edu

public class AlumnesActivity extends AppCompatActivity {

    public ListView listView;
    private AlumnesAdapter alumnesAdapter;
    private List<Alumne> list;
   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        getSupportActionBar().setSubtitle(R.string.gestioassig);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.alumnes, menu);
        return super.onCreateOptionsMenu(menu);
    }/*

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.afegirass:
                Intent i = new Intent(this, Nassignatura1Activity.class);
                startActivity(i);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnes);
        list = new ArrayList<Alumne>();
        listView = (ListView) findViewById(R.id.lista_alumnos);
        alumnesAdapter = new AlumnesAdapter(this, list);

        listView.setAdapter(alumnesAdapter);
    }


}