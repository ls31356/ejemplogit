package com.example.ls31084.practicaprojectes;

/**
 * Created by Alba on 05/05/2016.
 */
public class Examen {
    private String data;
    private String hora;
    private String assignatura;
    private String aula;
    private String carrera;

    public Examen() {
    }

    public Examen(String data, String hora, String assignatura, String aula, String carrera) {
        this.data = data;
        this.hora = hora;
        this.assignatura = assignatura;
        this.aula = aula;
        this.carrera = carrera;
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
        return data;
    }
}
