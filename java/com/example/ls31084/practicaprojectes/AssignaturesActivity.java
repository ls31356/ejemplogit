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

public class AssignaturesActivity extends BaseActivity {
    public ListView listView;
    private AssignaturaAdapter assignaturaAdapter;
    private List<Assignatura> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignatures);
        resId = R.string.gestioassig;
        inflate = true;

        list = new ArrayList<Assignatura>();
        listView = (ListView) findViewById(R.id.list);
        assignaturaAdapter = new AssignaturaAdapter(this, list);

        listView.setAdapter(assignaturaAdapter);

    }
}