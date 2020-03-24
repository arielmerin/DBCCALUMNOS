package com.dbcc;

import com.util.Lista;

import java.io.Serializable;

public class Materia implements Serializable {
    private Lista<Alumno> alumnas;
    private int clave;
    private String nombre;
    private String profesora;

    public Materia(String nombre, String profesora, int clave){
        this.nombre = nombre;
        this.clave = clave;
        this.profesora = profesora;
        alumnas = new Lista<>();
    }
    public Materia(String nombre){
        this.nombre = nombre;
        this.profesora = "NO asinada";
        alumnas = new Lista<>();
    }

    public void agregarAlumno(Alumno alumna){
        alumnas.agregar(alumna);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Materia: " + nombre + " Profe: "+ profesora + " Clave: " + clave +"\n" ;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setProfesora(String profesora) {
        this.profesora = profesora;
    }

    public Lista<Alumno> listaInscritos(){
        return alumnas;
    }
}
