/**
 * Classe principal, que executa o jogo em si
 * @author Andre Luis Mendes Fakhoury - 4482145
 * @author Gustavo Vinicius Vieira Silva Soares - 10734428
 */
public class Main {

    /**
     * Funcao para jogar o poker
     */
    public static void main(String[] args) {
        long creditos = 200; //inicia com 200 creditos

        do {
            System.out.printf("Saldo atual: %d\n", creditos);
            System.out.println("Digite a quantidade que quer apostar (ou um numero negativo para encerrar).");
            int aposta = readInt();
            if (aposta < 0) { // encerra o loop
                break;
            }

            if (aposta > creditos || aposta == 0) {
                System.out.println("Valor invalido.");
                continue;
            }

            creditos -= aposta;

            // cria um novo baralho, com todas as cartas
            Baralho baralho = new Baralho();
            baralho.embaralhar();

            // separa algumas cartas para a mao atual
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
            creditos += aposta * mult; // multiplica de acordo com as cartas
        } while (creditos > 0);

        System.out.println("Voce encerrou o jogo com " + creditos + " créditos.");

    }

    /**
     * Le e retorna um numero inteiro, e 0 caso haja algum erro.
     * @return numero lido
     */
    private static int readInt() {
        try {
            return EntradaTeclado.leInt();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Le e retorna uma string, e vazio caso haja algum erro
     * @return string lida lido
     */
    private static String readString() {
        try {
            return EntradaTeclado.leString();
        } catch (Exception e) {
            return "";
        }
    }

}
