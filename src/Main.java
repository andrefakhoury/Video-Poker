import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        long creditos = 200;

        do {
            System.out.printf("Saldo atual: %d\n", creditos);
            System.out.println("Digite a quantidade que quer apostar.");
            int aposta = EntradaTeclado.leInt();
            creditos -= aposta;

            Baralho baralho = new Baralho();
            baralho.embaralhar();

            Hand hand = new Hand(baralho);
            System.out.println(hand);

            System.out.println("Digite as cartas que quer trocar: ");
            String option = EntradaTeclado.leString();

            hand.trocar(option);
            System.out.println(hand);

            System.out.println("Digite as cartas que quer trocar: ");
            option = EntradaTeclado.leString();

            hand.trocar(option);
            System.out.println(hand);

            int mult = hand.multiplicadorMao();
            System.out.printf("Conseguiu um profit de %dx...\n", mult);
            creditos += aposta * mult;

        } while (true);



    }
}
