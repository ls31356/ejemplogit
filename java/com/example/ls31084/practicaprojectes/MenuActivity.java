package com.example.ls31084.practicaprojectes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button assignatures, alumnes, examens, sortir;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPreferencesEditor;
    private boolean finish = false;
    private String saveAssig, list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_close:
                finish();
                System.exit(0);
                return true;
            case R.id.action_alumn:
                Intent i = new Intent(this, NalumneActivity.class);
                startActivity(i);
                return true;
            case R.id.action_assig:
                Intent i2 = new Intent(this, Nassignatura1Activity.class);
                startActivity(i2);
                return true;
            case R.id.action_exam:
                Intent i3 = new Intent(this, NexamActivity.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        assignatures = (Button) findViewById(R.id.assignatures);
        alumnes = (Button) findViewById(R.id.alumnat);
        examens = (Button) findViewById(R.id.examens);
        sortir = (Button) findViewById(R.id.sortir);

        assignatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AssignaturesActivity.class);
                changeActivity(i, finish);
            }
        });

        alumnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AlumnesActivity.class);
                changeActivity(i, finish);
            }
        });

        examens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ExamensActivity.class);
                changeActivity(i, finish);
            }
        });

        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                resetPreferences();
                finish = true;
                changeActivity(i, finish);
            }
        });

        if (getIntent().getBooleanExtra("creada", false)){
            Intent i = new Intent(getApplicationContext(), AssignaturesActivity.class);
            loginPreferences = getSharedPreferences("nouAssig", MODE_PRIVATE);
            loginPreferencesEditor = loginPreferences.edit();
            saveAssig = loginPreferences.getString("nouAssig", "");
            loginPreferencesEditor.clear();
            loginPreferencesEditor.commit();

            loginPreferences = getSharedPreferences("AssignaturaList", MODE_PRIVATE);
            loginPreferencesEditor = loginPreferences.edit();

            list = loginPreferences.getString("myList", "");
            loginPreferencesEditor.clear();
            loginPreferencesEditor.putString("myList", list+saveAssig);
            loginPreferencesEditor.commit();

            startActivity(i);
        }
        if (getIntent().getBooleanExtra("borrada", false)) {
            Intent i = new Intent(this, AssignaturesActivity.class);
            startActivity(i);
        }
    }

    public void resetPreferences(){
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPreferencesEditor = loginPreferences.edit();
        loginPreferencesEditor.clear();
        loginPreferencesEditor.commit();
    }

    public void changeActivity(Intent i, boolean finish){
        startActivity(i);
        if (finish)    finish();
    }
}