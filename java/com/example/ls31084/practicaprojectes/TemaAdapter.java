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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aleix on 05/05/2016.
 */
public class TemaAdapter extends ArrayAdapter {
    public static final int layout=R.layout.activity_nassignatura3;
    private ArrayList<String> elements;
    private Context context;
    private View row;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;
    private StringBuilder saveList;
    private String getList;
    private String[] arrayStrings;
    private String[] nomdesc;

    public TemaAdapter(Context context, List<String> objects) {
        super(context, layout, objects);
        this.elements = new ArrayList<>();
        this.context = context;
        ompleLlista();
    }

    public void ompleLlista() {
        this.elements.clear();
        nomdesc = getContext().getSharedPreferences("nouAssig", Context.MODE_PRIVATE).getString("nouAssig", "").split("-");

        sharedPref = getContext().getSharedPreferences("Temari", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        getList = sharedPref.getString(nomdesc[0], "");
        arrayStrings = getList.split("-");
        if(getList.equals("")) {
            insereixTema("Tema de ejemplo");
            sharedPrefEditor.clear().commit();
        } else{
            for (int i = 0; i < arrayStrings.length; i++){
                insereixTema(arrayStrings[i]);
            }
        }
    }

    public String getItem (int index) {
        return this.elements.get(index);
    }

    public int getCount () {
        return elements.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(row==null){
            row = inflater.inflate(R.layout.tema_row, parent, false);
        }
        String a = getItem(position);
        row.setTag(a);

        TextView nom = (TextView) row.findViewById(R.id.nomtema);

        nom.setText(a);


        Button deleteButton = (Button) row.findViewById(R.id.delbutton);
        deleteButton.setTag(position);
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Integer index = (Integer) v.getTag();
                elements.remove(index.intValue());
                notifyDataSetChanged();

                saveList = new StringBuilder("");

                for (String x : elements){
                    saveList.append(x+"-");
                }

                sharedPrefEditor.putString(nomdesc[0], saveList.toString());
                sharedPrefEditor.commit();
            }
        });

        Button upButton = (Button) row.findViewById(R.id.up);
        upButton.setTag(position);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Integer index = (Integer) v.getTag();
                if(position != 0) {
                    String s = elements.get(position);
                    String t = elements.get(position - 1);
                    elements.set(position, t);
                    elements.set(position - 1, s);
                    notifyDataSetChanged();

                    saveList = new StringBuilder("");

                    for (String x : elements){
                        saveList.append(x+"-");
                    }

                    sharedPrefEditor.putString(nomdesc[0], saveList.toString());
                    sharedPrefEditor.commit();
                }

            }
        });

        Button downButton = (Button) row.findViewById(R.id.down);
        downButton.setTag(position);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Integer index = (Integer) v.getTag();
                try {
                    String s = elements.get(position);
                    String t = elements.get(position + 1);
                    elements.set(position, t);
                    elements.set(position + 1, s);
                    notifyDataSetChanged();

                    saveList = new StringBuilder("");

                    for (String x : elements){
                        saveList.append(x+"-");
                    }

                    sharedPrefEditor.putString(nomdesc[0], saveList.toString());
                    sharedPrefEditor.commit();
                } catch (IndexOutOfBoundsException exeption){}

            }
        });
        return row;
    }

    public void insereixTema(String nom){
        this.elements.add(new String(nom));
        notifyDataSetChanged();
    }
}
