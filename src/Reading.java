import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reading {
    public void leyendo(){
        Pattern regex = Pattern.compile(",");
        Matcher mat = regex.matcher("");
        try {
            Scanner input = new Scanner(new File("src/alumnos.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine()W;
                System.out.println(line);
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
