import java.io.*;

public class Ejercicio05 {

//     5 Realiza un programa que dadas dos rutas, origen y destino, copie el archivo origen en el
//    destino. El archivo origen es un fichero de texto y la copia se hará línea a línea (una línea se
//    considera hasta que se encuentre un salto de línea) . Si el destino es un directorio, se copiará el
//    archivo en dicho directorio, y si es un archivo, habrá varias opciones según un booleano:
//            • Si el booleano es verdadero y el destino es un archivo existente, se reemplazará por el
//              archivo origen.
//            • Si el booleano es verdadero y el destino es un archivo inexistente, no se hará nada.
//            • Si el booleano es falso y el destino es un archivo existente, se lanzará una excepción.
//            • Si el booleano es falso y el destino es un archivo inexistente, no se hará nada.

    public static void main(String[] args) throws Exception {

        boolean overwriteMode, noExist = false;
        String line;
        File oPath, dPath, newFile;
        BufferedReader fileReader = null;
        BufferedWriter fileWriter = null;

        System.out.print("Introduzca la ruta de origen del fichero: ");
        oPath = new File(Teclado.leerString());
        System.out.print("Introduzca la ruta del destino: ");
        dPath = new File(Teclado.leerString());
        overwriteMode = Teclado.leerBoolean("¿Desea activar el modo sobre escritura?", "Sí.", "No.");

        if (!oPath.exists() || oPath.isDirectory()) {

            System.out.println("El archivo de origen no existe.");

        } else {

            try {
                fileReader = new BufferedReader(new FileReader(oPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (dPath.isDirectory()) {

                try {
                    newFile = new File(dPath, oPath.getName());
                    newFile.createNewFile();
                    fileWriter = new BufferedWriter(new FileWriter(newFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {

                if (dPath.exists()) {
                    if (!overwriteMode) {
                        throw new Exception("La ruta de destino es un archivo existente y no está activado el modo sobre escritura.");
                    } else {
                        try {
                            fileWriter = new BufferedWriter(new FileWriter(dPath));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    noExist = true;
                }

            }

            if (!noExist) {
                while ((line = fileReader.readLine()) != null) {
                    fileWriter.write(line);
                    fileWriter.newLine();
                }
                fileWriter.close();
            }else{
                System.out.println("El archivo de destino no existe.");
            }

            fileReader.close();

        }


    }
}
