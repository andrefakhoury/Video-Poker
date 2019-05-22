package video_poker;

/**
 * Classe que armazena os valores de uma carta especifica.
 * @author Andre Luis Mendes Fakhoury - 4482145
 * @author Gustavo Vinicius Vieira Silva Soares - 10734428
 */
public class Carta implements Comparable<Carta> {

    /**
     * Todos os Naipes possiveis
     */
    public static enum Naipe {
        PAUS("\u2663"), COPAS("\u2665"), ESPADAS("\u2660"), OUROS("\u2666");

        private final String simbolo; // simbolo de cada Naipe

        private Naipe(String simbolo) {
            this.simbolo = simbolo;
        }

        /**
         * Retorna a string de simbolo de cada Naipe
         * @return string do Naipe
         */
        public String getSimbolo() {
            return simbolo;
        }
    }

    /**
     * Todos os valores possiveis, com seus respectivos simbolos
     */
    public static enum Valor {
        V2("2"), V3("3"), V4("4"), V5("5"), V6("6"), V7("7"), V8("8"), V9("9"), V10("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

        private final String simbolo;

        private Valor(String simbolo) {
            this.simbolo = simbolo;
        }

        /**
         * Retorna string do simbolo do valor
         * @return Simbolo do valor atual
         */
        public String getSimbolo() {
            return simbolo;
        }
    }

    private final Naipe naipe;
    private final Valor valor;

    /**
     * Construtor da video_poker.video_poker.Carta, recebe o naipe e o valor
     * @param naipe
     * @param valor
     */
    public Carta(Naipe naipe, Valor valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    /**
     * Retorna o naipe da carta
     * @return Naipe da carta
     */
    public Naipe getNaipe() {
        return naipe;
    }

    /**
     * Retorna o valor da carta
     * @return Valor da carta
     */
    public Valor getValor() {
        return valor;
    }

    /**
     * Verifica se uma carta equivale a outra mandada por parametro.
     * @param obj outro objeto a se conferir
     * @return true se o objeto for uma carta igual a atual, diferente caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Carta) {
            return ((Carta) obj).naipe == this.naipe && ((Carta) obj).valor == this.valor;
        }
        return false;
    }

    /**
     * Imprime uma arte da video_poker.video_poker.Carta atual
     * @return
     */
    @Override
    public String toString() {
        String ret = "+-----+\n";
        ret += "|     |\n";
        ret += "| " + naipe.getSimbolo() + " " + valor.getSimbolo();
        if (valor.getSimbolo() != "10")  ret += " ";
        ret += "|\n";
        ret += "|     |\n";
        ret += "+-----+\n";
        return ret;
    }

    /**
     * Operador de comparação de duas cartas, ordenando pelo valor, e desempatando pelo naipe.
     * @param carta
     * @return
     */
    @Override
    public int compareTo(Carta carta) {
        return valor == carta.valor ? naipe.ordinal() - carta.naipe.ordinal() : valor.ordinal() - carta.valor.ordinal();
    }
}