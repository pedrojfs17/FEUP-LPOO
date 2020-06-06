public class FileSystem {
    private String type;
    private Folder root;
    private NameFormatter nameFormatter;

    public FileSystem(String type) {
        this.type = type;
        this.root = new Folder(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Folder getRoot() {
        return root;
    }

    public void setRoot(Folder root) {
        this.root = root;
    }

    public void setNameFormatter(NameFormatter nameFormatter) {
        this.nameFormatter = nameFormatter;
    }

    public NameFormatter getNameFormatter() {
        return nameFormatter;
    }
}
