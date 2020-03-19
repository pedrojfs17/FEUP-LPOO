public class AreaStringOutputter {
    private AreaAggregator aggregator;

    AreaStringOutputter(AreaAggregator aggregator) {
        this.aggregator = aggregator;
    }

    public String output() {
        return "Sum of areas: " + aggregator.sum();
    }
}
