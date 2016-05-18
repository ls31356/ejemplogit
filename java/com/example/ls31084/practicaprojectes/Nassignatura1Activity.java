package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Nassignatura1Activity extends AppCompatActivity {
    private EditText nomNouAssig, descNouAssig;
    private Button pas2;
    private String saveAssig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nassignatura1);

        nomNouAssig = (EditText) findViewById(R.id.nomNouAssig);
        descNouAssig = (EditText) findViewById(R.id.descNouAssig);
        pas2 = (Button) findViewById(R.id.pas2);

        saveAssig = nomNouAssig.getText().toString()+"-"+descNouAssig.getText().toString();

        pas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Nassignatura2Activity.class);
                i.putExtra("nouAssig", saveAssig);
                startActivity(i);
            }
        });



    }


}