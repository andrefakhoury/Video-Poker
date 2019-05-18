package video_poker;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {

    @Test
    public void getNaipe() {
        Carta c = new Carta(Carta.Naipe.OUROS, Carta.Valor.ACE);
        assertEquals(Carta.Naipe.OUROS, c.getNaipe());
    }

    @Test
    public void getValor() {
        Carta c = new Carta(Carta.Naipe.OUROS, Carta.Valor.ACE);
        assertEquals(Carta.Valor.ACE, c.getValor());
    }

    @Test
    public void TestaResto() {
        Carta c1 = new Carta(Carta.Naipe.OUROS, Carta.Valor.ACE);
        Carta c2 = new Carta(Carta.Naipe.ESPADAS, Carta.Valor.V10);
        assertNotEquals(c1, null);
        assertNotEquals(c1, c2);
        assertNotEquals(c1.toString(), c2.toString());
        assertNotEquals(c1.compareTo(c2), 0);
        assertNotEquals(c2.compareTo(c1), 0);
    }
}