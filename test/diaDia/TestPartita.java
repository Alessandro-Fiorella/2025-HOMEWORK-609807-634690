package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.BeforeEach;

class TestPartita {
	private Partita partita;
	private Labirinto labirinto;

	
	@BeforeEach
	public void setUp() {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		partita = new Partita(labirinto);
	}
	
	@Test
	void testCFUInizioPartita() {
	    assertEquals(20, partita.getGiocatore().getCfu(), "I CFU iniziali devono essere 20");
	}
	@Test
	void testStatoInizioPartita() {
	  	assertFalse(partita.isFinita(), "La partita è impostata finita fin dall'inizio");
	}
	
	// Test: funzione vinta
	@Test
	void testPartitaVinta() {
		partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	void testPartitaDaVincere() {
		assertFalse(partita.vinta());
	}
	
	// Test: fine partita e funzione isFinita
	@Test
	void testCFUFiniti() {
		partita.getGiocatore().setCfu(0);	//Si nota anche il corretto funzionamento di setCFU
		assertTrue(partita.isFinita());
	}
	@Test
	void testPartitaFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	@Test
	void testPartitaFinitaPerchéVinta() {
		partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.isFinita());
	}
	@Test
	void testPartitaNonTerminata() {
		partita.getGiocatore().setCfu(15);
		partita.setStanzaCorrente(new Stanza("Nuova Stanza"));
		assertFalse(partita.isFinita());
	}
	
	// Test: funzione setStanzaCorrente
	@Test
	void testImpostaStanzaCorrente() {	    
	    Stanza nuovaStanza = new Stanza("Nuova Stanza");
	    partita.setStanzaCorrente(nuovaStanza);	    
	    assertEquals("Nuova Stanza", partita.getStanzaCorrente().getNome(), "La stanza corrente dovrebbe essere la 'Nuova Stanza'");
	}
	
}
