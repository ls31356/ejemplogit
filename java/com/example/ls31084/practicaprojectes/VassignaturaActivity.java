package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VassignaturaActivity extends AppCompatActivity {
    private TextView nomAssig, descAssig;
    private Bundle info;
    private String infoStr, nom, desc, temaritxt, s3;
    private TextView temari;
    private String[] parts, temes, s1, s2;
    private SharedPreferences snom, sp;
    private SharedPreferences.Editor spe;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        getSupportActionBar().setSubtitle(R.string.vassig);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteass:
                sp = getSharedPreferences("AssignaturaList", MODE_PRIVATE);
                spe = sp.edit();
                s1 = sp.getString("myList", "").split("/");
                int j = 0;
                s2 = new String[s1.length];
                for (int i = 0; i < s1.length; i++){
                    if (!s1[i].equals(infoStr)){
                        s2[j] = s1[i];
                        j++;
                    }
                }
                s3 = new String();
                for (j = 0; j < s2.length -1; j++) {
                    s3 = s3 + s2[j] + "/";
                }
                spe.putString("myList", s3);
                spe.commit();
                sp = getSharedPreferences("ExamList", MODE_PRIVATE);
                spe = sp.edit();
                s2 = infoStr.split("-");
                spe.putString(s2[0], "");
                spe.commit();
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("borrada", true);
                startActivity(i);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vassignatura);

        nomAssig = (TextView) findViewById(R.id.nomAssig);
        descAssig = (TextView) findViewById(R.id.descAssig);
        temari = (TextView) findViewById(R.id.temarivassig);

        info = getIntent().getExtras();
        infoStr = info.getString("nomdesc");

        parts = infoStr.split("-");

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