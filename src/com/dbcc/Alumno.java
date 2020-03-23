package com.dbcc;

public class Alumno {
    private String name;
    private String materia;
    private int matricula;

    public Alumno(String name, String materia, int matricula){
        this.materia = materia;
        this.matricula = matricula;
        this.name = name;
    }
    public Alumno(String name, String materia){
        this.materia = materia;
        this.name = name;
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

    @Override
    public String toString() {
        return "Alumno:" + name + " materia: " + materia + " matricula: " + matricula + '\n';
    }
}
