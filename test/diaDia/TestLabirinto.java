package diaDia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;

class TestLabirinto {

	private IO ioConsole = new IOConsole();
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto(ioConsole);
	}

	@Test
	void testStanzaIniziale() {
		assertNotNull(labirinto.getStanzaIniziale());
		assertEquals("Cortile", labirinto.getStanzaIniziale().getNome(), 
				"La stanza iniziale dovrebbe essere il Cortile");
	}

	@Test
	void testStanzaVincente() {
		assertNotNull(labirinto.getStanzaVincente());
		assertEquals("Aula Magna", labirinto.getStanzaVincente().getNome(), 
				"La stanza vincente dovrebbe essere la Aula Magna");
	}

	@Test
	void testStanzeAdiacentiAStanzaIniziale() {
		assertEquals("Mensa", labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome(), 
				labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
	}
}
