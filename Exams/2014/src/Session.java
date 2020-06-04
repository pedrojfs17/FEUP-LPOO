import java.util.Objects;

public class Session extends Countable {
    private ATM atm;

    public Session(ATM atm) {
        this.atm = atm;
    }

    public ATM getATM() {
        return atm;
    }

    public void setATM(ATM atm) {
        this.atm = atm;
    }

    public void addTransaction(Transaction t) {
        add();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(atm, session.atm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atm);
    }
}
