import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Baralho {

	private LinkedList<Carta> baralho;
	
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
	
	public List<Carta> getBaralho() {
		return baralho;
	}

	public Carta[] getHand() {
		return getHand(5);
	}
	
	public Carta[] getHand(int n) {
		Carta[] hand = new Carta[n];
		for (int i = 0; i < n; i++) {
			hand[i] = baralho.removeFirst();
		}
		return hand;
	}
	
	public void embaralhar() {
		Collections.shuffle(baralho);
	}
	
}
