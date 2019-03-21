import java.lang.reflect.Array;
import java.util.Arrays;

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
        int qttPares = 0;
        for (int i = 1; i < ordHand.length; i++) {
            if (ordHand[i].equals(ordHand[i-1])) {
                qttPares++;
                i++;
            }
        }
        if (qttPares == 2) return 2;

        return 0;
    }
}