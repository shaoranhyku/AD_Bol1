import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio17 {

    public enum Mode {
        INT, STRING, DOUBLE, BOOLEAN
    }

//     17 Realiza una función que te permita buscar en un json por un número indeterminado de
//    campos.

    public static void main(String[] args) throws IOException {

        searchInJson(new File("Ejercicio17.json"));

    }

    public static void searchInJson(File file) throws IOException {

        final String PATTERN_SEARCH = "\"(.*?)\":([^,]*?)";
        final String FORMAT_PATTERN_QUOTATION = "\"(%s)\":\"(%s)\"";
        final String FORMAT_SEARCHPATTERN_QUOTATION = "\"%s\":\"%s\"";
        final String FORMAT_PATTERN_NO_QUOTATION = "\"(%s)\":(%s)";
        final String FORMAT_SEARCHPATTERN_NO_QUOTATION = "\"%s\":%s";
        String patternComplete = "[{]";
        String searchPatternComplete = "[{]";
        String format;
        String formatSearch;
        String patternSearch;
        int numFields = 0;
        int counter=0;
        int i;
        boolean successFind = true, tryEndFile = false, firstSearch = true;
        Pattern patron;
        Matcher matcher;
        Mode actualMode;

        //Creo el reader con la ruta del fichero obtenida
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String json = "", aux;

        //Obtengo el JSON
        while ((aux = reader.readLine()) != null) {
            json += aux;
        }

        //Comienzo a buscar los campos

        while (successFind) {

            //Establezco el patron por el que buscar
            if(firstSearch){
                patternSearch="[{]"+PATTERN_SEARCH;
            }else{
                patternSearch=searchPatternComplete+","+PATTERN_SEARCH;
            }

            if(tryEndFile){
                patternSearch+="[}]";
            }else{
                patternSearch+=",";
            }

            //Aplico el patron y creo un matcher.
            patron = Pattern.compile(patternSearch);
            matcher = patron.matcher(json);

            //Busco un resultado
            if (matcher.find()) {
                numFields++;
                //Si lo encuentro, determino el tipo del valor
                if(matcher.group(2).contains("}")){
                 tryEndFile=true;
                 successFind=false;
                }
                if (matcher.group(2).charAt(0) == '"') {
                    actualMode = Mode.STRING;
                    format = FORMAT_PATTERN_QUOTATION;
                    formatSearch = FORMAT_SEARCHPATTERN_QUOTATION;
                } else {
                    if (matcher.group(2).matches("[tf].*")) {
                        actualMode = Mode.BOOLEAN;
                    } else if (matcher.group(2).contains(".")) {
                        actualMode = Mode.DOUBLE;
                    } else {
                        actualMode = Mode.INT;
                    }
                    format = FORMAT_PATTERN_NO_QUOTATION;
                    formatSearch = FORMAT_SEARCHPATTERN_NO_QUOTATION;
                }

                //A la hora de guardar los datos sobre el campo y el valor, determino si es la primera busqueda o el final.
                if (!firstSearch) {
                    format = "," + format;
                    formatSearch = "," + formatSearch;
                }
                if(tryEndFile){
                    format=format+"[}]";
                    formatSearch=formatSearch+"[}]";
                }

                //Pregunto al usuario si quiere buscar por ese campo
                if (Teclado.leerBoolean(String.format("¿Quieres buscar por el campo %s?", matcher.group(1)), "Sí.", "No.")) {
                    System.out.print("Introduce el valor a buscar en el campo: ");
                    //Determino el tipo de valor que permito introducir
                    switch (actualMode) {
                        case BOOLEAN:
                            patternComplete += String.format(format, matcher.group(1), Teclado.leerBoolean("", "Verdadero", "Falso"));
                            break;
                        case INT:
                            patternComplete += String.format(format, matcher.group(1), Teclado.leerNumero(Teclado.Tipos.INT));
                            break;
                        case DOUBLE:
                            patternComplete += String.format(format, matcher.group(1), Teclado.leerNumero(Teclado.Tipos.DOUBLE));
                            break;
                        case STRING:
                            patternComplete += String.format(format, matcher.group(1), Teclado.leerString());
                            break;
                    }
                } else {
                    //Si no quiero buscar por ese campo, guardo el campo con cualquier valor válido
                    patternComplete += String.format(format, matcher.group(1), "[^,]*?");
                }
                //Independientemente de si quiero buscar por ese campo o no, lo agrego a mi patron para buscar el siguiente campo
                searchPatternComplete += String.format(formatSearch, matcher.group(1), "[^,]*?");
                if (firstSearch) {
                    firstSearch = false;
                    tryEndFile = false;
                }
            } else {
                //En caso de no encontrar ningun resultado
                if (!tryEndFile) {
                    //Establezco que se ha llegado el final del archivo
                    tryEndFile = true;
                } else {
                    //Si he llegado al final del archivo, termino de buscar
                    successFind = false;
                }

            }
        }

        //Una vez establecido un patrón de búsqueda con los campos que el usuario quiere buscar, realizo la busqueda.
        patron = Pattern.compile(patternComplete);
        matcher = patron.matcher(json);

        counter=0;
        while(matcher.find()){
            System.out.printf("Elemento %d:\n",++counter);
            i=0;
            while(i<numFields*2){
                System.out.printf("%s: %s\n",matcher.group(++i),matcher.group(++i));
            }
            System.out.println();
        }
    }
}

