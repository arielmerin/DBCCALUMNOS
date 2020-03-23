import com.dbcc.Alumno;
import com.dbcc.Materia;
import com.util.Lista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Reading {
    private static Lista<Alumno> alumnos = new Lista<>();
    private static int contador = alumnos.longitud();

    public void leyendo(){
        try {
            Scanner input = new Scanner(new File("src/alumnos.txt"));
            while (input.hasNextLine()) {
                /**
                 * Este while es para cada alumno (bueno cada linea del txt)
                 */
                String line = input.nextLine();
                String[] casit = line.split(",");
                String name = casit[0].substring(1);
                String materia = casit[1];
                materia = materia.substring(1,materia.length()-1);
                Alumno nuevo = new Alumno(name, materia, ++contador);
                alumnos.agregar(nuevo);
            }
            input.close();
        } catch (FileNotFoundException e){
            System.out.println("NO se encontro el archivo");
        } catch (IOException e){
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println("El documento no se pudo abrir, intente de nuevo");
        }
    }
    public Lista<Alumno> getAlumnos(){
        return alumnos;
    }

    public Materia asignaAlumnos(String nombreMateria, String profesora, int clave){
        Materia materia = new Materia(nombreMateria, profesora, clave);
        for (Alumno al: alumnos) {
            if (al.getMateria().equals(nombreMateria)){
                materia.agregarAlumno(al);
            }
        }
        return materia;
    }
}
