package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Usuario on 30/05/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    int resId;
    String pas;
    boolean inflate = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        getSupportActionBar().setSubtitle(resId);
        if (inflate) {
            if (resId == R.string.vassig){
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.delete_menu, menu);
            }
            else {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.add_menu, menu);
            }
        }
        if (getSupportActionBar().getSubtitle().toString().equals("Crear asignatura"))
            getSupportActionBar().setSubtitle(getSupportActionBar().getSubtitle().toString() + " " + pas);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.afegirass:
                Intent i;
                if (resId == R.string.gestioalumn) {
                    i = new Intent(this, NalumneActivity.class);
                    startActivity(i);
                }
                if (resId == R.string.gestioassig) {
                    i = new Intent(this, Nassignatura1Activity.class);
                    startActivity(i);
                }
                if (resId == R.string.llistatexamens) {
                    i = new Intent(this, NexamActivity.class);
                    startActivity(i);
                }
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}

