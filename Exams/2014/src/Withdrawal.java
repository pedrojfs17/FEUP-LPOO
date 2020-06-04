import java.text.DecimalFormat;
import java.util.Locale;

public class Withdrawal implements Transaction {
    private ATM atm;
    private Session session;
    private Card card;
    private double amount;

    public Withdrawal(ATM atm, Session session, Card card, double amount) {
        this.atm = atm;
        this.session = session;
        this.card = card;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Withdrawal at " + atm + " of " + String.format(Locale.US,"%.2f",amount);
    }
}
