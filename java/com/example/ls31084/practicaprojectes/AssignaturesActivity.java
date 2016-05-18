package com.example.ls31084.practicaprojectes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AssignaturesActivity extends AppCompatActivity {
    public ListView listView;
    private AssignaturaAdapter assignaturaAdapter;
    private List<Assignatura> list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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