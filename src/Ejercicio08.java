import java.io.*;
import java.time.LocalDate;
import java.time.Month;

public class Ejercicio08 {

//     8 Queremos hacer una agenda telefónica con los siguientes datos:
//            • Nombre del contacto
//            • Teléfono
//            • Dirección
//            • Código postal (número entero)
//            • Fecha de nacimiento
//            • Si le debo dinero (booleano)
//            • Cuánto le debo(número decimal)
        //    Realiza un programa que almacene los datos en un fichero binario. A continuación, lee el fichero y
        //    muestra el contenido por consola. Hacer dos versiones:
//            • Sin serialización de objetos
//            • Con serialización de objetos

    public static void main(String[] args) throws IOException {

        //Contacto 1.
        String name ="Alejandro Segura";
        int phoneNumber = 615101488;
        String address = "Vicente Alexandre";
        int postalCode = 11204;
        LocalDate birthdate = LocalDate.of(1997, Month.JANUARY,6);
        boolean hasDebts = true;
        double debts = 57.32;

        //Contanto 2.
        String name2 ="qwqwertyuiopertyuiopoiuytrewqé";
        int phoneNumber2 = 636932813;
        String address2 = "Avenida Vistamar";
        int postalCode2 = 00000;
        LocalDate birthdate2 = LocalDate.of(1998, Month.MARCH,26);
        boolean hasDebts2 = false;
        double debts2 = 0;

        DataOutputStream myWriter=new DataOutputStream(new FileOutputStream("ejercicio8.txt"));
        DataInputStream myReader=new DataInputStream(new FileInputStream("ejercicio8.txt"));

        myWriter.writeUTF(name);
        myWriter.writeInt(phoneNumber);
        myWriter.writeUTF(address);
        myWriter.writeInt(postalCode);
        myWriter.writeUTF(birthdate.toString());
        myWriter.writeBoolean(hasDebts);
        myWriter.writeDouble(debts);

        myWriter.writeUTF(name2);
        myWriter.writeInt(phoneNumber2);
        myWriter.writeUTF(address2);
        myWriter.writeInt(postalCode2);
        myWriter.writeUTF(birthdate2.toString());
        myWriter.writeBoolean(hasDebts2);
        myWriter.writeDouble(debts2);

        myWriter.close();

        try {
            while (true) { //lectura del fichero
                System.out.printf("Nombre: %s%n",myReader.readUTF());
                System.out.printf("Numero de teléfono: %d%n",myReader.readInt());
                System.out.printf("Dirección: %s%n",myReader.readUTF());
                System.out.printf("Codigo Postal: %d%n",myReader.readInt());
                System.out.printf("Fecha de nacimiento: %s%n",myReader.readUTF());
                System.out.printf("Tiene deudas: %s%n",myReader.readBoolean()?"Si":"No");
                System.out.printf("Deudas: %.2f%n",myReader.readDouble());
                System.out.println();
            }
        }catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        }

        myReader.close();


    }

}
