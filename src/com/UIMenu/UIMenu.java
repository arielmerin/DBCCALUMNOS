package com.UIMenu;

import com.dbcc.*;
import com.serializer.Serializer;
import java.io.File;
import java.util.Scanner;

import static com.util.Utilidades.getInt;
import static com.util.Utilidades.getStr;

/**
 * <h1>UI Menu</h1>
 * Esta clase provee de todas las interaccione graficas que el usuario experimentara a traves de la terminal durante la
 * ejecucion del programa, lo refente a mostrar y manejar las opciones que el ejecutador use
 * @author Armando Aquino Chapa, Ariel Merino Peña
 * @version 1
 */
public class UIMenu {
    /**
     * Objeto que sera util para realizar la persistencia de datos a traves de la serializacion de informacipon
     */
    private static Serializer serializer = new Serializer();
    /**
     * Path por defecto donde se alojara el arachivo que contiene la informacion serializada
     */
    private static String ruta = "Base_Datos.dat";
    /**
     * Ruta donde por omision se espera encontrar al menos un archivo txt
     */
    private static String rutadefault = "alumnos.txt";
    /**
     * Este objeto sera de utlilidad para toda la clase pues es gracias a este que se pueden tener habilitadas todas las
     * funcionalidades en nuestro proyecto
     */
    private static BaseDeDatos db = new BaseDeDatos();

    /**
     * Metodo inicial donde se le pide al usuario la seleccion de opciones para ofrecerle que se trabaje con la informacion
     * del sistema o que se lea un nuevo archivo, tambien se presentan las opciones de ver las materias a las que esta
     * inscrito un alumno y los alumnos que estan inscritos en una materia, solo se puede salir de este metodo una vez que
     * el usuario lo pidio de manera explicita
     */
    public static void principal(){
        System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
        System.out.println("BIENIDX A la carrera de Ciencias de la Computación");
        boolean primeraPregunta = true;
        do {
            System.out.println("[1] Leer un nuevo archivo de texto: ");
            System.out.println("[2] Trabajar con los datos del sistema ");
            int lecturaONo = getInt("Ingrese una opción: ", "Intente con numeros válidos");
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
                    if(serializer.read(ruta) == null){
                        System.out.println("OJO: ¡¡No existe el archivo de configuración!!");
                        System.out.println("Intente de nuevo");
                    }else{
                        db = (BaseDeDatos) serializer.read(ruta);
                        primeraPregunta = false;
                    }
                    break;
                default:
                    System.out.println("Intente de nuevo con alguna opcion valida");
            }

        }while (primeraPregunta);
        boolean continuar = true;
        do{
            System.out.println("Estas son las opciones: ");
            System.out.println("[1] Materias a las que esta inscrito un alumno");
            System.out.println("[2] Alumnos inscritos en una materia");
            System.out.println("[3] Añadir profesor y clave de materias");
            System.out.println("[4] Salir");
            int respues = getInt("Seleccione una opción: ", "Error. Intente de nuevo");
            switch (respues){
                case 1:
                    String nombre = getStr("Ingrese el nombre a consultar: ", "Error, solo ingrese letras");
                    Alumno busqueda = db.buscaAlumnos(nombre);
                    if (busqueda != null ){
                        System.out.println(busqueda);
                        System.out.println("\nInscrito en las siguientes materias:\n");
                        System.out.println("_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_\n");
                        System.out.println(busqueda.getMaterias());
                        System.out.println("_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_");
                    }else {
                        System.out.println("No fue encontrado el estudiante: " + nombre);
                    }
                    break;
                case 2:
                    String mat = getStr("Ingrese el nombre de la materia", "Error, solo ingrese letras");
                    Materia busquedaMat = db.buscaMateria(mat);
                    if (busquedaMat != null){
                        System.out.println(busquedaMat);
                        System.out.println(busquedaMat.listaInscritos());
                    }else {
                        System.out.println("No fue encontrada la materia " + mat);
                    }
                    break;
                case 3:
                    String laMateriaAAsignar = getStr("Ingrese la materia a asignar: ", "Error, intente de nuevo");
                    Materia buscaAgregar = db.buscaMateria(laMateriaAAsignar);
                    if (buscaAgregar != null){
                        int clav = getInt("Ingrese la clave de la materia", "Ingrese un  valor válido");
                        String  prof = getStr("Ingrese el nombre de la profesora(o): ", "Error, ingrese solo letras");
                        db.asignarClaveProfe(buscaAgregar, prof,clav);
                    }else {
                        System.out.println("NO hay resultados para " + laMateriaAAsignar);
                    }
                    break;
                case 4:
                    serializer.write(db,ruta);
                    continuar = false;
                    System.out.println("Hasta luego");
                    System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
                    break;
                default:
                    System.out.println("Error, seleccione una opción válida");
            }

        }while (continuar);

    }
}
