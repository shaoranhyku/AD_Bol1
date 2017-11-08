import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.io.*;

public class Ejercicio14 {

//     14 A partir del fichero XML de la agenda telefónica, obtener un fichero Json utilizando la
//    librería Gson.

    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ejercicio14.json"));
        Agenda agenda;

        XStream xstream = new XStream();
        xstream.alias("ListaContactos", Agenda.class);
        xstream.alias("Contacto", PersonaAgenda.class);
        xstream.addImplicitCollection(Agenda.class, "lista");

        agenda= (Agenda) xstream.fromXML(new FileInputStream("Ejercicio10.xml"));

        writer.write(gson.toJson(agenda));

        writer.close();
    }

}
