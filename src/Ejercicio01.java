
//1 Realiza un programa que dado un fichero que se le solicite al usuario, muestre su nombre, si
//  es un ejecutable, si está oculto, la ruta relativa, la ruta absoluta y el tamaño.

import java.io.File;
import java.io.IOException;

public class Ejercicio01 {

    public static void main(String[] args) throws IOException {

        System.out.print("Introduce el nombre de un archivo (Pista: ejercicio1.txt): ");
        File archivo = new File(new File("."),Teclado.leerString());

        System.out.printf("Nombre: %s%nEs ejecutable: %b%nEsta oculto: %b%nRuta relativa: %s%nRuta absoluta: %s%nTamaño: %d%n",archivo.getName(),archivo.canExecute(),archivo.isHidden(),archivo.getPath(),archivo.getAbsolutePath(),archivo.length());

    }
}
