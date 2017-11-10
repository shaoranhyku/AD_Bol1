import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio12 {

//    Dado el fichero XML, añádele los siguientes atributos utilizando un editor de textos:
//            • Prefijo del país en el teléfono
//            • Localidad en la dirección
//            • Tipo de moneda en el dinero
//    Procésalo con SAX y guarda los datos en un fichero de texto. Guarda cada contacto en una línea y
//    separa los datos del contacto con un espacio.

    public static void main(String[] args) throws IOException, SAXException {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor = new GestionContenido("Ejercicio12.txt");
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("Ejercicio12.xml");
        procesadorXML.parse(fileXML);
    }
}

class GestionContenido extends DefaultHandler {

    BufferedWriter writer;
    String contacto;

    public GestionContenido(String destino) {
        super();
        try {
            writer = new BufferedWriter(new FileWriter(destino));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startDocument() {
        try {
            writer.write("Contact List:");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endDocument() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
        if (nombre.equals("Contacto")) {
            contacto = "";
        } else if (nombre.equals("phoneNumber")) {
            contacto += String.format(" %s: %s ", capitalizer(nombre), atts.getValue(0));
        } else if (nombre.equals("address")) {
            contacto += String.format(" %s: %s %s: ", capitalizer(atts.getQName(0)), atts.getValue(0), capitalizer(nombre));
        } else if (nombre.equals("debts")) {
            contacto += String.format(" %s: %s ", capitalizer(nombre), atts.getValue(0));
        } else if (nombre.equals("name")) {
            contacto += String.format("%s: ", capitalizer(nombre));
        } else {
            contacto += String.format(" %s: ", capitalizer(nombre));
        }
    }

    public void endElement(String uri, String nombre, String nombreC) {
        if (nombre.equals("Contacto")) {
            try {
                writer.write(contacto);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car = new String(ch, inicio, longitud);
        car = car.replaceAll("[\t\n] ", "");
        car = car.trim();
        contacto += String.format("%s", car);
    }

    public static String capitalizer(final String texto) {

        String[] palavras = texto.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String palavra : palavras) {
            sb.append(Character.toUpperCase(palavra.charAt(0))).append(palavra.substring(1).toLowerCase()).append(" ");
        }

        return sb.toString().trim();
    }
}
