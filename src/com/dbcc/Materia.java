package com.dbcc;

import com.util.Lista;
import java.io.Serializable;

/**
 * Clase que permite el manejo adecuado de las materias
 * @author Aquino Chapa Armando Abraham, Merino Peña Kevin Ariel
 * @version  1
 */
public class Materia implements Serializable {
    private Lista<Alumno> alumnas;
    private int clave;
    private String nombre;
    private String profesora;

    /**
     * Constructor que permite el ingreso correcto de los valores necesarios para la creación de un objeto de tipo materia
     * @param nombre Nombre de la materia
     * @param profesora Profesora de la materia
     * @param clave Clave de la materia
     */
    public Materia(String nombre, String profesora, int clave){
        this.nombre = nombre;
        this.clave = clave;
        this.profesora = profesora;
        alumnas = new Lista<>();
    }

    /**
     * Constructor para la creación de una materia
     * @param nombre Nombre de la materia
     */
    public Materia(String nombre){
        this.nombre = nombre;
        this.profesora = "NO asignada";
        alumnas = new Lista<>();
    }

    /**
     * Método para agregar una alumna
     * @param alumna Alumna a agregar
     */
    public void agregarAlumno(Alumno alumna){
        alumnas.agregar(alumna);
    }

    /**
     * Getter para acceder al nombre de la materia
     * @return Nombre
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Materia: " + nombre + " Profesor: "+ profesora + " Clave: " + clave +"\n" ;
    }

    /**
     * Setter para asignar la clave de una materia
     * @param clave Clave
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     * Setter para asignar una profesora
     * @param profesora Profesora
     */
    public void setProfesora(String profesora) {
        this.profesora = profesora;
    }

    /**
     * Método que genera una lista con las alumnas
     * @return alumnas
     */
    public Lista<Alumno> listaInscritos(){
        return alumnas;
    }
}
