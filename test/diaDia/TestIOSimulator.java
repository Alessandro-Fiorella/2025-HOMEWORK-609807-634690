package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;

class TestIOSimulator {

    IO ioSimulator;
    DiaDia gioco;

    @Test
    public void testVittoria() {
        String vittoria[] = {
        		"prendi chiave", "vai nord", "vai est", "vai est", "vai est"};

        ioSimulator = new IOSimulator(vittoria);
        gioco = new DiaDia(ioSimulator);
        gioco.gioca();

        assertTrue(gioco.getPartita().isFinita());
    }
}
