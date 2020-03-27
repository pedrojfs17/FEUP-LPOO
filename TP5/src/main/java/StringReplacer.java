public class StringReplacer implements StringTransformer {
    private char old;
    private char recent;

    public StringReplacer(char replaced, char replacer) {
        this.old = replaced;
        this.recent = replacer;
    }

    @Override
    public void execute(StringDrink drink) {
        String str = drink.getText();
        str = str.replace(old, recent);
        drink.setText(str);
    }

    @Override
    public void undo(StringDrink drink) {
        StringReplacer sr = new StringReplacer(recent, old);
        sr.execute(drink);
    }
}
