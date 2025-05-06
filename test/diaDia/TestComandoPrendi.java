package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class TestComandoPrendi {

	private Comando comandoPrendi;
	private Stanza stanza;
	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private IO ioConsole = new IOConsole();
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		// Creiamo la borsa, la stanza e i 3 attrezzi
		comandoPrendi = new ComandoPrendi();
		borsa = new Borsa(3, ioConsole);
		stanza = new Stanza("test", ioConsole);
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		attrezzo3 = new Attrezzo("C", 3);
		
		partita = new Partita(ioConsole);
		partita.setStanzaCorrente(stanza);
		partita.getGiocatore().setBorsa(borsa);
		
	}
	
	@Test
	void testPrendiAttrezzoStanzaVuota() {
		comandoPrendi.setParametro("A");
		comandoPrendi.esegui(partita, ioConsole);
		assertFalse(borsa.hasAttrezzo("A")&&stanza.hasAttrezzo("A"));
	}
	
	@Test
	void testPrendiAttrezzoSingolo() {
		stanza.addAttrezzo(attrezzo1);
		comandoPrendi.setParametro("A");
		comandoPrendi.esegui(partita, ioConsole);
		assertTrue(borsa.hasAttrezzo("A")&&!stanza.hasAttrezzo("A"));
	}
	
	@Test
	void testPosaDueAttrezzi() {
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
