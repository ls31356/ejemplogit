package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Nassignatura1Activity extends BaseActivity {
    private EditText nomNouAssig, descNouAssig;
    private Button pas2;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nassignatura1);
        resId = R.string.nouassig;
        pas = new String("1/3");

        nomNouAssig = (EditText) findViewById(R.id.nomNouAssig);
        descNouAssig = (EditText) findViewById(R.id.descNouAssig);
        pas2 = (Button) findViewById(R.id.pas2);

        pas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("nouAssig", MODE_PRIVATE);
                prefEditor = preferences.edit();
                if(nomNouAssig.getText().toString().length() == 0 || descNouAssig.getText().toString().length() == 0) Toast.makeText(getApplicationContext(), R.string.errorassig, Toast.LENGTH_SHORT).show();
                else if(nomNouAssig.getText().toString().contains("-") || descNouAssig.getText().toString().contains("-")) Toast.makeText(getApplicationContext(), R.string.errorassig2, Toast.LENGTH_SHORT).show();
                else {
                    prefEditor.putString("nouAssig", nomNouAssig.getText().toString() + "-" + descNouAssig.getText().toString() + "/");
                    prefEditor.commit();

                    Intent i = new Intent(getApplicationContext(), Nassignatura2Activity.class);
                    startActivity(i);

                }
            }
        });

        nomNouAssig.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    descNouAssig.requestFocus();
                }
                return false;
            }
        });
    }
}