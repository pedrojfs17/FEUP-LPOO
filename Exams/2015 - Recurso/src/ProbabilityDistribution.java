import java.util.HashMap;
import java.util.Objects;

public abstract class ProbabilityDistribution {
    protected double mean;
    protected double stddev;

    private static HashMap<String, ProbabilityDistribution> distributions = new HashMap<>();

    public ProbabilityDistribution(String name, double mean, double stddev) {
        this.mean = mean;
        this.stddev = stddev;
        distributions.put(name, this);
    }

    public static Object find(String x) {
        return distributions.get(x);
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStddev() {
        return stddev;
    }

    public void setStddev(double stddev) {
        this.stddev = stddev;
    }

    public abstract double probabilityDensityFunction(double v);

    public abstract double calcRangeProbability(double xMin, double xMax);

    public abstract double calcLeftProbability(double x);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProbabilityDistribution that = (ProbabilityDistribution) o;
        return Double.compare(that.mean, mean) == 0 &&
                Double.compare(that.stddev, stddev) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mean, stddev);
    }
}
