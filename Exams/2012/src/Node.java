import java.util.Objects;

public abstract class Node implements Cloneable {
    private static int num = 0;

    private FileSystem fs;
    private Folder parent;
    private String name;
    private int number;

    public Node(FileSystem fs) {
        this.fs = fs;
        this.name = "";
        num++;
        this.number = num;
    }

    public Node(Folder parent, String name) throws DuplicateNameException {
        this.parent = parent;
        this.name = name;
        this.fs = parent.getFS();
        parent.addChild(this);
        num++;
        this.number = num;
    }

    public static void resetNumbering(int number) {
        num = number;
    }

    public Folder getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public abstract int getSize();

    public String getPath() {
        if (parent != null)
            return parent.getPath() + fs.getNameFormatter().getSeparator() + name;
        else return "";
    }

    public FileSystem getFS() {
        return fs;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(fs, node.fs) &&
                Objects.equals(parent, node.parent) &&
                Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fs, parent, name);
    }
}
