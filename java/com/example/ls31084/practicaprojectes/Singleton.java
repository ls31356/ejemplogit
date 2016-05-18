package com.example.ls31084.practicaprojectes;

/**
 * Created by Usuario on 18/05/2016.
 */
public class Singleton {
    public String nomActionbar;
    private static Singleton singleton = new Singleton( );
    private Singleton(){ }
    public static Singleton getInstance( ) {
        return singleton;
    }

    protected void setNomActionbar (String customNom) {
        nomActionbar = customNom.toString();
    }
    protected String getNomActionbar () {
        return getNomActionbar();
    }
}
