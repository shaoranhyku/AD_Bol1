import java.io.*;

public class Ejercicio06 {

//     6 Realiza un programa que dado un fichero de texto, se copie en tres ficheros diferentes de tal
//    manera que copie en el primer fichero los primeros 5 caracteres, en el segundo, los 5 siguientes y
//    en el tercero los 5 siguientes, y así sucesivamente hasta copiar todo el fichero. Después, hacer
//    justamente lo contrario. Dados los 3 ficheros, construir el fichero original. Comprobar que ambos
//    son iguales. Utilizar para el ejercicio buffer con desplazamiento.

    public static void main(String[] args) {

        int charCountRead, charCountWrite, aux = 0, i, icheckFile1 = 0, icheckFile2 = 0;
        boolean finished = false, isEqual = true;
        FileReader oFile = null;
        FileWriter dFile = null;
        FileWriter dFile1, dFile2, dFile3;
        FileReader oFile1, oFile2, oFile3, checkFile1 = null, checkFile2 = null;
        final int IFILES = 3, BUFFERLENGHT = 15, COPYLENGHT = 5;
        FileWriter[] subFilesWrite = new FileWriter[3];
        FileReader[] subFilesRead = new FileReader[3];

        char[] buffer = new char[BUFFERLENGHT];
        char[] buffer2;

        try {
            oFile = new FileReader("ejercicio6.txt");
            dFile1 = new FileWriter("ejercicio6-1.txt");
            dFile2 = new FileWriter("ejercicio6-2.txt");
            dFile3 = new FileWriter("ejercicio6-3.txt");
            subFilesWrite[0] = dFile1;
            subFilesWrite[1] = dFile2;
            subFilesWrite[2] = dFile3;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Mientras queden caracteres por leer.
        while (!finished) {

            charCountRead = 0;

            //Copio 15 caracteres del archivo original en el buffer.
            try {
                charCountRead = oFile.read(buffer, charCountRead, BUFFERLENGHT);
            } catch (IOException e) {
                e.printStackTrace();
            }

            charCountWrite = 0;

            if (charCountRead != -1) {

                for (i = 0; i < IFILES; i++) {
                    aux = 0;

                    //Compruebo si puedo copiar 5 caracteres del buffer para no copiar caracteres antiguos
                    if (charCountRead > COPYLENGHT) {
                        charCountRead -= COPYLENGHT;
                        aux = COPYLENGHT;
                    } else {
                        aux = charCountRead;
                        charCountRead = 0;
                    }

                    try {
                        subFilesWrite[i].write(buffer, charCountWrite, aux);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    charCountWrite += aux;

                }
            }

            //Si he escrito menos de 15 caracteres es que el buffer contenia caracteres que no debía de copiar, y por lo tanto el archivo
            // original no contenia más caracteres nuevos, así que he terminado el archivo.
            if (charCountWrite < buffer.length) finished = true;
        }

        finished = false;

        //Cierro los archivos que no usaré, para guardar los cambios y ahorrar consumo.
        try {
            oFile.close();
            for (i = 0; i < IFILES; i++) {
                subFilesWrite[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dFile = new FileWriter("ejercicio6final.txt");
            oFile1 = new FileReader("ejercicio6-1.txt");
            oFile2 = new FileReader("ejercicio6-2.txt");
            oFile3 = new FileReader("ejercicio6-3.txt");
            subFilesRead[0] = oFile1;
            subFilesRead[1] = oFile2;
            subFilesRead[2] = oFile3;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Mientras pueda copiar caracteres de alguno de los 3 archivos.
        while (!finished) {
            charCountRead = 0;
            for (i = 0; i < IFILES; i++) {
                try {
                    //Guardo cuantos caracteres he copiado.
                    aux = subFilesRead[i].read(buffer, charCountRead, COPYLENGHT);
                    //En caso de haber terminado el archivo, la variable auxiliar que iba contanto cuantos caracteres valdrá -1.
                    //Con el fin de no perder información, cuando valga -1 no se sumará a la variable que cuenta cuantos caracteres he copiado.
                    if (aux > 0) charCountRead += aux;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Escribo en el archivo final todos los caracteres que he copiado entre los 3 archivos anteriores.
            try {
                //Si he copiado en el buffer pero no lo he rellenado, se creará otro buffer que contendrá los caracteres que deseamos copiar.
                if (charCountRead < buffer.length) {
                    buffer2 = new char[charCountRead];
                    System.arraycopy(buffer, 0, buffer2, 0, buffer2.length);
                    dFile.write(buffer2);
                    finished = true;
                } else {
                    dFile.write(buffer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //Cierro el resto de archivos para guardar cambios y terminar el programa.
        try {
            dFile.close();
            for (i = 0; i < IFILES; i++) {
                subFilesRead[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Compruebo que ambos archivos contiene los mismos caracteres.
        try {
            checkFile1 = new FileReader("ejercicio6.txt");
            checkFile2 = new FileReader("ejercicio6final.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        do {
            try {
                icheckFile1 = checkFile1.read();
                icheckFile2 = checkFile2.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (icheckFile1 != icheckFile2) {
                isEqual = false;
            }

        } while (icheckFile1 > 0 && icheckFile2 > 0);

        if (isEqual) {
            System.out.println("Proceso realizado correctamente.");
        } else {
            System.out.println("Fallo en el proceso.");
        }


    }

}
