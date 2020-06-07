public class TGV extends Comboio {
    public TGV(String nome) {
        super(nome);
        setServicoABordo(new ServicoPrioritario());
    }

    @Override
    public String toString() {
        return "TGV " + getNome() + ", " +
                getNumCarruagens() + (getNumCarruagens() != 1 ?  " carruagens" : " carruagem") + ", " +
                getNumLugares() + (getNumLugares() != 1 ?  " lugares" : " lugar") + ", " +
                getNumPassageiros() + (getNumPassageiros() != 1 ?  " passageiros" : " passageiro");    }
}
