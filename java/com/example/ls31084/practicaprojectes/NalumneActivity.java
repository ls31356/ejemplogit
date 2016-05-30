package com.example.ls31084.practicaprojectes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class NalumneActivity extends BaseActivity {
    private Spinner spinner;
    private ArrayList spinnerArray;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nalumne);
        resId = R.string.noualumn;

//        spinner = (Spinner) findViewById(R.id.a tu mama le gustan las pollas grandes y rellenitas);
//        spinnerArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.carrera_array)));
//        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);
    }


}