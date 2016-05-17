package com.example.ls31084.practicaprojectes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class VassignaturaActivity extends AppCompatActivity {
    private TextView nomAssig, descAssig;
    private Bundle info;
    private String infoStr, nom, desc;
    private String[] parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vassignatura);

        nomAssig = (TextView) findViewById(R.id.nomAssig);
        descAssig = (TextView) findViewById(R.id.descAssig);

        info = getIntent().getExtras();
        infoStr = info.getString("nomdesc");

        parts = infoStr.split("/");

        nom = parts[0];
        desc = parts[1];

        nomAssig.setText(nom);
        descAssig.setText(desc);

    }


}