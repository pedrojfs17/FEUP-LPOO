public class Pendular extends Comboio {
    public Pendular(String nome) {
        super(nome);
        setServicoABordo(new ServicoSemEnjoos());
    }

    @Override
    public String toString() {
        return "Pendular " + getNome() + ", " +
                getNumCarruagens() + (getNumCarruagens() != 1 ?  " carruagens" : " carruagem") + ", " +
                getNumLugares() + (getNumLugares() != 1 ?  " lugares" : " lugar") + ", " +
                getNumPassageiros() + (getNumPassageiros() != 1 ?  " passageiros" : " passageiro");    }
}
