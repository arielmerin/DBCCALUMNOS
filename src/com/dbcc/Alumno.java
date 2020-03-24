package com.dbcc;

import com.util.Lista;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String name;
    private String materia;
    private Lista<Materia> materias;
    private int matricula;

    public Alumno(String name, String materia){
        this.materia = materia;
        this.name = name;
        materias = new Lista<>();
    }
    public Alumno(String name){
        this.name = name;
        materias = new Lista<>();
    }
    public void agregarMaterias(String nombre, String prof, int clave){
        Materia nueva = new Materia(nombre,prof,clave);
        materias.agregar(nueva);
    }
    public String getName() {
        return name;
    }

    public String getMateria() {
        return materia;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Lista<Materia> getMaterias() {
        return materias;
    }

    @Override
    public String toString() {
        return "Alumno:" + name + "Numero de cuenta: " + matricula ;
    }
}
