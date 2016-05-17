package com.example.ls31084.practicaprojectes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AssignaturesActivity extends AppCompatActivity {
    public ListView listView;
    private AssignaturaAdapter assignaturaAdapter;
    private List<Assignatura> list;
    private EditText addNom;
    private EditText addDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignatures);

        list = new ArrayList<Assignatura>();
        listView = (ListView) findViewById(R.id.list);
        assignaturaAdapter = new AssignaturaAdapter(this, list);



        /*addNom = (EditText) findViewById(R.id.nom_add);
        addDesc = (EditText) findViewById(R.id.edat_add);

        Button btn = (Button) findViewById(R.id.add_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assignatura assignatura = new Assignatura(addNom.getText().toString(), addDesc.getText().toString(), 0);
                list.add(assignatura);

            }
        });
        */

        listView.setAdapter(assignaturaAdapter);

    }
}