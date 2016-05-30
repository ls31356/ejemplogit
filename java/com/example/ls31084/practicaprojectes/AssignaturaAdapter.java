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

/**
 * Created by Aleix on 05/05/2016.
 */
public class AssignaturaAdapter extends ArrayAdapter {
    public static final int layout=R.layout.activity_assignatures;
    private ArrayList<Assignatura> elements;
    private Context context;
    private View row;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;
    private StringBuilder saveList;
    private String getList;
    private String[] arrayStrings;
    private String[] nomdesc;

    public AssignaturaAdapter(Context context, List<Assignatura> objects) {
        super(context, layout, objects);
        this.elements = new ArrayList<>();
        this.context = context;
        ompleLlista();
    }

    public void ompleLlista() {
        this.elements.clear();

        sharedPref = getContext().getSharedPreferences("AssignaturaList", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        getList = sharedPref.getString("myList", "");
        if (getList.isEmpty()) this.elements.add(new Assignatura("Asignatura de ejemplo", "Descripció breu de la assignatura tallant les lletres al superar les 2 linies", R.mipmap.ic_launcher));

        arrayStrings = getList.split("/");
        for (int i = 0; i < arrayStrings.length; i++){
            nomdesc = arrayStrings[i].split("-");
            this.elements.add(new Assignatura(nomdesc[0], nomdesc[1], R.mipmap.ic_launcher));
        }
    }

    public Assignatura getItem (int index) {
        return this.elements.get(index);
    }

    public int getCount () {
        return elements.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(row==null){
            row = inflater.inflate(R.layout.assignatura_row, parent, false);
            row.setClickable(true);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    canviActivitat(row, position);
                }
            });
        }
        Assignatura a = getItem(position);
        row.setTag(a);

        TextView nom = (TextView) row.findViewById(R.id.nom);
        TextView desc = (TextView) row.findViewById(R.id.desc);
        ImageView img = (ImageView) row.findViewById(R.id.img);

        nom.setText(a.getNom());
        desc.setText(a.getDescripcio());
        img.setImageResource(a.getImage());

        Button deleteButton = (Button) row.findViewById(R.id.listbutton);
        deleteButton.setTag(position);
        deleteButton.setOnClickListener(new Button.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final Integer index = (Integer) v.getTag();
                                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( context);

                                                // set title
                                                alertDialogBuilder.setTitle(R.string.dialogasstitle);
                                                alertDialogBuilder.setIcon(R.mipmap.alert);

                                                //set dialog message
                                                alertDialogBuilder.setMessage(R.string.dialogasstext).setCancelable(false)
                                                        .setPositiveButton(R.string.borrar,new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog,int id) {
                                                                // if this button is clicked,
                                                                elements.remove(index.intValue());
                                                                notifyDataSetChanged();
                                                                saveList = new StringBuilder("");

                                                                for (Assignatura x : elements){
                                                                    saveList.append(x.getNom()+"-"+x.getDescripcio()+"/");
                                                                }

                                                                sharedPrefEditor.putString("myList", saveList.toString());
                                                                sharedPrefEditor.commit();
                                                            }
                                                        }) .setNegativeButton(R.string.cancelar
                                                        , new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // if this button is clicked, do nothing
                                                        dialog.cancel();
                                                    }
                                                });
                                                AlertDialog alertDialog = alertDialogBuilder.create();
                                                // show it
                                                alertDialog.show();
                                            }
                                        }
        );
        return row;
    }

    public void canviActivitat(View row, int position){
        Assignatura a = getItem(position);
        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        row.setTag(a);

        Intent i = new Intent(context, VassignaturaActivity.class);
        i.removeExtra("nomdesc");
        i.putExtra("nomdesc", a.getNom()+"-"+a.getDescripcio());
        getContext().startActivity(i);
    }
}
