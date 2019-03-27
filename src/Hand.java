import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Hand {
    private Baralho baralho;
    private Carta[] hand;

    public Hand(Baralho baralho) {
        this.hand = baralho.getHand();
        this.baralho = baralho;
    }

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

    public String toString() {
        String ret = "";
        for (int i = 0; i < hand.length; i++)
            ret += hand[i] + " ";
        return ret;
    }

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

    private static boolean straight(Carta[] ordHand) {
        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getValor().ordinal() - ordHand[i - 1].getValor().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean flush(Carta[] ordHand) {
        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].getNaipe() != ordHand[i - 1].getNaipe()) {
                return false;
            }
        }
        return true;
    }

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
