public class FiguraComposta extends Figura {
    private Figura[] figuras;

    public FiguraComposta(Figura[] figuras) {
        this.figuras = figuras;
        for (Figura f : figuras) add();
    }

    @Override
    public double getArea() {
        double area = 0;
        for (Figura f : figuras)
            area += f.getArea();
        return area;
    }

    @Override
    public double getPerimetro() {
        double perimetro = 0;
        for (Figura f : figuras)
            perimetro += f.getPerimetro();
        return perimetro;
    }
}
