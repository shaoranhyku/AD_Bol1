import java.io.File;

public class Ejercicio03 {

//     3 Realiza un programa que muestre el nombre y tipo (fichero o directorio) de los ficheros y
//    subdirectorios contenidos en un directorio solicitado al usuario. Mostrar también el contenido de
//    todos los subdirectorios y si éstos contienen subdirectorios también...y así sucesivamente hasta
//    mostrar todo el contenido de dicho directorio.

    public static void main(String[] args) {

        System.out.print("Introduce un directorio: ");
        File directorio = new File(Teclado.leerString());
        if(directorio.exists())
            showContent(directorio,0);
        else
            System.out.println("El directorio no existe.");

    }

    private static void showContent(File directorio, int deepLevel) {

        int i,j;
        String[] content=directorio.list();

        for(i=0;i<content.length;i++){
            for(j=0;j<deepLevel;j++){
                System.out.print("    ");
            }
            File archivoActual=new File(directorio,content[i]);
            System.out.printf("%s %s%n",content[i],archivoActual.isDirectory()?"Es un directorio":"Es un fichero");
            if(archivoActual.isDirectory())
                showContent(archivoActual,deepLevel+1);
        }

    }
}
