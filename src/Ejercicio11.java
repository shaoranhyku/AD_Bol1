import com.thoughtworks.xstream.XStream;

import java.io.*;

public class Ejercicio11 {

//     11 Dado el XML del ejercicio anterior, utilizando XStream, pásalo a un fichero binario sin
//    serialización de objetos. Compara que sea igual al fichero binario obtenido en el ejercicio 8. Haz la
//    comparación byte a byte.

    public static void main(String[] args) throws IOException {

        DataOutputStream myWriter=new DataOutputStream(new FileOutputStream("ejercicio11.txt"));
        FileInputStream checkFile1=new FileInputStream("ejercicio11.txt");
        FileInputStream checkFile2=new FileInputStream("ejercicio8.txt");

        Agenda agenda;
        PersonaAgenda contacto;

        XStream xstream = new XStream();
        xstream.alias("ListaContactos", Agenda.class);
        xstream.alias("Contacto", PersonaAgenda.class);
        xstream.addImplicitCollection(Agenda.class, "lista");

        agenda= (Agenda) xstream.fromXML(new FileInputStream("Ejercicio10.xml"));

        for(int i=0;i<agenda.getLista().size();i++){
            contacto=agenda.getLista().get(i);
            myWriter.writeUTF(contacto.getName());
            myWriter.writeInt(contacto.getPhoneNumber());
            myWriter.writeUTF(contacto.getAddress());
            myWriter.writeInt(contacto.getPostalCode());
            myWriter.writeUTF(contacto.getBirthdate().toString());
            myWriter.writeBoolean(contacto.isHasDebts());
            myWriter.writeDouble(contacto.getDebts());
        }

        myWriter.close();

        int icheckFile1 = 0;
        int icheckFile2 = 0;
        boolean isEqual = true;

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

        } while (icheckFile1>=0 && icheckFile2>=0 );

        if (isEqual) {
            System.out.println("Proceso realizado correctamente.");
        } else {
            System.out.println("Fallo en el proceso.");
        }

        checkFile1.close();
        checkFile2.close();

    }
}
