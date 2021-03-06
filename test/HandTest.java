//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import video_poker.Baralho;
import video_poker.Carta;
import video_poker.Hand;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HandTest {

    private void edit(Hand h, Carta... cartas) {
        try {
            Field f = h.getClass().getDeclaredField("hand");
            f.setAccessible(true);
            f.set(h, cartas);
        } catch (Exception e) {
            fail("edit");
        }
    }

    @Test
    public void testarTroca() {
        Baralho baralho = new Baralho();
        Hand h = new Hand(baralho);
        String s = h.toString();
        h.trocar("1");
        assertNotEquals(s, h.toString());
    }

    @Test
    public void multiplicadorMao() {
        Baralho baralho = new Baralho();
        Hand h = new Hand(baralho);
        assertTrue(h.multiplicadorMao() >= 0);
    }

    @Test
    public void testaResto() {
        Baralho baralho = new Baralho();
        Hand h = new Hand(baralho);

        List<Carta> list = new Baralho().getBaralho();
        list = new ArrayList<>(list);

        boolean tester = true;
        for (Carta c1 : list) {
            for (Carta c2 : list) {
                for (Carta c3 : list) {
                    for (Carta c4 : list) {
                        for (Carta c5 : list) {
                            edit(h, c1, c2, c3, c4, c5);
                            tester = tester && h.multiplicadorMao() > -1;
                        }
                    }
                }
            }
        }
        assertTrue(tester);
    }

}