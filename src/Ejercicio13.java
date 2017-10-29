import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio13 {
    public static void main(String[] args) throws IOException {

        Source estilo=new StreamSource("Ejercicio13.xsl");
        Source datos=new StreamSource("Ejercicio10.xml");
        Result resultado=new StreamResult(new FileOutputStream("Ejercicio13.html"));

        try {
            Transformer transformer= TransformerFactory.newInstance().newTransformer(estilo);
            transformer.transform(datos,resultado);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

