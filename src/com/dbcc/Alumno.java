package com.dbcc;

import com.util.Lista;

import java.io.Serializable;

/**
 * Clase que permite el manejo adecuado de los alumnos
 * @author Aquino Chapa Armando Abraham, Merino Peña Kevin Ariel
 * @version 1
 */
public class Alumno implements Serializable {
    private String name;
    private String materia;
    private Lista<Materia> materias;
    private int matricula;

    /**
     * Constructor que permite el ingreso correcto de los valores necesarios para la creación de un objeto de tipo Alumno
     * @param name Nombre del alumno
     * @param materia Materia que toma el alumno
     */
    public Alumno(String name, String materia){
        this.materia = materia;
        this.name = name;
        materias = new Lista<>();
    }

    /**
     * Constructor para la creación de un alumno
     * @param name Nombre que recibirá el alumno
     */
    public Alumno(String name){
        this.name = name;
        materias = new Lista<>();
    }

    /**
     * Método para asignar a una materia profesor y clave.
     * @param nombre Nombre de la materia
     * @param prof Profesor que imparte la materia
     * @param clave Clave que recibirá la materia
     */
    public void agregarMaterias(String nombre, String prof, int clave){
        Materia nueva = new Materia(nombre,prof,clave);
        materias.agregar(nueva);
    }

    /**
     * Getter para acceder al nombre
     * @return Nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Getter para acceder a una materia
     * @return Materia
     */
    public String getMateria() {
        return materia;
    }

    /**
     * Getter para acceder a la matrícula
     * @return Matricula
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Setter para devolver una matrícula
     * @param matricula Matrícula ingresada
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * Método que genera una lista con las materias
     * @return Materias
     */
    public Lista<Materia> getMaterias() {
        return materias;
    }

    @Override
    public String toString() {
        return "Alumno: " + name + "\nNumero de cuenta: " + matricula ;
    }
}
