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

public class AlumnesActivity extends BaseActivity {

    public ListView listView;
    private AlumnesAdapter alumnesAdapter;
    private List<Alumne> list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnes);
        resId = R.string.gestioalumn;
        inflate = true;

        list = new ArrayList<Alumne>();
        listView = (ListView) findViewById(R.id.lista_alumnos);
        alumnesAdapter = new AlumnesAdapter(this, list);

        listView.setAdapter(alumnesAdapter);
    }


}