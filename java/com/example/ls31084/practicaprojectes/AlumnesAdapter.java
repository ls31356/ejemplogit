package com.example.ls31084.practicaprojectes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by educabezas on 22/05/16.
 */
public class AlumnesAdapter extends ArrayAdapter {
    public static final String PREFS = "AlumneList";
    public static final int layout=R.layout.alumne_row;
    private List<Alumne> elements;
    private Context context;
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    private View row;

    public AlumnesAdapter(Context context, List<Alumne> array){
        super(context, layout);
        this.elements = array;
        ompleLlista();
    }

    public void ompleLlista() {
        this.elements.clear();

        sharedPrefs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();


        if (!sharedPrefs.contains("studenList") || sharedPrefs.getString("studentList", "").equals("")) {
            elements.add(new Alumne("alumne prova", 18, "especialitat", new Date(), "m",  null, null));
        }
        else {
            String studentList = sharedPrefs.getString("studentList", "empty");
            try {

                JSONArray jsonArray = new JSONArray(studentList);

                for (int i = 0; i < jsonArray.length(); i++) {
                    elements.add((Alumne) jsonArray.get(i));
                }

            } catch (JSONException e) {
            }

        }

    }

    public Alumne getItem (int index) {
        return this.elements.get(index);
    }

    public int getCount () {
        return elements.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        row = convertView;


        if(row==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.alumne_row, parent, false);
            row.setClickable(true);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    canviActivitat(row, position);
                }
            });
        }
        Alumne a = getItem(position);
        row.setTag(a);

        TextView nom = (TextView) row.findViewById(R.id.nom);
        TextView edat = (TextView) row.findViewById(R.id.edat);
        TextView esp = (TextView) row.findViewById(R.id.esp);
        ImageView img = (ImageView) row.findViewById(R.id.img);

        nom.setText(a.getNom());
        edat.setText(Integer.toString(a.getEdat()));
        esp.setText(a.getEspecialitat());
        img.setImageResource(R.mipmap.ic_launcher);

        Button deleteButton = (Button) row.findViewById(R.id.alistbutton);
        deleteButton.setTag(position);
        deleteButton.setOnClickListener(new Button.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final Integer index = (Integer) v.getTag();
                                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( getContext());

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
                                                                JSONArray jsonArray = new JSONArray();
                                                                for (int i = 0; i < elements.size(); i++){
                                                                    jsonArray.put(elements.get(i));
                                                                }


                                                                editor.putString("studentList", jsonArray.toString());
                                                                editor.commit();
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
        Alumne a = getItem(position);
        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        row.setTag(a);

       /* Intent i = new Intent(context, VassignaturaActivity.class);
        i.removeExtra("nomal");
        i.putExtra("nomal", a.getNom() + "/" + a.getDescripcio());
        getContext().startActivity(i);*/
    }
}
