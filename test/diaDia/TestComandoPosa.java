package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

class TestComandoPosa {
	
	
	private Comando comandoPosa;
	private Partita partita;
	private Stanza stanza;
	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private IO ioConsole = new IOConsole();
	
	@BeforeEach
	public void setUp() {
		// Creiamo la stanza, la borsa e la riempiamo con i 3 attrezzi
		comandoPosa = new ComandoPosa();
		borsa = new Borsa(3, ioConsole);
		stanza = new Stanza("test", ioConsole);
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		
		
		partita = new Partita(ioConsole);
		partita.setStanzaCorrente(stanza);
		partita.getGiocatore().setBorsa(borsa);
	}
	
	@Test
	void testPosaAttrezzoDaBorsaVuota() {
		comandoPosa.setParametro("A");	// Proviamo a posare l'attrezzo A
		comandoPosa.esegui(partita, ioConsole);
		assertFalse(borsa.hasAttrezzo("A")&&stanza.hasAttrezzo("A"));	// Verifichiamo che non si trovi nè in borsa nè in stanza
	}
	
	@Test
	void testPosaAttrezzoSingolo() {
		borsa.addAttrezzo(attrezzo1);
		comandoPosa.setParametro("A");	// Proviamo a posare l'attrezzo A
		comandoPosa.esegui(partita, ioConsole);
		assertTrue(!borsa.hasAttrezzo("A")&&stanza.hasAttrezzo("A"));	// Verifichiamo si trovi in stanza e non in borsa
	}
	
	@Test
	void testPosaDueAttrezzi() {
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
		comandoPosa.setParametro("A");
		comandoPosa.esegui(partita, ioConsole);
		assertTrue(stanza.hasAttrezzo("A")&&!borsa.hasAttrezzo("A"));
		comandoPosa.setParametro("B");
		comandoPosa.esegui(partita, ioConsole);
		assertTrue(stanza.hasAttrezzo("A")&&stanza.hasAttrezzo("B"));
	}
	
	
	@Test
	void testPosaInStanzaPiena() {
		int numeroAttrezzi = stanza.getAttrezzi().length;
		for (int i = 0; i < numeroAttrezzi; i++){
			Attrezzo attrezzo = new Attrezzo("A"+ i, 1);
			stanza.addAttrezzo(attrezzo);	
		}
		borsa.addAttrezzo(attrezzo1);
		comandoPosa.setParametro("A");
		comandoPosa.esegui(partita, ioConsole);
		assertTrue(!stanza.hasAttrezzo("A") && borsa.hasAttrezzo("A"));
	}

}
