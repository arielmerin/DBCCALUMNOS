package com.UIMenu;

import com.dbcc.*;
import com.serializer.Serializer;

import java.io.File;
import java.util.Scanner;

import static com.util.Utilidades.getInt;
import static com.util.Utilidades.getStr;

public class UIMenu {
    static Serializer serializer = new Serializer();
    static String ruta = "Base_Datos.dat";
    static String rutadefault = "src/alumnos.txt";
    static BaseDeDatos db = new BaseDeDatos();
    public static void principal(){
        if(serializer.read(ruta) != null){
            db = (BaseDeDatos) serializer.read(ruta);
        }
        System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
        System.out.println("BIENIDX A la carrera de Ciencias de la Computacion");
        boolean primeraPregunta = true;
        do {
            System.out.println("[1] Leer un nuevo archivo de texto ");
            System.out.println("[2] Trabajar con los datos del sistema ");
            int lecturaONo = getInt("Ingrese una opcion", "intente con numeros validos");
            switch (lecturaONo){
                case 1:
                    Scanner scn = new Scanner(System.in);
                    System.out.println("Ingrese la ruta y el nombre del archivo con la extension .txt:  " );
                    String rutaLeer = scn.nextLine();
                    File archivo = new File(rutaLeer);
                    primeraPregunta = false;
                    if (!archivo.exists()) {
                        System.out.println("OJO: ¡¡No existe dicho archivo!!");
                        primeraPregunta = true;
                    }else {
                        db.leyendo(rutaLeer);
                    }
                    break;
                case 2:
                    File archivo2 = new File(ruta);
                    if (!archivo2.exists()) {
                        System.out.println("OJO: ¡¡No existe el archivo de configuración!!");
                    }else {
                        System.out.println("En este caso si existe el archivo");
                        db.leyendo(rutadefault);
                        System.out.println(db.getListaMaterias());
                        primeraPregunta = false;
                    }
                    break;
                default:
                    System.out.println("Intente de nuevo con alguna opcion valida");
            }

        }while (primeraPregunta);
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
                    System.out.println(db.unionRelacionar());
                    System.out.println("Ingrese el nombre del alumno a consultar");
                    String nombre = getStr("Ingrese el nombre a consultar: ", "Error, solo ingrese letras");
                    Alumno busqueda = db.buscaAlumnos(nombre);
                    if (busqueda != null ){
                        System.out.println(busqueda);
                        System.out.println(busqueda.getMaterias());
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
                    }else {
                        System.out.println("NO ha resultados para " + laMateriaAAsignar);
                    }
                    break;
                case 5:
                    serializer.write(db,ruta);
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
