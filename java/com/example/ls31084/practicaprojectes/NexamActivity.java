package com.example.ls31084.practicaprojectes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NexamActivity extends BaseActivity {
    private Spinner carrera, assignatura, aula;
    private EditText data, hora;
    private Button datebtn, timebtn, guardar;
    private int year, month, day, hour, minute;
    private Calendar calendar;
    private ArrayAdapter<String> carreraAdapter, aulaAdapter, assignaturaAdapter;
    private ArrayList<String> carreraList, aulaList;
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private String[] s1, s2, s3, temaedit, e1;
    private StringBuilder saveExamens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexam);
        resId = R.string.llistatexamens;
        inflate = true;

        carrera = (Spinner) findViewById(R.id.carrera);
        assignatura = (Spinner) findViewById(R.id.assignspin);
        aula = (Spinner) findViewById(R.id.aulaspin);
        data = (EditText) findViewById(R.id.data);
        hora = (EditText) findViewById(R.id.hora);
        datebtn = (Button) findViewById(R.id.datepicker);
        timebtn = (Button) findViewById(R.id.timepicker);
        guardar = (Button) findViewById(R.id.guardar);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        sp = getSharedPreferences("AssignaturaList", MODE_PRIVATE);
        s1 = sp.getString("myList", "").split("/");
        s3 = new String[s1.length];
        for (int i = 0; i < s1.length; i++) {
            s2 = s1[i].split("-");
            s3[i] = s2[0];
        }

        s1 = getResources().getStringArray(R.array.aula_array);
        s2 = getResources().getStringArray(R.array.carrera_array);

        aulaList = new ArrayList<>(Arrays.asList(s1));
        carreraList = new ArrayList<>(Arrays.asList(s2));

        assignaturaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, s3);
        carreraAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carreraList);
        aulaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aulaList);

        assignaturaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carreraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aulaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        assignatura.setAdapter(assignaturaAdapter);
        carrera.setAdapter(carreraAdapter);
        aula.setAdapter(aulaAdapter);

        if (getIntent().getBooleanExtra("edita", false)){
            temaedit = getIntent().getStringExtra("string").split("-");

            data.setText(temaedit[0]);
            hora.setText(temaedit[1]);
            carrera.setSelection(carreraAdapter.getPosition(temaedit[2]));
            assignatura.setSelection(assignaturaAdapter.getPosition(temaedit[3]));
            aula.setSelection(aulaAdapter.getPosition("Aula " + temaedit[4]));
        }

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(998);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("ExamList", MODE_PRIVATE);
                spe = sp.edit();
                if (getIntent().getBooleanExtra("edita", false)) {
                    e1 = sp.getString(temaedit[3], "").split("_");
                    saveExamens = new StringBuilder();
                    for(int j = 0; j < e1.length; j++){
                        if (!e1.equals(getIntent().getStringExtra("string"))) saveExamens.append(e1[j]).append("_");
                    }
                    spe.putString(temaedit[3], saveExamens.toString());
                    spe.commit();
                }
                saveExamens = new StringBuilder();
                saveExamens.append(sp.getString(assignatura.getSelectedItem().toString(), ""))
                        .append(data.getText().toString()).append("-")
                        .append(hora.getText().toString()).append("-")
                        .append(assignatura.getSelectedItem().toString()).append("-")
                        .append(aula.getSelectedItem().toString()).append("-")
                        .append(carrera.getSelectedItem().toString()).append("_");
                spe.putString(assignatura.getSelectedItem().toString(), saveExamens.toString());
                spe.commit();
                finish();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    data.setText(new StringBuilder().append(dayOfMonth).append("/").append(monthOfYear).append("/").append(year));
                }
            }, year, month, day);
        }
        if (id == 998) {
            return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hora.setText(hourOfDay + ":" + minute);
                }
            }, hour, minute, true);
        }
        return null;
    }
}