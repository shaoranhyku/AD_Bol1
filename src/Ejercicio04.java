import java.io.File;
import java.io.FilenameFilter;

public class Ejercicio04 {

//    4 Un filtro sirve para que el método list devuelva solo aquellos archivos o carpetas que
//    cumplan una condición (que tengan una extensión determinada, contengan en su nombre una
//    cadena concreta, empiecen por un carácter, etc). Un filtro es un objeto de una clase que implementa
//    el interface FilenameFilter. Realiza un programa que muestre los archivos de un directorio que
//    posean una extensión concreta. Tanto la extensión como el directorio se solicita al usuario.

    public static void main(String[] args) {

        int i;

        class Filtro implements FilenameFilter {

            private String extension;

            public Filtro(String extension) {

                this.extension = "." + extension.toLowerCase();
            }

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(extension);
            }
        }

        System.out.print("Introduce un directorio: ");
        File path = new File(Teclado.leerString());
        System.out.print("Introduce una extensión (sin . ): ");
        String extension = Teclado.leerString();
        FilenameFilter filter = new Filtro(extension);

        if (path.exists()) {
            String[] content = path.list(filter);
            if (content.length > 0) {

                for (i = 0; i < content.length; i++) {
                    System.out.println(content[i]);
                }
            } else
                System.out.printf("El directorio no contiene ningún archivo con la extensión .%s", extension);
        } else
            System.out.println("La ruta no existe.");

    }

}
