package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Nassignatura2Activity extends AppCompatActivity {
    private Button anterior, seguent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nassignatura2);

        anterior = (Button) findViewById(R.id.backpas1);
        seguent = (Button) findViewById(R.id.pas3);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Nassignatura3Activity.class);
                startActivity(i);
            }
        });
    }


}