package com.dbcc;

import com.serializer.Serializer;
import com.util.Lista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;


public class BaseDeDatos implements Serializable {
    private static Lista<Alumno> listaMadre = new Lista<>();
    private  Lista<Materia> listaMaterias = new Lista<>();
    /**
     * Esta lista albergara los alumnos sin repetir, con las materias correspondietnes
     */
    private Lista<Alumno> listaFinalF = new Lista<>();
    private static int contador;


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

    public Materia asignaAlumnos(String nombreMateria){
        Materia materia = new Materia(nombreMateria);
        for (Alumno al: listaMadre) {
            if (al.getMateria().equals(nombreMateria)){
                materia.agregarAlumno(al);
            }
        }
        return materia;
    }

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
