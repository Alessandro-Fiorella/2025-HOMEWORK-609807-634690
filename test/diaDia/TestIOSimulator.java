package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class TestIOSimulator {

    IO ioSimulator;
    DiaDia gioco;
    Labirinto labirinto;
    IOConsole ioConsole;

    @Test
    public void testVittoria() {
        String vittoria[] = {
        		"prendi osso", "vai sud", "vai est", "vai est", "vai nord"};

        ioSimulator = new IOSimulator(vittoria);
        labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
        gioco = new DiaDia(labirinto, ioSimulator);
        gioco.gioca();

        assertTrue(gioco.getPartita().isFinita());
    }
}
