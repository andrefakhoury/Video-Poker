public class Carta implements Comparable<Carta> {
	
	public static enum Naipe {
		PAUS("♣"), COPAS("♥"), ESPADAS("♠"), OUROS("♦");
		
		private final String simbolo;

		private Naipe(String simbolo) {
			this.simbolo = simbolo;
		}

		public String getSimbolo() {
			return simbolo;
		}
	}
	
	public static enum Valor {
		V2("2"), V3("3"), V4("4"), V5("5"), V6("6"), V7("7"), V8("8"), V9("9"), V10("10"), JOKER("J"), QUEEN("Q"), KING("K"), ACE("A");
		
		private final String simbolo;

		private Valor(String simbolo) {
			this.simbolo = simbolo;
		}

		public String getSimbolo() {
			return simbolo;
		}
	}
	
	private final Naipe naipe;
	private final Valor valor;
	
	public Carta(Naipe naipe, Valor valor) {
		this.naipe = naipe;
		this.valor = valor;
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public Valor getValor() {
		return valor;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carta) {
			return ((Carta) obj).naipe == this.naipe && ((Carta) obj).valor == this.valor;
		}
		return false;
	}

	@Override
	public String toString() {
		return naipe.simbolo + valor.simbolo;
	}

	@Override
	public int compareTo(Carta carta) {
		return valor == carta.valor ? naipe.ordinal() - carta.naipe.ordinal() : valor.ordinal() - carta.valor.ordinal();
	}
}
