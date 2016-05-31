package com.example.ls31084.practicaprojectes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleix on 05/05/2016.
 */
public class ExamenAdapter extends ArrayAdapter {
    public static final int layout=R.layout.activity_examens;
    private ArrayList<Examen> elements;
    private Context context;
    private View row;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;
    private StringBuilder saveList;
    private String[] getList;
    private String[] arrayStrings;
    private String[] nomdesc;
    private String[] examens;
    private String[] contingut;
    int k, n;

    public ExamenAdapter(Context context, List<Examen> objects) {
        super(context, layout, objects);
        this.elements = new ArrayList<>();
        this.context = context;
        ompleLlista();
    }

    public void ompleLlista() {
        this.elements.clear();
        sharedPref = getContext().getSharedPreferences("AssignaturaList", Context.MODE_PRIVATE);
        getList = sharedPref.getString("myList", "").split("/");
        sharedPref = getContext().getSharedPreferences("ExamList", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        nomdesc = new String[getList.length];

        for (int i = 0; i < getList.length; i++) {
            arrayStrings = getList[i].split("-");
            nomdesc[i] = arrayStrings[0];
        }
        try {
            for (String e : nomdesc) {
                if(!sharedPref.getString(e, "").isEmpty()){
                    examens = sharedPref.getString(e, "").split("_");
                    for (int i = 0; i < examens.length; i++) {
                        contingut = examens[i].split("-");
                        this.elements.add(new Examen(contingut[0], contingut[1], contingut[2], contingut[3], contingut[4]));
                    }
                }
            }
            n = elements.size();
            for (int m = n; m >= 0; m--) {
                for (int i = 0; i < n - 1; i++) {
                    k = i + 1;
                    if (esMesGran(i, k, elements)) {
                        swap(i, k, elements);
                    }
                }
            }
        } catch (IndexOutOfBoundsException e){
            if(elements.size() >= 1) {}
            else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( context);
                alertDialogBuilder.setTitle(R.string.dialogexamtitle);
                alertDialogBuilder.setIcon(R.mipmap.alert);
                alertDialogBuilder.setMessage(R.string.dialogexammessage).setCancelable(false).setPositiveButton(R.string.crear,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent i = new Intent(getContext(), NexamActivity.class);
                        getContext().startActivity(i);
                    }
                }) .setNegativeButton(R.string.volver, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }


    public Examen getItem (int index) {
        return this.elements.get(index);
    }

    public int getCount () {
        return elements.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(row==null){
            row = inflater.inflate(R.layout.examen_row, parent, false);
            row.setClickable(true);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Examen e = getItem(position);
                    Intent i = new Intent(getContext(), NexamActivity.class);
                    i.putExtra("edita", true);
                    i.putExtra("string", e.getData() + "-" + e.getHora() + "-" + e.getCarrera() + "-" + e.getAssignatura() + "-" + e.getAula());
                    getContext().startActivity(i);
                }
            });
        }
        Examen examen = getItem(position);
        row.setTag(examen);

        TextView data = (TextView) row.findViewById(R.id.data);
        TextView hora = (TextView) row.findViewById(R.id.hora);
        TextView assignatura = (TextView) row.findViewById(R.id.assignatura);
        TextView aula = (TextView) row.findViewById(R.id.aula);
        TextView numAlumn = (TextView) row.findViewById(R.id.numalumnes);

        data.setText(examen.getData());
        hora.setText(examen.getHora());
        assignatura.setText("Assignatura: " + examen.getAssignatura());
        aula.setText(examen.getAula());
        numAlumn.setText("x" + " alumnes");
        return row;
    }

    public static boolean esMesGran (int i, int k, ArrayList<Examen> elements){
        String[] array1 = elements.get(i).getData().split("/");
        String[] array2 = elements.get(k).getData().split("/");
        String[] array3 = elements.get(i).getHora().split(":");
        String[] array4 = elements.get(k).getHora().split(":");
        if(Integer.valueOf(array1[2]) > Integer.valueOf(array2[2])) return true;
        if(Integer.valueOf(array1[1]) > Integer.valueOf(array2[1])) return true;
        if(Integer.valueOf(array1[0]) > Integer.valueOf(array2[0])) return true;
        if(Integer.valueOf(array3[0]) > Integer.valueOf(array4[0])) return true;
        if(Integer.valueOf(array3[1]) > Integer.valueOf(array4[1])) return true;
        return  false;
    }

    private void swap(int i, int j, ArrayList<Examen> elements) {
        Examen temp;
        temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
}
