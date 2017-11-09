import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio16 {

//     16 Dado el fichero Json anterior, mostrar todos los datos de un contacto cuyo nombre nos
//    indique el usuario. La búsqueda se hará directamente en el fichero Json. No utilizar Gson.

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("Ejercicio14.json"));

        System.out.print("Introduce un nombre: ");
        String name = Teclado.leerString();
        System.out.println();
        Pattern patron = Pattern.compile("[{]\"name\":\"" + name + "\",\"phoneNumber\":(.*?),\"address\":\"(.*?)\",\"postalCode\":(.*?),\"birthdate\":[{]\"year\":(.*?),\"month\":(.*?),\"day\":(.*?)},\"hasDebts\":(.*?),\"debts\":(.*?)[}]");

        String json = "", aux;

        while ((aux = reader.readLine()) != null) {
            json += aux;
        }

        Matcher matcher = patron.matcher(json);

        boolean hasResult=false;
        while (matcher.find()) {
            hasResult=true;
            System.out.printf("Nombre: %s\n",name);
            System.out.printf("Número de teléfono: %s\n",matcher.group(1));
            System.out.printf("Dirección: %s\n",matcher.group(2));
            System.out.printf("Código postal: %s\n",matcher.group(3));
            System.out.printf("Fecha de nacimiento: %s/%s/%s\n",matcher.group(6),matcher.group(5),matcher.group(4));
            System.out.printf("Debe dinero: %s\n",matcher.group(7).equals("true")?"Sí":"No");
            System.out.printf("Cantidad: %s\n",matcher.group(8));
            System.out.println();
        }

        if(!hasResult){
            System.out.println("No se ha encontrado ningún usuario.");
        }
    }
}