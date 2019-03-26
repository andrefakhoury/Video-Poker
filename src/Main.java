

public class Main {

    public static void main(String[] args) {
        long creditos = 200;

        do {
            System.out.printf("Saldo atual: %d\n", creditos);
            System.out.println("Digite a quantidade que quer apostar.");
            int aposta = readInt();
            if (aposta < 0) {
            	break;
            }
            if (aposta > creditos || aposta == 0) {
            	continue;
            }
            creditos -= aposta;

            Baralho baralho = new Baralho();
            baralho.embaralhar();

            Hand hand = new Hand(baralho);
            System.out.println(hand);

            System.out.println("Digite as cartas que quer trocar: ");
            String option = readString();

            hand.trocar(option);
            System.out.println(hand);

            System.out.println("Digite as cartas que quer trocar: ");
            option = readString();

            hand.trocar(option);
            System.out.println(hand);

            int mult = hand.multiplicadorMao();
            System.out.printf("Conseguiu um profit de %dx...\n", mult);
            creditos += aposta * mult;
        } while (creditos > 0);

    }
    
    private static int readInt() {
    	try {
    		return EntradaTeclado.leInt();
    	} catch (Exception e) {
			return 0;
		}
    }
    
    private static String readString() {
    	try {
    		return EntradaTeclado.leString();
    	} catch (Exception e) {
    		return "";
    	}
    }
    
}
