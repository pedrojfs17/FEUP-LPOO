import java.util.Objects;

public class ATM {
    private int ID;
    private String place;
    private String name;

    public ATM(int ID, String place, String name) {
        this.ID = ID;
        this.place = place;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ATM " + ID + " (" + place + ", " + name + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return ID == atm.ID &&
                Objects.equals(place, atm.place) &&
                Objects.equals(name, atm.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, place, name);
    }
}
