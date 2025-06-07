package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class TestComandoPrendi {

	private AbstractComando comandoPrendi;
	private Stanza stanza;
	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Labirinto labirinto;
	private IO ioConsole = new IOConsole();
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		// Creiamo la borsa, la stanza e i 3 attrezzi
		comandoPrendi = new ComandoPrendi();
		borsa = new Borsa(3);	// Peso massimo 3
		stanza = new Stanza("test");
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		attrezzo3 = new Attrezzo("C", 3);
		labirinto = new Labirinto.LabirintoBuilder()
				.addStanzaIniziale(stanza)
				.getLabirinto();	
		partita = new Partita(labirinto);
		partita.getGiocatore().setBorsa(borsa);
		
		DiaDia.setIO(ioConsole);	// Soluzione temporanea sporca
	}
	
	@Test
	void testPrendiAttrezzoStanzaVuota() {
		comandoPrendi.setParametro("A");
		comandoPrendi.esegui(partita, ioConsole);
		assertTrue(!borsa.hasAttrezzo("A")&&!stanza.hasAttrezzo("A"));	// L'oggetto non è stato spostato in borsa nè creato per sbaglio in stanza
	}
	
	@Test
	void testPrendiAttrezzoSingolo() {
		stanza.addAttrezzo(attrezzo1);
		comandoPrendi.setParametro("A");
		comandoPrendi.esegui(partita, ioConsole);
		assertTrue(borsa.hasAttrezzo("A")&&!stanza.hasAttrezzo("A"));
	}
	
	@Test
	void testPrendiDueAttrezzi() {
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		comandoPrendi.setParametro("A");
		comandoPrendi.esegui(partita, ioConsole);
		assertTrue(borsa.hasAttrezzo("A")&&!stanza.hasAttrezzo("A"));
		comandoPrendi.setParametro("B");
		comandoPrendi.esegui(partita, ioConsole);
		assertTrue(borsa.hasAttrezzo("A")&&borsa.hasAttrezzo("B"));
	}
	
	
	@Test
	void testPrendiInBorsaPiena() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		stanza.addAttrezzo(attrezzo3);
		comandoPrendi.setParametro("C");
		comandoPrendi.esegui(partita, ioConsole);
		assertTrue(!borsa.hasAttrezzo("C") && stanza.hasAttrezzo("C") && borsa.hasAttrezzo("B"));
	}

}
