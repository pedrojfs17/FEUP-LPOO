public class AreaStringOutputter {
    private SumProvider provider;

    AreaStringOutputter(SumProvider provider) {
        this.provider = provider;
    }

    public String output() {
        return "Sum of areas: " + provider.sum();
    }
}
