package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Nassignatura2Activity extends BaseActivity {
    private Button anterior, seguent;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nassignatura1);
        resId = R.string.nouassig;
        pas = new String("2/3");

        anterior = (Button) findViewById(R.id.backpas1);
        seguent = (Button) findViewById(R.id.pas3);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("nouAssig", MODE_PRIVATE);
                prefEditor = preferences.edit();
                prefEditor.clear();
                prefEditor.commit();
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