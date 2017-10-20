import java.io.Serializable;
import java.time.LocalDate;

public class PersonaAgenda implements Serializable {

    private String name;
    private int phoneNumber;
    private String address;
    private int postalCode;
    private LocalDate birthdate;
    private boolean hasDebts;
    private double debts;

    public PersonaAgenda(String name, int phoneNumber, String address, int postalCode, LocalDate birthdate, boolean hasDebts, double debts) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.birthdate = birthdate;
        this.hasDebts = hasDebts;
        this.debts = debts;
    }

    @Override
    public String toString() {
        return "PersonaAgenda{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", birthdate=" + birthdate +
                ", hasDebts=" + hasDebts +
                ", debts=" + debts +
                '}';
    }
}
