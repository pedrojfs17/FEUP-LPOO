import static java.lang.Math.*;

public class NormalDistribution extends ProbabilityDistribution {
    private String name;

    public NormalDistribution() {
        super("N",0.0,1.0);
    }

    public NormalDistribution(double mean, double stddev) {
        super("N",mean, stddev);
        this.name = "N";
        if (mean == 0 && stddev == 0) throw new IllegalArgumentException();
    }

    public NormalDistribution(String name, double mean, double stddev) {
        super(name,mean, stddev);
        this.name = name;
        if (mean == 0 && stddev == 0) throw new IllegalArgumentException();
    }

    @Override
    public double probabilityDensityFunction(double x) {
        return 1/(stddev * sqrt(2 * Math.PI)) * exp(-0.5*pow((x - mean) / stddev,2));
    }

    @Override
    public double calcRangeProbability(double xMin, double xMax) {
        return Math.abs(xMax - xMin) / 2 * 0.477250;
    }

    @Override
    public double calcLeftProbability(double x) {
        if (x < 1)
            return 0.5 - 0.477250;
        else if (x > 1)
            return 0.5 + 0.477250;
        return 0.5;
    }

    @Override
    public String toString() {
        return name + "(" + mean + ", " + stddev + ")";
    }
}
