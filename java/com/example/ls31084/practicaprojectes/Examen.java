package com.example.ls31084.practicaprojectes;

/**
 * Created by Alba on 05/05/2016.
 */
public class Examen {
    private String data;
    private String hora;
    private String carrera;
    private String assignatura;
    private String aula;

    public Examen() {
    }

    public Examen(String data, String hora, String carrera, String assignatura, String aula) {
        this.data = data;
        this.hora = hora;
        this.carrera = carrera;
        this.assignatura = assignatura;
        this.aula = aula;
    }

    public String getHora() {
        return hora;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getAssignatura() {
        return assignatura;
    }

    public String getAula() {
        return aula;
    }

    public String getData(){
        return this.data;
    }
}
