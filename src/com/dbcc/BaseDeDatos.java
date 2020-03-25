package com.dbcc;

import com.serializer.Serializer;
import com.util.Lista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * <h1>Base de Datos</h1>
 * Dentro de esta clase se van a simular las veces de una base de datos que tiene alumnos y materias
 * Se encarga de leer un archivo y darle formato a la informacion que contiene para realacionarla con alumnos
 * y sus respectivas materias
 * @author Ariel, Armando
 * @version 1
 */
public class BaseDeDatos implements Serializable {
    /**
     * Esta sera la primera lista que tengamos, donde los datos estan sin procesar y seguramente
     * habra elementos repetidos o no bien relacionados
     */
    private static Lista<Alumno> listaMadre = new Lista<>();
    /**
     * En esta estructura almacenaremos los nombres de las mataerias sin repetir
     */
    private  Lista<Materia> listaMaterias = new Lista<>();
    /**
     * Esta lista albergara los alumnos sin repetir, con las materias correspondietnes
     */
    private Lista<Alumno> listaFinalF = new Lista<>();
    private static int contador;

    /**
     * En este metodo se toma el path que se indica al principio para crear un flujo de lectura donde en cada linea que
     * encuentre dividira la linea en dos, la primera parte antes de que aparezca una coma y la segunda viene despues de
     * la coma, ademas esta pensado para que las lineas esten entre parentesis por lo que los elimina
     * @param ruta direccion del archivo a leer
     */
    public void leyendo(String ruta){
        try {
            Scanner input = new Scanner(new File(ruta));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] casit = line.split(",");
                String name = casit[0].substring(1);
                String materia = casit[1];
                materia = materia.substring(1,materia.length()-1);
                Alumno nuevo = new Alumno(name, materia);
                listaMadre.agregar(nuevo);
            }
            input.close();
        } catch (FileNotFoundException e){
            System.out.println("NO se encontro el archivo");
        } catch (IOException e){
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println("El documento no se pudo abrir, intente de nuevo");
        }
        if (listaFinalF.esVacia()){
            unionRelacionar();
        }
    }

    /**
     * En este metodo se busca cada uno de los alumnos que se recolecto el la lista principal y luego lo regeresa la materia
     * con los alumnos tantas veces como haya encontrado los alumnos
     * @param nombreMateria la materia con la que buscara relacionar a los alumnos
     * @return materia con todos los alumnos que encontro
     */
    public Materia asignaAlumnos(String nombreMateria){
        Materia materia = new Materia(nombreMateria);
        for (Alumno al: listaMadre) {
            if (al.getMateria().equals(nombreMateria)){
                materia.agregarAlumno(al);
            }
        }
        return materia;
    }

    /**
     *
     * @return
     */
    public Lista<String> alumnosSinRepetir(){
        Lista<String> alumnosSinRepetir = new Lista<>();
        for (Alumno estudiante: listaMadre) {
            if (!alumnosSinRepetir.contiene(estudiante.getName())){
                estudiante.setMatricula(++contador);
                alumnosSinRepetir.agregar(estudiante.getName());
            }
        }
        return alumnosSinRepetir;
    }


    public void unionRelacionar(){
        if (!listaFinalF.esVacia()){
            return;
        }
        for (String nombre: alumnosSinRepetir()){
            Alumno nuevo = new Alumno(nombre);
            for (Alumno estudiante : listaMadre) {
                if (nombre.equals(estudiante.getName())){
                    if (estudiante.getMatricula() != 0){
                        nuevo.setMatricula(estudiante.getMatricula());
                    }
                    nuevo.agregarMaterias(estudiante.getMateria(),"No asignado",0);
                }
            }
            listaFinalF.agregar(nuevo);
        }
    }
    public Lista<String> materiasSinRepetir(){
        Lista<String> materiasSinR = new Lista<>();
        for (Alumno estudiante: listaMadre) {
            if (!materiasSinR.contiene(estudiante.getMateria())){
                materiasSinR.agregar(estudiante.getMateria());
            }
        }
        return materiasSinR;
    }

    public Lista<Materia> materiasAlumnos() {
        if (listaMaterias.esVacia()) {
            Lista<Materia> materiasL = new Lista<>();
            for (String mat: materiasSinRepetir()) {
                materiasL.agregar(asignaAlumnos(mat));
            }
            listaMaterias = materiasL;
        }
        return listaMaterias;
    }

    public void  asignarClaveProfe(Materia materia, String profe, int clave){
        for (Alumno alumno : listaFinalF) {
            for (Materia materia1: alumno.getMaterias()) {
                if (materia1.getNombre().toLowerCase().equals(materia.getNombre().toLowerCase())){
                    materia1.setProfesora(profe);
                    materia1.setClave(clave);
                }
            }
        }
        for (Materia materia1 : listaMaterias) {
            if (materia1.getNombre().toLowerCase().equals(materia.getNombre().toLowerCase())){
                materia1.setProfesora(profe);
                materia1.setClave(clave);
            }
        }
        Serializer ser = new Serializer();
        ser.write(listaMaterias, "Base_Datos.dat");
        System.out.println(listaMaterias);
    }



    public Materia buscaMateria(String name){
        for (Materia materia : materiasAlumnos()){
            if (materia.getNombre().toLowerCase().equals((name.toLowerCase()))){
                return materia;
            }
        }
        return null;
    }
    public Alumno buscaAlumnos(String name){
        if (listaFinalF.esVacia()){
            unionRelacionar();
        }
        for (Alumno estudiante: listaFinalF) {
            if (estudiante.getName().toLowerCase().equals(name.toLowerCase())){
                return estudiante;
            }
        }
        return null;
    }

}
