package com.example.ls31084.practicaprojectes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Declarem dos variables de SharedPrefereces, una per guardar i l'altre per editar.
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPreferencesEditor;

    //Boleà que indica si el CheckBox estava o no marcada a la anterior sessió
    private boolean saveLogin;

    //Declarem els elements del layout
    private EditText email, pass;
    private Button login;
    private CheckBox autologin;

    //Definim 2 strings on guardarem el que s'introdueixi als EditTexts
    private String usuari,contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        checkUser();
    }

    public void checkUser(){
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        autologin = (CheckBox) findViewById(R.id.autologin);

        //Recuperem la informació de el fitxer de SharedPreferenes.
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        //Creem un nou editor per aquestes Preferences
        loginPreferencesEditor = loginPreferences.edit();

        //Mirem si a la anterior sessió, el checkbox estava activat.
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            loginOk();
        } else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuari = email.getText().toString();
                    contra = pass.getText().toString();

                    if (usuari.equals("administrador@salleurl.edu") && contra.equals("123qwe")){
                        if (autologin.isChecked()) {
                            loginPreferencesEditor.putBoolean("saveLogin", true);
                            loginPreferencesEditor.putString("username", usuari);
                            loginPreferencesEditor.putString("password", contra);
                            loginPreferencesEditor.commit();
                        } else {
                            loginPreferencesEditor.clear();
                            loginPreferencesEditor.commit();
                        }
                        loginOk();
                    } else {
                        Toast.makeText(getApplicationContext(),R.string.incorrecte, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void loginOk() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        finish();
    }
}