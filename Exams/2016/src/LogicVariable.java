import java.util.Objects;

public class LogicVariable {
    private String name;
    private boolean value;

    private LogicGate calculatedBy;

    public LogicVariable(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public LogicVariable(String name) {
        this.name = name;
        this.value = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getValue() {
        if (calculatedBy == null) return value;
        else calculatedBy.calculate();

        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public LogicGate getCalculatedBy() {
        return calculatedBy;
    }

    public void setCalculatedBy(LogicGate calculatedBy) {
        this.calculatedBy = calculatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicVariable that = (LogicVariable) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getFormula() {
        if (calculatedBy == null) return name;
        String formula = calculatedBy.getSymbol() + "(";
        for (int i = 0; i < calculatedBy.getInputs().length; i++) {
            formula = formula.concat(calculatedBy.getInputs()[i].getFormula());
            if (i + 1 < calculatedBy.getInputs().length)
                formula = formula.concat(",");
        }
        formula = formula.concat(")");
        return formula;
    }
}
