package diaDia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanza {
	private Stanza stanzaIniziale;
	private Stanza stanzaAdiacente;
	private Stanza stanzaSovrascritta;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private IO ioConsole = new IOConsole();
	
	@BeforeEach
	public void setUp() {
		stanzaIniziale = new Stanza("N10",ioConsole);
		stanzaAdiacente = new Stanza("N11",ioConsole);
		stanzaSovrascritta = new Stanza("N12",ioConsole);
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		attrezzo3 = new Attrezzo("C", 3);
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
	@Test
	void testRemoveAttrezzoSuStanzaVuota() {
		stanzaIniziale.removeAttrezzo(attrezzo1.getNome());
		assertFalse(stanzaIniziale.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void testRemoveUnicoAttrezzo() {
		stanzaIniziale.addAttrezzo(attrezzo1);
		assertEquals(stanzaIniziale.removeAttrezzo(attrezzo1.getNome()), attrezzo1);
		assertNull(stanzaIniziale.getAttrezzi()[0]);
	}
	
	@Test
	void testRemoveAttrezzoNonPresente(){
		stanzaIniziale.addAttrezzo(attrezzo1);
		assertNull(stanzaIniziale.removeAttrezzo("Paperino"));
	}
	
	//rimuove attrezzo in posizione randomica tra altri attrezzi, e controlla che ci siano gli altri attrezzi non rimossi
	@Test
	void testRemoveAttrezzoQualsiasi() {
		stanzaIniziale.addAttrezzo(attrezzo1);
		stanzaIniziale.addAttrezzo(attrezzo2);
		stanzaIniziale.addAttrezzo(attrezzo3);
		assertEquals(stanzaIniziale.removeAttrezzo(attrezzo2.getNome()), attrezzo2);
		assertEquals(stanzaIniziale.getAttrezzi()[0], attrezzo1);
		assertEquals(stanzaIniziale.getAttrezzi()[1], attrezzo3);
	}
	
	//rimuove l'ultimo attrezzo di una serie di attrezzi
	@Test
	void testRemoveUltimoAttrezzo() {
		stanzaIniziale.addAttrezzo(attrezzo1);
		stanzaIniziale.addAttrezzo(attrezzo2);
		stanzaIniziale.addAttrezzo(attrezzo3);
		assertEquals(stanzaIniziale.removeAttrezzo(attrezzo3.getNome()), attrezzo3);
		assertEquals(stanzaIniziale.getAttrezzi()[0], attrezzo1);
		assertEquals(stanzaIniziale.getAttrezzi()[1], attrezzo2);
	}
	
	//rimuove l'ultimo da una stanza piena
	@Test
	void testRemoveAttrezzoDaStanzaPiena() {
		int numeroAttrezzi = stanzaIniziale.getAttrezzi().length;
		for (int i = 0; i < numeroAttrezzi; i++){
			Attrezzo attrezzo = new Attrezzo("Attrezzo"+ i, 1);
			stanzaIniziale.addAttrezzo(attrezzo);	
		}
		Attrezzo daEliminare = stanzaIniziale.getAttrezzi()[numeroAttrezzi-1];
		assertEquals(stanzaIniziale.removeAttrezzo(daEliminare.getNome()), daEliminare);
	}
}
