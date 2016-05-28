package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alba on 05/05/2016.
 */
public class Assignatura {
    private String nom;
    private String descripcio;
    private int mainImage;

    public Assignatura(){}
    public Assignatura(String nom, String descripcio, int img){
        this.nom = nom;
        this.descripcio = descripcio;
        this.mainImage = img;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescripcio() {
        return this.descripcio;
    }

    public int getImage() {
        return this.mainImage;
    }
}
