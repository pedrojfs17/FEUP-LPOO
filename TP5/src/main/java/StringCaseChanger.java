import static java.lang.Character.isLowerCase;

public class StringCaseChanger implements StringTransformer {
    @Override
    public void execute(StringDrink drink) {
        StringBuffer sbf = new StringBuffer(drink.getText());

        for (int i = 0; i < sbf.length(); i++) {
            Character c = sbf.charAt(i);
            if (Character.isLowerCase(c))
                sbf.replace(i, i+1, Character.toUpperCase(c)+"");
            else
                sbf.replace(i, i+1, Character.toLowerCase(c)+"");
        }

        drink.setText(sbf.toString());
    }

    @Override
    public void undo(StringDrink drink) {
        execute(drink);
    }
}
