package com.util;

import java.util.Scanner;

public class Utilidades {
    /**
     * Este método sirve para controlar que en las entradas de enteros
     * lo único que se pueda ingresar sean justo sólo valores numéricos y nada de cadenas
     *
     * @param msg mensaje de instrucciones al usuario o indicaciones
     * @param error mensaje de error al detectar que la entrada no es un valor nummérico
     * @return entero que validó y ahora puede ser utilizado
     */
    public static int getInt(String msg, String error){
        int entero = 0;
        Scanner scan = new Scanner(System.in);
        String librearBuffer;
        boolean conti = true;
        do{
            System.out.println(msg);
            if(scan.hasNextInt())
            {

                entero = scan.nextInt();
                if (entero > 0){
                    conti = false;
                }
            }else{
                librearBuffer = scan.next();
                System.out.println(error);
            }
        }while(conti);
        return entero;
    }

    public static String getStr(String mensaje, String error){
        Scanner sc = new Scanner(System.in);
        String cadena = null;
        boolean continuar = false;
        do {
            System.out.println(mensaje);
            cadena = sc.nextLine().replaceAll("[^A-Za-z ]", "");
            if (cadena.equals("")){
                continuar = true;
                System.out.println(error);
            }else{
                return cadena;
            }
        }while (continuar);
        return cadena;
    }

}
