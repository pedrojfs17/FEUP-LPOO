public class User {
    private String username;

    public User(String name, int age) {
        this.username = name + age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
