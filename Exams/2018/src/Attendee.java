public class Attendee extends Person {
    private boolean paid;

    public Attendee(String name) {
        super(name);
        this.paid = false;
    }

    public Attendee(String name, int age) {
        super(name, age);
    }

    public boolean hasPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "Attendee " + super.getName() + (paid ? " has" : " hasn't") + " paid its registration.";
    }
}
