import java.io.*;
import java.time.LocalDate;
import java.time.Month;

public class Ejercicio09UTF {

//    9.Partiendo del fichero binario del ejercicio anterior, crear un fichero de acceso aleatorio
//    donde vamos a poder hacer lo siguiente (el identificador empieza en 1):
//            • Consultar el fichero entero (hacerlo de manera secuencial, no con el seek) (mostrarle al
//              usuario los identificadores)
//            • Consultar un contacto (pedirle al usuario el identificador)
//            • Añadir un contacto
//                ➢ Por el final
//                ➢ En la primera posición libre
//            • Eliminar un contacto (considerar baja lógica) (pedirle al usuario el identificador)
//            • Modificar si le debo dinero y la cantidad (pedirle al usuario el identificador)
//            • Compactación del fichero

    final static int LONGITUD_CONTACTO = 98, POSICION_ALTA = 97, POSICION_DEUDAS = 88;

    public static void main(String[] args) throws IOException {

        int i = 1, opcion, posicionVacia;
        boolean terminado = false, deudas;
        DataInputStream binaryReader = null;
        RandomAccessFile randomFile = null;
        String buffer;

        try {
            binaryReader = new DataInputStream(new FileInputStream("ejercicio8.txt"));
            randomFile = new RandomAccessFile("ejercicio9UTF.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Comienzo copiando el fichero binario en el fichero de acceso aleatorio.

        try {
            while (true) {
                //Antes de asignar un identificador leo un dato para no escribir un identificador antes de saber que he acabado el archivo.
                buffer = binaryReader.readUTF();

                //Identificador 4 bytes
                randomFile.writeInt(i);

                //Nombre 32 bytes TOTAL 36 bytes
                randomFile.writeUTF(padRight(buffer,30));

                //Numero de teléfono 4 bytes TOTAL 40 bytes
                randomFile.writeInt(binaryReader.readInt());

                //Dirección 32 bytes TOTAL 72 bytes
                buffer = binaryReader.readUTF();
                randomFile.writeUTF(padRight(buffer,30));

                //Codigo Postal 4 bytes TOTAL 76 bytes
                randomFile.writeInt(binaryReader.readInt());

                //Fecha nacimiento 12 bytes TOTAL 88 bytes
                buffer = binaryReader.readUTF();
                randomFile.writeUTF(padRight(buffer,10));

                //Tiene deudas 1 byte TOTAL 89 bytes
                randomFile.writeBoolean(binaryReader.readBoolean());

                //Cantidad de deudas 8 bytes TOTAL 97 bytes
                randomFile.writeDouble(binaryReader.readDouble());

                //Dado de alta 1 byte TOTAL 98 bytes
                randomFile.writeBoolean(true);

                i++;
            }
        } catch (EOFException eo) {

        }


        binaryReader.close();


        while (!terminado) {
            System.out.printf("Seleccione que acción desea hacer:%n1.- Consultar el fichero entero%n2.- Consultar un contacto%n3.- Añadir un contanto%n4.- Eliminar un contacto%n5.- Modificar deudas%n6.- Compactar fichero%n");
            opcion = Teclado.leerEntre(1, 6, Teclado.Incluido.TODOS, Teclado.Tipos.INT);

            switch (opcion) {
                case 1:
                    if (randomFile.length() > 0) {
                        showAllUsers(randomFile);
                        System.out.println("Lista mostrada.\n");
                    } else {
                        System.out.println("No existen contactos.\n");
                    }


                    break;
                case 2:
                    if (randomFile.length() > 0) {
                        System.out.printf("Introduce el número del contacto: ");
                        opcion = Teclado.leerEntre(1, (int) (randomFile.length() / LONGITUD_CONTACTO), Teclado.Incluido.TODOS, Teclado.Tipos.INT);
                        //Primero validamos que el contacto introducido no está dado de baja.
                        randomFile.seek(POSICION_ALTA + (LONGITUD_CONTACTO * (opcion - 1)));
                        if (randomFile.readBoolean()) {
                            System.out.println();
                            showUser(randomFile, opcion);
                            System.out.println("\nUsuario mostrado.\n");
                        } else {
                            System.out.printf("El contacto %d está dado de baja.\n\n", opcion);
                        }
                    } else {
                        System.out.println("No existen contactos.\n");
                    }


                    break;
                case 3:
                    System.out.printf("Selecciona como desea introducir el nuevo usuario:%n1.- Al final%n2.- En la primera posición libre%n");
                    opcion = Teclado.leerEntre(1, 2, Teclado.Incluido.TODOS, Teclado.Tipos.INT);
                    switch (opcion) {
                        case 1:
                            //Lo posiciono al final.
                            addUser(randomFile);
                            System.out.println("Usuario creado.\n");
                            break;
                        case 2:
                            if (randomFile.length() > 0) {
                                posicionVacia = -1;
                                //Busco en todos los valores de alta hasta encontrar uno que sea falso.
                                for (i = POSICION_ALTA; i <= randomFile.length() - 1; i += LONGITUD_CONTACTO) {
                                    randomFile.seek(i);
                                    if (!randomFile.readBoolean() && posicionVacia == -1) {
                                        posicionVacia = i;
                                    }
                                }
                                if (posicionVacia == -1) {
                                    System.out.println("No hay posiciones vacías.\n");
                                } else {
                                    addUser(randomFile, posicionVacia - POSICION_ALTA);
                                    System.out.println("\nUsuario creado.\n");
                                }
                            } else {
                                System.out.println("No existen posiciones libres porque el fichero está vacío.\n");
                            }
                            break;
                    }


                    break;
                case 4:
                    if (randomFile.length() > 0) {
                        System.out.printf("Introduce el número del contacto: ");
                        opcion = Teclado.leerEntre(1, (int) (randomFile.length() / LONGITUD_CONTACTO), Teclado.Incluido.TODOS, Teclado.Tipos.INT);
                        //Primero validamos que el contacto introducido no está dado de baja.
                        randomFile.seek(POSICION_ALTA + (LONGITUD_CONTACTO * (opcion - 1)));
                        if (randomFile.readBoolean()) {
                            System.out.println();
                            deleteUser(randomFile, opcion);
                            System.out.println("\nUsuario eliminado.\n");
                        } else {
                            System.out.printf("El contacto %d ya está dado de baja.\n\n", opcion);
                        }
                    } else {
                        System.out.println("No existen contactos.\n");
                    }


                    break;
                case 5:
                    if (randomFile.length() > 0) {
                        System.out.printf("Introduce el número del contacto a modificar: ");
                        opcion = Teclado.leerEntre(1, (int) (randomFile.length() / LONGITUD_CONTACTO), Teclado.Incluido.TODOS, Teclado.Tipos.INT);
                        //Primero validamos que el contacto introducido no está dado de baja.
                        randomFile.seek(POSICION_ALTA + (LONGITUD_CONTACTO * (opcion - 1)));
                        if (randomFile.readBoolean()) {
                            modifyUser(randomFile, opcion);
                            System.out.println("Usuario modificado.\n");
                        } else {
                            System.out.printf("El contacto %d está dado de baja.\n\n", opcion);
                        }
                    } else {
                        System.out.println("No existen contactos.\n");
                    }


                    break;
                case 6:
                    randomFile = cleanFile(randomFile);
                    break;
            }

        }

    }

    private static void modifyUser(RandomAccessFile randomFile, int opcion) throws IOException {
        boolean deudas;
        randomFile.seek((LONGITUD_CONTACTO * (opcion - 1)) + POSICION_DEUDAS);
        deudas = Teclado.leerBoolean("¿Quieres que el usuario tenga deudas?", "Sí.", "No.");
        randomFile.writeBoolean(deudas);
        if (deudas) {
            System.out.print("Introduce la cantidad que debe: ");
            randomFile.writeDouble(Teclado.leerComparacion(Teclado.Comparacion.MAYOR, Teclado.Tipos.DOUBLE, (double) 0));
        } else {
            randomFile.writeDouble(0);
        }
    }

    private static void deleteUser(RandomAccessFile randomFile, int opcion) throws IOException {
        randomFile.seek(POSICION_ALTA + (LONGITUD_CONTACTO * (opcion - 1)));
        randomFile.writeBoolean(false);
    }

    private static void showAllUsers(RandomAccessFile randomFile) throws IOException {
        System.out.println(randomFile.length());
        randomFile.seek(0);
        while (randomFile.getFilePointer() != randomFile.length()) {
            long aux;

            //Antes de mostrar, compruebo que no este dado de baja para mostrarlo.

            aux = randomFile.getFilePointer();
            randomFile.seek(aux + POSICION_ALTA);

            if (randomFile.readBoolean()) {
                //Una vez comprobado, vuelvo a la posición correspondiente.
                randomFile.seek(aux);

                System.out.printf("Identificador: %d%n", randomFile.readInt());
                System.out.print("Nombre: ");
                System.out.print(randomFile.readUTF());
                System.out.println();
                System.out.printf("Numero de teléfono: %d%n", randomFile.readInt());
                System.out.print("Dirección: ");
                System.out.print(randomFile.readUTF());
                System.out.println();
                System.out.printf("Codigo Postal: %d%n", randomFile.readInt());
                System.out.print("Fecha de nacimiento: ");
                System.out.print(randomFile.readUTF());
                System.out.println();
                System.out.printf("Tiene deudas: %s%n", randomFile.readBoolean() ? "Si" : "No");
                System.out.printf("Deudas: %.2f%n", randomFile.readDouble());

                // FOR TESTING PURPOSES
                //System.out.println(randomFile.readBoolean());

                randomFile.readBoolean();

                System.out.println(randomFile.getFilePointer());

                System.out.println();
            } else {
                //En caso de que esté dado de baja, paso al siguiente contacto.
                randomFile.seek(aux + LONGITUD_CONTACTO);
            }

        }
    }

    private static void addUser(RandomAccessFile randomFile) throws IOException {
        addUser(randomFile, (int) randomFile.length());
    }

    private static void addUser(RandomAccessFile randomFile, int posicion) throws IOException {
        String buffer;
        int anio;
        int mes;
        int dia;
        boolean deudas;

        randomFile.seek(posicion);

        randomFile.writeInt((int) Math.floor((randomFile.getFilePointer() / LONGITUD_CONTACTO)) + 1);

        System.out.print("Introduce el nombre (máximo 30 caracteres): ");
        buffer = Teclado.leerString();
        randomFile.writeUTF(padRight(buffer,30));

        System.out.print("Introduce el número de teléfono: ");
        randomFile.writeInt(Teclado.leerNumero(Teclado.Tipos.INT));

        System.out.print("Introduce la dirección (máximo 30 caracteres): ");
        buffer = Teclado.leerString();
        randomFile.writeUTF(padRight(buffer,30));

        System.out.print("Introduce el código postal: ");
        randomFile.writeInt(Teclado.leerNumero(Teclado.Tipos.INT));

        System.out.print("Introduce el año de nacimiento: ");
        anio = Teclado.leerComparacion(Teclado.Comparacion.MAYOR, Teclado.Tipos.INT, 0);
        System.out.print("Introduce el mes: ");
        mes = Teclado.leerEntre(1, 12, Teclado.Incluido.TODOS, Teclado.Tipos.INT);
        System.out.print("Introduce el día: ");
        dia = Teclado.leerEntre(1, Month.of(mes).maxLength(), Teclado.Incluido.TODOS, Teclado.Tipos.INT);
        buffer = LocalDate.of(anio, mes, dia).toString();
        randomFile.writeUTF(padRight(buffer,10));

        deudas = Teclado.leerBoolean("¿Tiene deudas?", "Si", "No");
        randomFile.writeBoolean(deudas);
        if (deudas) {
            System.out.print("Introduce la cantidad que debe: ");
            randomFile.writeDouble(Teclado.leerComparacion(Teclado.Comparacion.MAYOR, Teclado.Tipos.DOUBLE, (double) 0));
        } else {
            randomFile.writeDouble(0);
        }
        randomFile.writeBoolean(true);
    }

    private static void showUser(RandomAccessFile randomFile, int userPosition) throws IOException {

        long aux;

        randomFile.seek((userPosition - 1) * LONGITUD_CONTACTO);

        //Antes de mostrar, compruebo que no este dado de baja para mostrarlo.

        aux = randomFile.getFilePointer();
        randomFile.seek(aux + POSICION_ALTA);

        if (randomFile.readBoolean()) {
            //Una vez comprobado, vuelvo a la posición correspondiente.
            randomFile.seek(aux);

            System.out.printf("Identificador: %d%n", randomFile.readInt());
            System.out.print("Nombre: ");
            System.out.print(randomFile.readUTF());
            System.out.println();
            System.out.printf("Numero de teléfono: %d%n", randomFile.readInt());
            System.out.print("Dirección: ");
            System.out.print(randomFile.readUTF());
            System.out.println();
            System.out.printf("Codigo Postal: %d%n", randomFile.readInt());
            System.out.print("Fecha de nacimiento: ");
            System.out.print(randomFile.readUTF());
            System.out.println();
            System.out.printf("Tiene deudas: %s%n", randomFile.readBoolean() ? "Si" : "No");
            System.out.printf("Deudas: %.2f%n", randomFile.readDouble());

            // FOR TESTING PURPOSES
            //System.out.println(randomFile.readBoolean());

            randomFile.readBoolean();

            System.out.println(randomFile.getFilePointer());

            System.out.println();
        } else {
            //En caso de que esté dado de baja, paso al siguiente contacto.
            randomFile.seek(aux + LONGITUD_CONTACTO);
        }

    }

    private static RandomAccessFile cleanFile(RandomAccessFile randomFile) throws IOException {

        long aux;

        randomFile.seek(0);

        //Creo un archivo nuevo donde iré copiando todos los datos.
        RandomAccessFile cleanRandomFile = new RandomAccessFile("ejercicio9utfcompacto.txt", "rw");

        while (randomFile.getFilePointer() != randomFile.length()) {

            //Primero, compruebo que ese usuario no está eliminado.

            aux = randomFile.getFilePointer();
            randomFile.seek(aux + POSICION_ALTA);

            if (randomFile.readBoolean()) {
                //Una vez comprobado, vuelvo a la posición correspondiente y comienzo a copiar.
                randomFile.seek(aux);

                cleanRandomFile.writeInt((int) Math.floor((cleanRandomFile.getFilePointer() / LONGITUD_CONTACTO)) + 1);
                randomFile.readInt();
                cleanRandomFile.writeUTF(randomFile.readUTF());
                cleanRandomFile.writeInt(randomFile.readInt());
                cleanRandomFile.writeUTF(randomFile.readUTF());
                cleanRandomFile.writeInt(randomFile.readInt());
                cleanRandomFile.writeUTF(randomFile.readUTF());
                cleanRandomFile.writeBoolean(randomFile.readBoolean());
                cleanRandomFile.writeDouble(randomFile.readDouble());
                cleanRandomFile.writeBoolean(randomFile.readBoolean());

            } else {
                //En caso de que esté dado de baja, paso al siguiente contacto.
                randomFile.seek(aux + LONGITUD_CONTACTO);
            }

        }

        randomFile.close();
        cleanRandomFile.close();

        new File("ejercicio9UTF.txt").delete();
        new File("ejercicio9UTFcompacto.txt").renameTo(new File("ejercicio9UTF.txt"));

        System.out.println("Fichero compactado.\n");

        return cleanRandomFile = new RandomAccessFile("ejercicio9UTF.txt", "rw");
    }

    public static String padRight(String s, int n) {
        try {
            while(s.getBytes("UTF-8").length>n){
                s=s.substring(0,s.length()-1);
            }
            n=n-(s.getBytes("UTF-8").length-s.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String formarString = String.format("%-" + n + "s", s);
        return formarString;
    }

}
