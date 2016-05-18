package com.example.ls31084.practicaprojectes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
    private Singleton singleton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        singleton = Singleton.getInstance();
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.logo_icon);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        TextView nom = new TextView(this);
        nom.setText(singleton.getNomActionbar());
        nom.setPadding(5, 0, 5, 0);
        nom.setTextSize(10);

        menu.add(0, R.menu.menu, 1, singleton.getNomActionbar()).setActionView(nom).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
}