import java.io.*;
import java.time.LocalDate;
import java.time.Month;

public class Ejercicio08_3 {

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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Contacto 1.
        PersonaAgenda persona1=new PersonaAgenda("Alejandro Segura", 615101488, "Vicente Alexandre",11204,LocalDate.of(1997, Month.JANUARY,6),true,57.32);
        PersonaAgenda persona2=new PersonaAgenda("Francisco Javier Florín",636932813,"Avenida Vistamar",00000,LocalDate.of(1998, Month.MARCH,26),false,0);


        ObjectOutputStream myWriter=new ObjectOutputStream(new FileOutputStream("ejercicio8_3.txt"));
        ObjectInputStream myReader=new ObjectInputStream(new FileInputStream("ejercicio8_3.txt"));

        myWriter.writeObject(persona1);

        myWriter.close();
        Ejercicio08_OutputStream myWriter2 = new Ejercicio08_OutputStream(new FileOutputStream("ejercicio8_3.txt",true));
        //ObjectOutputStream myWriter2=new ObjectOutputStream(new FileOutputStream("ejercicio8_3.txt",true));

        myWriter2.writeObject(persona2);

        myWriter2.close();

        try {
            while (true) { //lectura del fichero
                System.out.println(myReader.readObject().toString());
            }
        }catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        }


    }

}
