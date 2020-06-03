import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Room extends Facility {
    private Building building;
    private String number;
    private int floor;

    private Set<User> authorizedUsers;

    public Room(Building building, String number, int floor) throws DuplicateRoomException {
        super();
        if (floor > building.getMaxFloor() || floor < building.getMinFloor()) throw new IllegalArgumentException();
        this.building = building;
        this.number = number;
        this.floor = floor;
        building.addRoom(this);
        authorizedUsers = new HashSet<>();
    }

    public Room(Building building, String number, int floor, int capacity) throws DuplicateRoomException {
        super(capacity);
        if (floor > building.getMaxFloor() || floor < building.getMinFloor()) throw new IllegalArgumentException();
        this.building = building;
        building.setCapacity(building.getCapacity() + capacity);
        this.number = number;
        this.floor = floor;
        building.addRoom(this);
        authorizedUsers = new HashSet<>();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String getName() {
        return building.getName() + number;
    }

    @Override
    public String toString() {
        return "Room(" + building.getName() + "," + number + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return floor == room.floor &&
                Objects.equals(building, room.building) &&
                Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(building, number, floor);
    }

    public void authorize(User u1) {
        authorizedUsers.add(u1);
    }

    public boolean canEnter(User u1) {
        return authorizedUsers.contains(u1);
    }
}
