package com.example.ls31084.practicaprojectes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Nassignatura3Activity extends AppCompatActivity {
    private Button anterior, seguent, insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nassignatura3);

        anterior = (Button) findViewById(R.id.crea_assig);
        seguent = (Button) findViewById(R.id.backpas2);
        insertar = (Button) findViewById(R.id.insertar);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }


}