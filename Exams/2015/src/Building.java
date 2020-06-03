import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Building extends Facility {
    private String name;
    private int minFloor;
    private int maxFloor;

    private Set<Room> rooms;

    public Building(String name, int minFloor, int maxFloor) {
        super();
        if (minFloor > maxFloor) throw new IllegalArgumentException();
        this.name = name;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.rooms = new HashSet<>();
    }

    public Building(String name, int minFloor, int maxFloor, int capacity) {
        super(capacity);
        if (minFloor > maxFloor) throw new IllegalArgumentException();
        this.name = name;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.rooms = new HashSet<>();
    }

    public void addRoom(Room room) throws DuplicateRoomException {
        if (!rooms.add(room)) throw new DuplicateRoomException();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(int minFloor) {
        this.minFloor = minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    @Override
    public String toString() {
        return "Building(" + name + ")";
    }

    public boolean canEnter(User u1) {
        for (Room r : rooms) {
            if (r.canEnter(u1)) return true;
        }
        return false;
    }
}
