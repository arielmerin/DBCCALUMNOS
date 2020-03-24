package com.UIMenu;

import com.dbcc.*;

import java.util.Scanner;

import static com.util.Utilidades.getInt;
import static com.util.Utilidades.getStr;

public class UIMenu {
    public static void principal(){
        BaseDeDatos db = new BaseDeDatos();
        if (true){
            /**
             * todo lo de la serializacion
             */
            db.leyendo();
        }
        System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
        System.out.println("BIENIDX A la carrera de Ciencias de la Computacion");
        boolean continuar = true;
        do{
            System.out.println("Estas son las opciones");
            System.out.println("[1] Materias a las que esta inscrito un alumno");
            System.out.println("[2] Alumnos inscritos en una materia");
            System.out.println("[3] Añadir profesor y clave de materias");
            System.out.println("[5] Sallir");
            int respues = getInt("Seleccione una opcion: ", "Error, intente de nuevo");
            switch (respues){
                case 1:
                    System.out.println("Ingrese el nombre del alumno a consultar");
                    String nombre = getStr("Ingrese el nombre a consultar: ", "Error, solo ingrese letras");
                    Alumno busqueda = db.buscaAlumnos(nombre);
                    if (busqueda != null ){
                        System.out.println(busqueda);
                    }else {
                        System.out.println("No fue encontrado el estudiante " + nombre);
                    }
                    break;
                case 2:
                    String mat = getStr("Ingrese el nombre de la materia", "Error, solo ingrese letras");
                    Materia busquedaMat = db.buscaMateria(mat);
                    if (busquedaMat != null){
                        System.out.println(busquedaMat);
                    }else {
                        System.out.println("No fue encontrada la materia " + mat);
                    }
                    break;
                case 3:
                    String laMateriaAAsignar = getStr("La materia a asignar", "Error, intente de nuevo");
                    Materia buscaAgregar = db.buscaMateria(laMateriaAAsignar);
                    if (buscaAgregar != null){
                        int clav = getInt("Ingrese la clave de la materia", "Ingrese un  valor valido");
                        String  prof = getStr("Ingrese el nombre de la profesora(o): ", "Error, ingrese solo letras");
                        db.asignarClaveProfe(buscaAgregar, prof,clav);
                        System.out.println(BaseDeDatos.getListaMaterias());
                    }else {
                        System.out.println("NO ha resultados para " + laMateriaAAsignar);
                    }
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Hasta luego");
                    System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
                    break;
                default:
                    System.out.println("Error, seleccione una opcion valida");
            }

        }while (continuar);

    }
}
