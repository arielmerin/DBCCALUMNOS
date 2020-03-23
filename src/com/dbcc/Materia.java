package com.dbcc;

import com.util.Lista;

public class Materia {
    private Lista<Alumno> alumnos;
    private int clave;
    private String nombre;
    private String profesora;
    private String ayudantes;

    public Materia(String nombre, String profesora, int clave){
        this.nombre = nombre;
        this.clave = clave;
        this.profesora = profesora;
        alumnos = new Lista<>();
    }

    public void agregarAlumno(Alumno alumno) throws IllegalArgumentException{
        if (alumno == null){
            throw new IllegalArgumentException("NO debe ser nulo");
        }
        try {
            alumnos.agregar(alumno);
        } catch (NullPointerException e){
            System.out.println("Eso no deberia de pasaar");
        }
    }

    @Override
    public String toString() {
        return "Materia: " + nombre + " \nclave: "+ clave + "\nProfesora: "+ profesora
                +"\nNumero de alumnos: " + alumnos.longitud();
    }
}
