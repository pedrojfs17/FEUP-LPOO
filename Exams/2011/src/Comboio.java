import java.util.ArrayList;
import java.util.List;

public class Comboio {
    private String nome;
    private List<Carruagem> carruagens;

    private int numPassageiros;

    private ServicoABordo servicoABordo;

    public Comboio(String nome) {
        this.nome = nome;
        this.carruagens = new ArrayList<>();
        this.numPassageiros = 0;
        this.servicoABordo = new ServicoRegular();
    }

    public String getNome() {
        return nome;
    }

    public int getNumLugares() {
        int num = 0;
        for (Carruagem c : carruagens)
            num += c.getNumLugares();
        return num;
    }

    public int getNumCarruagens() {
        return carruagens.size();
    }

    public void addCarruagem(Carruagem c) {
        carruagens.add(c);
    }

    public Carruagem getCarruagemByOrder(int index) {
        return carruagens.get(index);
    }

    public void removeAllCarruagens(int capacidade) {
        for (int i = 0; i < carruagens.size(); i++) {
            if (carruagens.get(i).getNumLugares() == capacidade) {
                carruagens.remove(i);
                i--;
            }
        }
    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public int getLugaresLivres() {
        return getNumLugares() - numPassageiros;
    }

    public void addPassageiros(int n) throws PassengerCapacityExceeded {
        if (this.numPassageiros + n > getNumLugares()) throw new PassengerCapacityExceeded();
        this.numPassageiros += n;
    }

    public void removePassageiros(int n) {
        this.numPassageiros = Math.max(this.numPassageiros - n, 0);
    }

    public void removePassageiros() {
        this.numPassageiros = 0;
    }

    @Override
    public String toString() {
        return "Comboio " + getNome() + ", " +
                getNumCarruagens() + (getNumCarruagens() != 1 ?  " carruagens" : " carruagem") + ", " +
                getNumLugares() + (getNumLugares() != 1 ?  " lugares" : " lugar") + ", " +
                getNumPassageiros() + (getNumPassageiros() != 1 ?  " passageiros" : " passageiro");
    }

    @Override
    public boolean equals(Object o) {
        if (carruagens.size() != ((Comboio)o).getNumCarruagens()) return false;
        for (int i = 0; i < carruagens.size(); i++) {
            if (carruagens.get(i).getNumLugares() != ((Comboio)o).getCarruagemByOrder(i).getNumLugares())
                return false;
        }
        return true;
    }

    public ServicoABordo getServicoABordo() {
        return servicoABordo;
    }

    public void setServicoABordo(ServicoABordo servicoABordo) {
        this.servicoABordo = servicoABordo;
    }

    public String getDescricaoServico() {
        return servicoABordo.getDescricao();
    }
}
