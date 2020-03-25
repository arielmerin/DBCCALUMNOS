package com.dbcc;

import com.util.Lista;

import java.io.File;
import java.io.FileNotFoundException;
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
    private Lista<Integer> claves = new Lista<>();
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
     * En este metodo se toma la lista que se genero luego de leer el archivo de texto para comparar por cada alumno
     * dentro de esa lista entonces emplea una compracion donde se pregunta si ese nombre ya estaba añadido y en caso
     * de que no ocurra entonces lo añade en el caso contrario no lo agrega
     * @return lista de alimnos que no tiene elementos repetidos
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

    /**
     * Durante la ejecucion de este metodo se genera una lista donde cada alumno no repetido tiene todas las materias
     * que tenia en la lista original, finalmente se obtiene la relacion de alumnos-materia con cada uno como le corresponde
     */
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

    /**
     * En este metodo se busca cada materia de los alumnos obtenidos del metodo leyendo donde se tienen todos los datos
     * repetidos, luego va añadiendo los nombres de las materias bajo el criterio de que no haya una repetida
     * @return lista con el nombre de las materias sin repetirse
     */
    public Lista<String> materiasSinRepetir(){
        Lista<String> materiasSinR = new Lista<>();
        for (Alumno estudiante: listaMadre) {
            if (!materiasSinR.contiene(estudiante.getMateria())){
                materiasSinR.agregar(estudiante.getMateria());
            }
        }
        return materiasSinR;
    }

    /**
     * Este metodo asigna a cada una de las materias no repetidas todos los alumnos que tienen asignado en l alista de materias
     */
    public void materiasAlumnos() {
        if (listaMaterias.esVacia()) {
            Lista<Materia> materiasL = new Lista<>();
            for (String mat: materiasSinRepetir()) {
                materiasL.agregar(asignaAlumnos(mat));
            }
            listaMaterias = materiasL;
        }
    }

    /**
     * En este metodo se hace uso de la materia con la que funciona para busca ren la lista donde estan los alumnos con
     * sus materias y tambien busca en la lista de materias para actializar los datos, en cada una le asigna el nombre
     * de lx profesorx de la materia
     * @param materia materia a la que se le asignara profesor y clave
     * @param profe nombre del profesor
     * @param clave numero de clave para añadir a la materia del primer parametro
     */
    public void  asignarClaveProfe(Materia materia, String profe, int clave){
        claves.agregar(clave);
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
        System.out.println(listaMaterias);
    }

    /**
     * Este metodo itera sobre las lista de materias para encontrar una en especifico, lo hace comparando los nombres de
     * las materias que ya se tienen
     * @param name nombre de la materia que se buscara
     * @return materia encontrada en la lista o nullo en caso de no haberlo encontrado
     */
    public Materia buscaMateria(String name){
        materiasAlumnos();
        for (Materia materia : listaMaterias){
            if (materia.getNombre().toLowerCase().equals((name.toLowerCase()))){
                return materia;
            }
        }
        return null;
    }
    /**
     * Este metodo itera sobre las lista de alumnos para encontrar uno en especifico, lo hace comparando los nombres de
     * las alumnas que ya se tienen
     * @param name nombre de la alumna que se buscara
     * @return alumna encontrada en la lista o nulo en caso de no haberla encontrado
     */
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

    /**
     * Este metodo verifica si la clave existe en la lista de claves de materias
     * @param clave elemento a veriicar si existe con anterioridad
     * @return si encontro la clave o no
     */
    public boolean existeClave(int clave){
        return claves.contiene(clave);
    }

}