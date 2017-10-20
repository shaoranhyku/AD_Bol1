import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio07 {

//     7 Diseñar un programa para encriptar y desencriptar los datos de un fichero de texto. Se
//    introduce una cadena por teclado que será la clave a aplicar para la encriptación y desencriptación.
//    A cada carácter del fichero de texto original se le sumará una letra de la clave, cuando se hayan
//    acabado las letras de la palabra clave y aún no se hayan acabado los caracteres del fichero, se
//    volverá al principio de la cadena para seguir aplicando la encriptación. Los datos encriptados se
//    escribirán en un fichero destino, que será usado como origen para desencriptar. Para desencriptar se
//    aplicará la fórmula a la inversa. Por ejemplo, si el fichero origen contiene ?abcdef? y la palabra
//    clave es ?rosa?, en el fichero destino se escribirán los caracteres correspondientes a: ?a+r b+o c+s
//    d+a e+r f+o?.

    public static void main(String[] args) throws IOException {

        boolean finished = false;
        int charKeyCount = 0, iCharFile, iCharKey;
        String key;
        File oFile, encryptedFile, decryptedFile;
        FileReader oFileReader, encryptedFileReader;
        FileWriter encryptedFileWriter, decryptedFileWriter;

        System.out.print("Introduce la ruta del archivo a encriptar: ");
        oFile = new File(Teclado.leerString());
        System.out.print("Introduce la clave para la encriptación: ");
        key = Teclado.leerString();

        encryptedFile = new File(("encriptado_" + oFile.getName()));
        decryptedFile = new File(("desencriptado_" + oFile.getName()));
        try {
            encryptedFile.createNewFile();
            decryptedFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        oFileReader = new FileReader(oFile);
        encryptedFileWriter = new FileWriter(encryptedFile);

        while (!finished) {

            //Obtengo la primera letra de la clave, y en caso de llegar a la ultima, vuelvo a la primera.
            if (!(charKeyCount < key.length())) {
                charKeyCount = 0;
            }
            iCharKey = key.charAt(charKeyCount);
            charKeyCount++;

            if ((iCharFile = oFileReader.read()) != -1) {
                encryptedFileWriter.write((char) (iCharFile + iCharKey));
            } else {
                finished = true;
            }

        }

        oFileReader.close();
        encryptedFileWriter.close();

        finished = false;
        charKeyCount = 0;

        encryptedFileReader = new FileReader(encryptedFile);
        decryptedFileWriter = new FileWriter(decryptedFile);


        while (!finished) {

            //Obtengo la primera letra de la clave, y en caso de llegar a la ultima, vuelvo a la primera.
            if (!(charKeyCount < key.length())) {
                charKeyCount = 0;
            }
            iCharKey = key.charAt(charKeyCount);
            charKeyCount++;

            if ((iCharFile = encryptedFileReader.read()) != -1) {
                decryptedFileWriter.write((char) (iCharFile - iCharKey));
            } else {
                finished = true;
            }

        }

        encryptedFileReader.close();
        decryptedFileWriter.close();

    }


}
