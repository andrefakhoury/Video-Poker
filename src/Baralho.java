import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que cria e gerencia um Baralho completo de cartas.
 * @author Andre Luis Mendes Fakhoury - 4482145
 * @author Gustavo Vinicius Vieira Silva Soares - 10734428
 */
public class Baralho {

    private LinkedList<Carta> baralho;

    /**
     * Cria um novo baralho com todas as cartas
     */
    public Baralho() {
        Carta.Naipe[] naipes = Carta.Naipe.values();
        Carta.Valor[] valors = Carta.Valor.values();
        this.baralho = new LinkedList<>();

        for (Carta.Naipe n : naipes) {
            for (Carta.Valor v : valors) {
                baralho.add(new Carta(n, v));
            }
        }
    }

    /**
     * Retorna a lista contendo todas as cartas do baralho
     * @return as cartas do baralho
     */
    public List<Carta> getBaralho() {
        return baralho;
    }

    /**
     * Remove 5 cartas do baralho e retorna essas cartas
     * @return um array com 5 cartas
     */
    public Carta[] getHand() {
        return getHand(5);
    }

    /**
     * Remove n cartas do baralho e retorna essas cartas
     * @param n o numero de cartas
     * @return um array com n cartas
     */
    public Carta[] getHand(int n) {
        Carta[] hand = new Carta[n];
        for (int i = 0; i < n; i++) {
            hand[i] = baralho.removeFirst();
        }
        return hand;
    }

    /**
     * Embaralha o baralho
     */
    public void embaralhar() {
        Collections.shuffle(baralho);
    }

}
