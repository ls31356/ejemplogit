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

public class ExamensActivity extends BaseActivity {
    public ListView listView;
    private ExamenAdapter examenAdapter;
    private List<Examen> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examens);
        resId = R.string.llistatexamens;
        inflate = true;

        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.examlist);
        examenAdapter = new ExamenAdapter(this, list);

        listView.setAdapter(examenAdapter);

    }
}