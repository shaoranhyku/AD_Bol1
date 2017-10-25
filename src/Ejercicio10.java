import com.thoughtworks.xstream.XStream;

import java.io.*;

public class Ejercicio10 {

//     10 A partir de los datos del fichero binario de la agenda telefónica, crea un fichero XML
//    usando la librería XStream.

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Agenda agenda = new Agenda();

        ObjectInputStream myReader = new ObjectInputStream(new FileInputStream("ejercicio8_2.txt"));

        try {
            while (true) { //lectura del fichero
                agenda.add((PersonaAgenda) myReader.readObject());
            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        }
        myReader.close();

        XStream xstream = new XStream();
        xstream.alias("ListaContactos", Agenda.class);
        xstream.alias("Contacto", PersonaAgenda.class);
        xstream.addImplicitCollection(Agenda.class, "lista");
        xstream.toXML(agenda, new FileOutputStream("Ejercicio10.xml"));

        System.out.println("Proceso terminado.");

    }
}
