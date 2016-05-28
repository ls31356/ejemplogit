package com.example.ls31084.practicaprojectes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VassignaturaActivity extends AppCompatActivity {
    private TextView nomAssig, descAssig;
    private Bundle info;
    private String infoStr, nom, desc, temaritxt;
    private TextView temari;
    private String[] parts, temes;
    private SharedPreferences snom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vassignatura);

        nomAssig = (TextView) findViewById(R.id.nomAssig);
        descAssig = (TextView) findViewById(R.id.descAssig);
        temari = (TextView) findViewById(R.id.temarivassig);

        info = getIntent().getExtras();
        infoStr = info.getString("nomdesc");

        parts = infoStr.split("/");

        nom = parts[0];
        desc = parts[1];

        nomAssig.setText(nom);
        descAssig.setText(desc);

        snom = getSharedPreferences("Temari", MODE_PRIVATE);

        temes = snom.getString(nom, "").split("-");

        temaritxt = new String();

        for(int i = 0; i < temes.length; i++){
            int j = i + 1;
            temaritxt = temaritxt + j + ". "+ temes[i] + "\n";
        }

        temari.setText(temaritxt);
    }


}