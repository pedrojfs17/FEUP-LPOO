public class Ellipse implements Shape {
    private double xradius;
    private double yradius;

    Ellipse(double xradius, double yradius) {
        this.xradius = xradius;
        this.yradius = yradius;
    }

    public double getXradius() {
        return xradius;
    }

    public void setXradius(double xradius) {
        this.xradius = xradius;
    }

    public double getYradius() {
        return yradius;
    }

    public void setYradius(double yradius) {
        this.yradius = yradius;
    }

    @Override
    public double getArea() {
        return Math.PI * this.xradius * this.yradius;
    }
}
