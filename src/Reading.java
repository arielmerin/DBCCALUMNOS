import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reading {
    public void leyendo(){
        try {
            Scanner input = new Scanner(new File("src/alumnos.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] casit = line.split(",");
                for (int i = 0; i < casit.length; i++) {
                    casit[0] = casit[0].substring(1);
                    String ola = casit[1];
                    ola = ola.substring(1,ola.length()-1);
                    System.out.println("Elemento #" + i +" "+ (i == 0 ? casit[0] :ola ));
                }
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
