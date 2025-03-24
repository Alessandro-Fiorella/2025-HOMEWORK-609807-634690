package diaDia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStanza {
	private Stanza stanzaIniziale;
	private Stanza stanzaAdiacente;
	private Stanza stanzaSovrascritta;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	
	@BeforeEach
	public void setUp() {
		stanzaIniziale = new Stanza("N10");
		stanzaAdiacente = new Stanza("N11");
		stanzaSovrascritta = new Stanza("N12");
		attrezzo1 = new Attrezzo("Martello", 10);
		attrezzo2 = new Attrezzo("Cacciavite", 20);
	}
	
	// Test: impostaStanzaAdiacente
	@Test
	void testStanzeAdiacenti_InizialmenteNull() {
		assertNull(stanzaIniziale.getStanzaAdiacente("nord"));
		assertNull(stanzaIniziale.getStanzaAdiacente("sud"));
		assertNull(stanzaIniziale.getStanzaAdiacente("est"));
		assertNull(stanzaIniziale.getStanzaAdiacente("ovest"));
	}
	@Test
	void testImposta_StanzaAdiacente() {
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanzaAdiacente, stanzaIniziale.getStanzaAdiacente("nord"));
	}	
	
	@Test
	void testSovrascrittura_StanzaAdiacente() {
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaAdiacente);
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaSovrascritta);
		assertEquals(stanzaSovrascritta, stanzaIniziale.getStanzaAdiacente("nord"));
		assertNotEquals(stanzaAdiacente, stanzaIniziale.getStanzaAdiacente("nord"));
	}
	
	
	// test: addAttrezzo
	@Test
	void testStanzaSenzaAttrezzi() {
		assertNotNull(stanzaIniziale.getAttrezzi(), "L'array dovrebeb essere inizializzato");
		assertNull(stanzaIniziale.getAttrezzi()[0], "L'array dovrebbe essere vuoto");
	}
	
	@Test
	void testAddAttrezzo() {
		stanzaIniziale.addAttrezzo(attrezzo1);
		assertTrue(stanzaIniziale.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void testAddPiùAttrezzi() {
		stanzaIniziale.addAttrezzo(attrezzo1);
		stanzaIniziale.addAttrezzo(attrezzo2);
		assertTrue(stanzaIniziale.hasAttrezzo(attrezzo1.getNome()));
		assertTrue(stanzaIniziale.hasAttrezzo(attrezzo2.getNome()));
	}
	
	@Test
	void testRiempimentoStanza() {
		for (int i = 0; i < stanzaIniziale.getAttrezzi().length; i++) {
			stanzaIniziale.addAttrezzo(new Attrezzo("Attrezzo"+ i, i));	// Riempio la stanza di attrezzi
		}
		assertFalse(stanzaIniziale.addAttrezzo(attrezzo1), "Dovrebbe essere false dato che la stanza è piena");
	}

}
