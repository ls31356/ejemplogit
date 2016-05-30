package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Usuario on 30/05/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    int resId;
    String pas;

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
        if () {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.add_menu, menu);
        }
        if (getSupportActionBar().getSubtitle().toString().equals("Crear asignatura")) getSupportActionBar().setSubtitle(getSupportActionBar().getSubtitle().toString() + " " + pas);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();

        } else if (itemId == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));

        } else if (itemId == R.id.menu_search) {
            onSearchRequested();

        }

        return super.onOptionsItemSelected(item);
    }

}

