import com.dbcc.Materia;

public class Main {
    public static void main(String[] args) {
        Reading al = new Reading();
        al.leyendo();
        System.out.println(al.getAlumnos());
        Materia logicasNoClasicas =  al.asignaAlumnos("Logicas no clasicas","Ariel Merino",57841);
        //System.out.println("Estos son alumnos inscritos en Logicas no clasicas\n" + logicasNoClasicas);
        //System.out.println(logicasNoClasicas.listaInscritos());
        //al.leeMaterias();
    }
}
