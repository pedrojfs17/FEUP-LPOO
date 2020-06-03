public abstract class LogicGate {
    protected LogicVariable output;
    protected LogicVariable[] inputs;

    public LogicGate(LogicVariable output, LogicVariable input1, LogicVariable input2) throws CollisionException {
        if (output.getCalculatedBy() != null) throw new CollisionException();
        this.output = output;
        this.output.setCalculatedBy(this);
        this.inputs = new LogicVariable[]{input1, input2};
    }

    public LogicGate(LogicVariable output, LogicVariable input) throws CollisionException {
        if (output.getCalculatedBy() != null) throw new CollisionException();
        this.output = output;
        this.output.setCalculatedBy(this);
        this.inputs = new LogicVariable[]{input};
    }

    public LogicVariable getOutput() {
        return output;
    }

    public void setOutput(LogicVariable output) {
        this.output = output;
    }

    public LogicVariable[] getInputs() {
        return inputs;
    }

    public void setInputs(LogicVariable[] inputs) {
        this.inputs = inputs;
    }

    public abstract String getSymbol();

    public String getFormula() {
        return output.getFormula();
    }

    public abstract void calculate();
}
