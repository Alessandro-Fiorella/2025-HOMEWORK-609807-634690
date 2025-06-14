package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

class TestIOSimulator {

    IO ioSimulator;
    DiaDia gioco;
    Labirinto labirinto;
    IOConsole ioConsole;

    @Test
    public void testVittoria() {
        List<String> vittoria = List.of(
        		"prendi osso", "vai sud", "vai est", "vai est", "vai nord");

        ioSimulator = new IOSimulator(vittoria);
        labirinto = new Labirinto.LabirintoBuilder()
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
