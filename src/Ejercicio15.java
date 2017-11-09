import com.google.gson.Gson;

import java.io.*;

public class Ejercicio15 {

//    15 Dado el fichero Json del ejercicio anterior, obtener un fichero de texto con el siguiente
//    formato:
//    *******************************************************************************
//                          AGENDA TELEFONICA
//    *******************************************************************************
//    Nombre: Juan García
//    Teléfono: 956 23 45 67
//    Dirección: C/Romero Peña, 20
//    Código Postal: 11205
//    Fecha de nacimiento: 09/01/1987
//    Le debo dinero: No
//    Cuánto: 0,0
//    *******************************************************************************
//    Nombre: María Pérez
//    Teléfono: 956 66 54 97
//    Dirección: C/Tramontana, 35
//    Código Postal: 11207
//    Fecha de nacimiento: 21/02/1985
//    Le debo dinero: Sí
//    Cuánto: 67,43
//    *******************************************************************************
//                          FIN DE LA AGENDA TELEFONICA
//    *******************************************************************************


    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ejercicio15.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("Ejercicio14.json"));
        Agenda agenda;

        agenda=gson.fromJson(reader,Agenda.class);
        reader.close();

        //Escribo la cabecera
        for(int i=0;i<79;i++){
            writer.write('*');
        }
        writer.newLine();
        writer.write(String.format("%50s","AGENDA TELEFÓNICA"));
        writer.newLine();

        //Comienzo a pintar los datos
        for(PersonaAgenda contacto : agenda.getLista()){
            for(int i=0;i<79;i++){
                writer.write('*');
            }
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Nombre:",contacto.getName()));
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Teléfono:",contacto.getPhoneNumber()));
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Dirección:",contacto.getAddress()));
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Código Postal:",contacto.getPostalCode()));
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Fecha de nacimiento:",contacto.getBirthdate().toString()));
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Le debo dinero:",contacto.isHasDebts()?"Si":"No"));
            writer.newLine();
            writer.write(String.format("\t%-21s%s","Cuánto:",contacto.getDebts()));
            writer.newLine();
        }

        //Escribo el final
        for(int i=0;i<79;i++){
            writer.write('*');
        }
        writer.newLine();
        writer.write(String.format("%50s","FIN DE LA AGENDA TELEFÓNICA"));
        writer.newLine();
        for(int i=0;i<79;i++){
            writer.write('*');
        }

        //Cierro el Writer
        writer.close();
    }

}
