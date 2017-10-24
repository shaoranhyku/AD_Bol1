import java.util.ArrayList;

public class Agenda {

    private ArrayList<PersonaAgenda> lista = new ArrayList<>();

    public void add(PersonaAgenda per) {
        lista.add(per);
    }

    public ArrayList<PersonaAgenda> getLista() {
        return lista;
    }
}
