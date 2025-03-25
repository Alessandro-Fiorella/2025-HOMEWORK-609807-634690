package diaDia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class TestLabirinto {
	
	private Labirinto labirinto;
	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
	}
	// Test: Inizio partita e creaStanze
		
	@Test
	void testStanzaIniziale() {
		assertNotNull(labirinto.getStanzaIniziale());
	  	assertEquals("Atrio", labirinto.getStanzaIniziale().getNome(), "La stanza corrente dovrebbe essere l'atrio");
	}
	@Test
	void testStanzaVincente() {
		assertNotNull(labirinto.getStanzaVincente());
	    assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome(), 
	    		"La stanza vincente dovrebbe essere la biblioteca");
	}
	
	@Test
	void testStanzeAdiacentiAStanzaIniziale() {
		assertEquals("Biblioteca", labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
		assertEquals("Aula N10", labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
		assertEquals("Aula N11", labirinto.getStanzaIniziale().getStanzaAdiacente("est").getNome());
		assertEquals("Laboratorio Campus", labirinto.getStanzaIniziale().getStanzaAdiacente("ovest").getNome());
	}

}