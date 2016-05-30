package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AssignaturesActivity extends AppCompatActivity {
    public ListView listView;
    private AssignaturaAdapter assignaturaAdapter;
    private List<Assignatura> list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        getSupportActionBar().setSubtitle(R.string.gestioassig);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.afegirass:
                Intent i = new Intent(this, Nassignatura1Activity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignatures);

        list = new ArrayList<Assignatura>();
        listView = (ListView) findViewById(R.id.list);
        assignaturaAdapter = new AssignaturaAdapter(this, list);

        listView.setAdapter(assignaturaAdapter);

    }
}