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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isHasDebts() {
        return hasDebts;
    }

    public void setHasDebts(boolean hasDebts) {
        this.hasDebts = hasDebts;
    }

    public double getDebts() {
        return debts;
    }

    public void setDebts(double debts) {
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
