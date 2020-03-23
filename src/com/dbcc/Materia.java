package com.dbcc;

import com.util.Lista;

public class Materia {
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

    public void agregarAlumno(Alumno alumna){
        alumnas.agregar(alumna);
    }

    @Override
    public String toString() {
        return "Materia: " + nombre;
    }

    public Lista<Alumno> listaInscritos(){
        return alumnas;
    }
}
