import java.util.ArrayList;
import java.util.Objects;

public class Folder extends Node {
    private ArrayList<Node> child;

    public Folder(FileSystem fs) {
        super(fs);
        this.child = new ArrayList<>();
    }

    public Folder(Folder parent, String name) throws DuplicateNameException {
        super(parent, name);
        this.child = new ArrayList<>();
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Node n : child)
            size += n.getSize();
        return size;
    }

    public Node[] getChild() {
        Node[] array = new Node[child.size()];
        return child.toArray(array);
    }

    public void addChild(Node child) throws DuplicateNameException {
        if (getChildByName(child.getName()) != null) throw new DuplicateNameException();
        this.child.add(child);
    }

    public Node getChildByName(String name) {
        for (Node n : child)
            if (n.getName().equals(name)) return n;
        return null;
    }

    public Folder clone(Folder root, String name) throws DuplicateNameException {
        Folder f = new Folder(root, name);
        for (Node n : child) {
            if (n instanceof Folder) f.addChild(((Folder) n).clone(f, n.getName()));
            else {
                try {
                    f.addChild((File) n.clone());
                } catch (CloneNotSupportedException ignored) {
                }
            }
        }
        return f;
    }

}
