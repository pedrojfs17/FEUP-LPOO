public class Deposit implements Transaction {
	protected ATM atm;
	protected Session session;
	protected Card card;
	protected double amount;

	public Deposit(ATM atm, Session s, Card c, double i) {
		this.atm = atm;
		this.session = s;
		this.card = c;
		this.amount = i;
	}
}
