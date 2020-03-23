import com.dbcc.Alumno;
import com.dbcc.Materia;
import com.util.Lista;

import java.io.File;
import java.util.Scanner;


public class Reading {
    private static Lista<Alumno> alumnos = new Lista<>();
    private static int contador = alumnos.longitud();

    public void leyendo(){
        try {
            Scanner input = new Scanner(new File("src/alumnos.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] casit = line.split(",");
                for (int i = 0; i < casit.length; i++) {
                    String name = casit[0].substring(1);
                    String materia = casit[1];
                    materia = materia.substring(1,materia.length()-1);
                    Alumno nuevo = new Alumno(name, materia, ++contador);
                    alumnos.agregar(nuevo);
                }
            }
            input.close();
        } catch (Exception ex) {
            System.out.println("El documento no se pudo abrir, intente de nuevo");
        }
    }
    public Lista<Alumno> getAlumnos(){
        return alumnos;
    }

    public Materia asignaAlumnos(String nombreMateria, String profesor, int clave){
        Materia materia = new Materia(nombreMateria, profesor, clave);
        for (Alumno al: alumnos) {
            if (al.getMateria().equals(nombreMateria)){
                materia.agregarAlumno(al);
            }
        }
        return materia;
    }

    public void leeMaterias(){
        for (Alumno al : alumnos) {
            System.out.println(al.getMateria());
        }
    }
}
