import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Classe que armazena uma mao atual do jogador, a partir do baralho geral
 */
public class Hand {
    private Baralho baralho; // baralho com as cartas possiveis
    private Carta[] hand; // mao atual do jogador

    /**
     * Construtor da mao, recebe o baralho com as cartas possiveis
     * @param baralho baralho com as cartas possiveis
     */
    public Hand(Baralho baralho) {
        this.hand = baralho.getHand();
        this.baralho = baralho;
    }

    /**
     * Troca os elementos da mao atual, a partir de uma string mandando os indices
     * @param s elementos que serao trocados
     */
    public void trocar(String s) {
        String[] quais = s.split(" ");

        for (int i = 0; i < quais.length; i++) {
            try {
                int c = Integer.parseInt(quais[i]) - 1;
                hand[c] = baralho.getHand(2)[0];
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Retorna a string da mao atual, ou seja, as cartas
     * @return mao atual do jogador
     */
    public String toString() {
        //String ret = "";
        //for (int i = 0; i < hand.length; i++)
        //    ret += hand[i] + " ";

        String ret[][] = new String[hand.length][6];
        for (int i = 0; i < hand.length; i++) {
            ret[i] = hand[i].toString().split("\n");
        }

        String ans = "";
        for (int i = 0; i < ret[0].length; i++) {
            for (int j = 0; j < ret.length; j++) {
                ans += ret[j][i] + "\t";
            }
            ans += "\n";
        }

        for (int i = 0; i < hand.length; i++)
            ans += (i+1) + "\t\t";

        return ans + "\n";
    }

    /**
     * Verifica as configuracoes da mão atual e gera o multiplicador para ela
     * @return multiplicador da mão atual
     */
    public int multiplicadorMao() {
        Carta[] ordHand = Arrays.copyOf(hand, hand.length);

        Arrays.sort(ordHand);
        if (royal(ordHand) && straight(ordHand) && flush(ordHand)) return 200;
        if (straight(ordHand) && flush(ordHand)) return 100;
        if (quadra(ordHand)) return 50;
        if (fullHand(ordHand)) return 20;
        if (flush(ordHand)) return 10;
        if (straight(ordHand)) return 5;
        if (trinca(ordHand)) return 2;
        if (doisPares(ordHand)) return 1;

        return 0;
    }

    /**
     * Verifica se tem dois pares na mão atual
     * @param ordHand a mão atual, ordenada
     * @return booleano se tem dois pares atualmente
     */
    private static boolean doisPares(Carta[] ordHand) {
        int qttPares = 0;
        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getValor() == ordHand[i-1].getValor()) {
                qttPares++;
                i++;
            }
        }
        return qttPares >= 2;
    }

    /**
     * Verifica uma trinca na situação atual
     * @param ordHand mão atual ordenada
     * @return booleano se tem trinca
     */
    private static boolean trinca(Carta[] ordHand) {
        int qtd = 1;

        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getValor() == ordHand[i-1].getValor())
                qtd++;
            else {
                qtd = 1;
            }

            if (qtd == 3)
                return true;
        }

        return false;
    }

    /**
     * Verifica uma quadra na situação atual
     * @param ordHand mão atual ordenada
     * @return booleano se tem quadra
     */
    private static boolean quadra(Carta[] ordHand) {
        int qtd = 1;

        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getValor() == ordHand[i-1].getValor())
                qtd++;
            else {
                qtd = 1;
            }

            if (qtd == 4)
                return true;
        }

        return false;
    }

    /**
     * Verifica um full hand na situação atual
     * @param ordHand mão atual ordenada
     * @return booleano se tem full hand
     */
    private static boolean fullHand(Carta[] ordHand) {
        Map<Carta.Valor, Integer> qtd = new HashMap<>();

        for (int i = 0; i < ordHand.length; i++) {
            int cur;
            try {
                cur = qtd.get(ordHand[i].getValor());
            } catch (Exception ex) {
                cur = 0;
            }
            qtd.put(ordHand[i].getValor(), cur + 1);
        }

        boolean hasTrinca = false, hasPair = false;

        for (Carta.Valor cv : qtd.keySet()) {
            int cur = qtd.get(cv);

            if (cur == 2) hasPair = true;
            if (cur == 3) hasTrinca = true;
        }

        return hasTrinca && hasPair;
    }

    /**
     * Verifica um straight na situação atual
     * @param ordHand mão atual ordenada
     * @return booleano se tem straight
     */
    private static boolean straight(Carta[] ordHand) {
        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getValor().ordinal() - ordHand[i - 1].getValor().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica um flush na situação atual
     * @param ordHand mão atual ordenada
     * @return booleano se tem flush
     */
    private static boolean flush(Carta[] ordHand) {
        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getNaipe() != ordHand[i - 1].getNaipe()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica um royal na situação atual
     * @param ordHand mão atual ordenada
     * @return booleano se tem royal
     */
    private static boolean royal(Carta[] ordHand) {
        int ord = Carta.Valor.V10.ordinal();
        for (int i = 0; i < ordHand.length; i++) {
            if (ordHand[i].getValor().ordinal() != ord + i) {
                return false;
            }
        }
        return true;
    }
}
