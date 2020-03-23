package com.dbcc;

import com.util.Lista;

public class Materia {
    private Lista<Alumno> alumnos;
    private int clave;
    private String nombre;
    private String profesor;
    private String ayudantes;

    public Materia(String nombre, String profesor, int clave){
        this.nombre = nombre;
    }

    public void agregarAlumno(Alumno alumno){
        alumnos.agregar(alumno);
    }

    @Override
    public String toString() {
        return "Materia: " + nombre + " clave: "+ clave +"\nNumero de alumnos: " + alumnos.longitud() + " \n" + alumnos;
    }
}
