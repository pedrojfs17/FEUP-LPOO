public class GateNot extends LogicGate {
    public GateNot(LogicVariable output, LogicVariable input) throws CollisionException, CycleException {
        super(output, input);
        calculate();
    }

    @Override
    public String getSymbol() {
        return "NOT";
    }

    @Override
    public void calculate() {
        output.setValue(!inputs[0].getValue());
    }
}
