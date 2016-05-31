package com.example.ls31084.practicaprojectes;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NalumneActivity extends BaseActivity {
    private Spinner spinner;

    private ArrayList spinnerArray;
    private ArrayAdapter<String> spinnerAdapter;
    private static final int SELECTED_PICTURE = 1;
    private EditText nom, fecha;
    private Button crear, addFoto;
    private ImageView foto;
    private RadioGroup rgroup;
    private RadioButton rbutton;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Bitmap yourSelectedImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nalumne);
        resId = R.string.noualumn;
        inflate = true;
        Log.i("info", "log does work");
        nom = (EditText) findViewById(R.id.nomnou);
        fecha = (EditText) findViewById(R.id.fechanou);
        foto = (ImageView) findViewById(R.id.img);
        addFoto =(Button) findViewById(R.id.addimg);
        crear = (Button) findViewById(R.id.create);
        pref = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        editor = pref.edit();

        spinner = (Spinner) findViewById(R.id.aspinner);
        spinnerArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.carrera_array)));
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        addFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECTED_PICTURE);
            }
        });
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = "";
                if (!pref.contains("studenList") || pref.getString("studentList", "").equals("")) {

                }
                else{json = pref.getString("studentList", "");}

                    try {
                        JSONArray jarray = new JSONArray(json);
                        String string = fecha.getText().toString();
                        if (/*isValidDate(string)*/  true){
                            Log.i("info", "Daaaaaaaaate is valid");
                           /* String fechaForm[] = string.split("/");
                            int dia = Integer.parseInt(fechaForm[0]);
                            int mes = Integer.parseInt(fechaForm[1]);
                            int any = Integer.parseInt(fechaForm[2]);

                            int edat = 2016 - any;
                            Date date = new Date(dia, mes, any);*/
                            Date date = new Date();
                            int edat = 20;
                            char sexe = ' ';
                            int selectedid = rgroup.getCheckedRadioButtonId();
                            rbutton = (RadioButton) findViewById(selectedid);
                            Log.i("info", jarray.toString());
                            if(rbutton.getText().toString().equals("Masculino") || rbutton.getText().toString().equals("Femenino")){
                                String carrera = spinner.getSelectedItem().toString();
                                Alumne student = new Alumne(nom.getText().toString(), edat, carrera ,  date, rbutton.getText().toString(), yourSelectedImage, new LinkedList<Assignatura>());
                                JSONObject jobject = new JSONObject();

                                jarray.put(student);
                                Log.i("info", jarray.toString());
                                String string2 =  nom.getText().toString() + "-" + edat + "-" + carrera + "-" + date.toString() + "-" + rbutton.getText().toString() + "-" + "";
                                Log.i("info", string2);
                                editor.putString("studentList", jarray.toString());
                                editor.commit();
                            }


                        }

                    } catch (JSONException e) {
                    }

                }

        });


       // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECTED_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    //Drawable d = new BitmapDrawable(yourSelectedImage);
                    foto.setImageBitmap(yourSelectedImage);
                    // foto.setBackground(d);
                }
                break;
            default:
                break;


        }


    }




}

