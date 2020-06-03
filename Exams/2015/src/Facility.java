public abstract class Facility {
    private int capacity;

    public Facility() {this.capacity = 0;}

    public Facility(int capacity) {this.capacity = capacity;}

    public abstract String getName();

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public abstract boolean canEnter(User u1);
}
