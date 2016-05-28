package com.example.ls31084.practicaprojectes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Nassignatura3Activity extends AppCompatActivity {
    private Button anterior, seguent, insertar;
    private SharedPreferences stema, snom;
    private SharedPreferences.Editor setema;
    private EditText nom;
    private String getList;
    private String[] nomShared;
    private ListView listView;
    private TemaAdapter temaAdapter;
    private List<String> list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        getSupportActionBar().setSubtitle(R.string.nouassig);
        getSupportActionBar().setSubtitle(getSupportActionBar().getSubtitle().toString() +"  3/3");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nassignatura3);

        anterior = (Button) findViewById(R.id.backpas2);
        seguent = (Button) findViewById(R.id.crea_assig);
        insertar = (Button) findViewById(R.id.insertar);
        nom = (EditText) findViewById(R.id.nomtema);

        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.temari);
        temaAdapter = new TemaAdapter(this, list);

        listView.setAdapter(temaAdapter);

        snom = getApplicationContext().getSharedPreferences("nouAssig", Context.MODE_PRIVATE);
        nomShared = snom.getString("nouAssig", "").split("-");
        stema = getApplicationContext().getSharedPreferences("Temari", Context.MODE_PRIVATE);

        setema = stema.edit();



        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("creada", true);
                startActivity(i);
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList = stema.getString(nomShared[0], "") + nom.getText().toString() + "-";
                setema.putString(nomShared[0], getList);
                setema.commit();

                temaAdapter.insereixTema(nom.getText().toString());
            }
        });

    }


}