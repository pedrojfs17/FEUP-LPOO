public class StringInverter implements StringTransformer {
    @Override
    public void execute(StringDrink drink) {
        StringBuffer sbf = new StringBuffer(drink.getText());

        sbf.reverse();

        drink.setText(sbf.toString());
    }

    @Override
    public void undo(StringDrink drink) {
        execute(drink);
    }
}
