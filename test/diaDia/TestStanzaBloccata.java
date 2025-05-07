package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;

class TestStanzaBloccata {

	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente1;
	private Stanza stanzaAdiacente2;
	
	private IO ioConsole = new IOConsole();
	
	
	
	@BeforeEach
	public void setUp(){
		stanzaBloccata = new StanzaBloccata("Atrio", "nord", "A", ioConsole);
		stanzaAdiacente1 = new Stanza("N11", ioConsole);
		stanzaAdiacente2 = new Stanza("N12", ioConsole);
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacente1);
		stanzaBloccata.impostaStanzaAdiacente("sud", stanzaAdiacente2);

	}
	
	@Test
	void testStanzaBloccataSenzaOggetti(){
		assertEquals(stanzaBloccata.getStanzaAdiacente("nord"), stanzaBloccata);	// La stanza a nord deve essere bloccata
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"), stanzaAdiacente2);	//  La stanza a sud non è bloccata
	}
	
	@Test
	void testStanzaConOggettodiverso(){
		stanzaBloccata.addAttrezzo(attrezzo2);
		assertEquals(stanzaBloccata.getStanzaAdiacente("nord"), stanzaBloccata);	// La stanza a nord deve essere bloccata
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"), stanzaAdiacente2);	// La stanza a sud non è bloccata
	}
	
	@Test
	void testStanzaConSblocco(){
		stanzaBloccata.addAttrezzo(attrezzo1);
		stanzaBloccata.addAttrezzo(attrezzo2);
		assertEquals(stanzaBloccata.getStanzaAdiacente("nord"), stanzaAdiacente1);	// La stanza a nord deve essere sbloccata
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"), stanzaAdiacente2);	// La stanza a sud non è bloccata
		
	}
}
