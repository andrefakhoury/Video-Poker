//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import video_poker.Baralho;
import video_poker.Carta;


public class BaralhoTest {
    Baralho b;

    @BeforeEach
    public void setup() {
        b = new Baralho();
    }


    @Test
    public void getBaralho() {
        assertEquals(b.getBaralho().size(), Carta.Valor.values().length * Carta.Naipe.values().length);
    }

    @Test
    public void getHand() {
        assertEquals(b.getHand().length, 5);
    }

    @Test
    public void getHand1() {
        assertEquals(b.getHand(5).length, 5);
    }

    @Test
    public void embaralhar() {
        b.embaralhar();
        assertNotNull(b.getBaralho());
    }
}