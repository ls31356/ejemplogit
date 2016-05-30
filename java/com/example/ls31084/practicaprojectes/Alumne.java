package com.example.ls31084.practicaprojectes;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

/**
 * Created by educabezas on 22/05/16.
 */
public class Alumne extends AppCompatActivity {
    private String nom;
    private int edat;
    private String especialitat;
    private Date fechaNeixament;
    private Assignatura[] assig;
    /**
     * variable que indica el sexo del alumno: m si masculino, f si es femenino
     */
    private String sexe;
    private Bitmap foto;

    public Alumne(String nom, int edat, String especialitat, Date fechaNeixament, String sexe, Bitmap foto, Assignatura[] assig) {
        this.nom = nom;
        this.edat = edat;
        this.especialitat = especialitat;
        this.fechaNeixament = fechaNeixament;
        this.sexe = sexe;
        this.foto = foto;
        this.assig = assig;
    }

    public String getNom() {
        return nom;
    }

    public int getEdat() {
        return edat;
    }

    public String getEspecialitat() {
        return especialitat;
    }

    public Date getFechaNeixament() {
        return fechaNeixament;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public String getSexe() {
        return sexe;
    }


}
