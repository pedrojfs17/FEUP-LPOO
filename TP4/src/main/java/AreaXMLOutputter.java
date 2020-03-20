public class AreaXMLOutputter {
    private SumProvider provider;

    AreaXMLOutputter(SumProvider provider) {
        this.provider = provider;
    }

    public String output() {
        return "<area>" + provider.sum() + "</area>";
    }
}
