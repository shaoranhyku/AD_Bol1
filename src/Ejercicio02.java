import java.io.File;
import java.io.IOException;

public class Ejercicio02 {

//     2 Realiza un programa que cree un directorio en el directorio actual, luego cree tres ficheros
//    en dicho directorio donde uno se borre y otro se renombre. Crearle también un subdirectorio con un
//    fichero dentro. Después mostrar la ruta absoluta de ambos directorios y sus contenidos.

    public static void main(String[] args) {

        int i;
        File newPath = new File("./nueva_ruta");
        newPath.mkdir();

        File archivo1=new File(newPath,"archivo1.txt");
        File archivo2=new File(newPath,"archivo2.txt");
        File archivo3=new File(newPath,"archivo3.txt");

        try {
            archivo1.createNewFile();
            archivo2.createNewFile();
            archivo3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        archivo1.renameTo(new File(newPath,"archivo1renombrado.txt"));
        archivo2.renameTo(new File(newPath,"archivo2renombrado.txt"));
        archivo3.delete();

        File anotherPath=new File(newPath,"otra_ruta");
        anotherPath.mkdir();
        File archivo4=new File(anotherPath,"archivo4.java");



        try {
            archivo4.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Ruta del primer directorio: %s%nContenido:%n",newPath.getAbsolutePath());
        String[] archivosRuta1=newPath.list();
        for(i=0;i<archivosRuta1.length;i++){
            System.out.println(archivosRuta1[i]);
        }

        System.out.printf("Ruta del segundo directorio: %s%nContenido:%n",anotherPath.getAbsolutePath());
        String[] archivosRuta2=anotherPath.list();
        for(i=0;i<archivosRuta2.length;i++){
            System.out.println(archivosRuta2[i]);
        }
    }
}
