public class GateAnd extends LogicGate {
    public GateAnd(LogicVariable output, LogicVariable i1, LogicVariable i2) throws CollisionException, CycleException {
        super(output, i1, i2);
        calculate();
    }

    @Override
    public String getSymbol() {
        return "AND";
    }

    @Override
    public void calculate() {
        output.setValue(inputs[0].getValue() && inputs[1].getValue());
    }
}
