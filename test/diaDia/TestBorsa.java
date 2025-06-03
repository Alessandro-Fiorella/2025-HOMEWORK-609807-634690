package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class TestBorsa {

	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private IO ioConsole = new IOConsole();

	
	@BeforeEach
	public void setUp() {
		borsa = new Borsa(30);
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		attrezzo3 = new Attrezzo("C", 3);
		DiaDia.setIO(ioConsole);
	}
	
	// Test: addAttrezzo
	@Test
	void testAddAttrezzo() {
		borsa.addAttrezzo(attrezzo1);
		assertTrue(borsa.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void testAdd3Attrezzi() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		assertEquals(borsa.getAttrezzo(0), attrezzo1);
		assertEquals(borsa.getAttrezzo(1), attrezzo2);
		assertEquals(borsa.getAttrezzo(2), attrezzo3);
	}
	
	@Test
	void testRemoveAttrezzoSuBorsaVuota() {
		borsa.removeAttrezzo(attrezzo1.getNome());
		assertFalse(borsa.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void testRemoveUnicoAttrezzo() {
		borsa.addAttrezzo(attrezzo1);
		assertEquals(borsa.removeAttrezzo(attrezzo1.getNome()), attrezzo1);
		assertNull(borsa.getAttrezzo(0));
	}
	
	@Test
	void testRemoveAttrezzoNonPresente(){
		borsa.addAttrezzo(attrezzo1);
		assertNull(borsa.removeAttrezzo("Pippo"));
	}
	
	//rimuove attrezzo in posizione randomica tra altri attrezzi, e controlla che ci siano gli altri attrezzi non rimossi
	@Test
	void testRemoveAttrezzoQualsiasi() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		assertEquals(borsa.removeAttrezzo(attrezzo2.getNome()), attrezzo2);
		assertEquals(borsa.getAttrezzo(0), attrezzo1);
		assertEquals(borsa.getAttrezzo(1), attrezzo3);
	}
	
	//rimuove l'ultimo attrezzo di una serie di attrezzi
	@Test
	void testRemoveUltimoAttrezzo() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		assertEquals(borsa.removeAttrezzo(attrezzo3.getNome()), attrezzo3);
		assertEquals(borsa.getAttrezzo(0), attrezzo1);
		assertEquals(borsa.getAttrezzo(1), attrezzo2);
	}
	
	
	@Test 
	void comparatorePerPesoPoiPerNome(){
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		Attrezzo attrezzo4 = new Attrezzo ("B", 1);
		borsa.addAttrezzo(attrezzo4);
		Attrezzo attrezzo5 = new Attrezzo ("A", 2);
		borsa.addAttrezzo(attrezzo5);
		Attrezzo attrezzo6 = new Attrezzo ("A", 3);
		borsa.addAttrezzo(attrezzo6);
		Attrezzo.ComparatorePerPesoPoiPerNome comparator = new Attrezzo.ComparatorePerPesoPoiPerNome();
		Collections.sort(borsa.getAttrezzi(), comparator);	
		// gli argomenti andrebbero invertiti dato che a sinistra c'è il valore atteso e a dx quello reale
		assertEquals(borsa.getAttrezzo(0), attrezzo1);
		assertEquals(borsa.getAttrezzo(1), attrezzo4);
		assertEquals(borsa.getAttrezzo(2), attrezzo5);
		assertEquals(borsa.getAttrezzo(3), attrezzo2);
		assertEquals(borsa.getAttrezzo(4), attrezzo6);
		assertEquals(borsa.getAttrezzo(5), attrezzo3);
	}
	
	
	/* Non ha più senso fare questo test dato che abbiamo rimosso la capienza massima
	@Test
	void testRemoveAttrezzoDaBorsaPiena() {
		int numeroAttrezzi = borsa.getAttrezzi().length;
		for (int i = 0; i < numeroAttrezzi; i++){
			Attrezzo attrezzo = new Attrezzo("Attrezzo"+ i, 1);
			borsa.addAttrezzo(attrezzo);	
		}
		Attrezzo daEliminare = borsa.getAttrezzi()[numeroAttrezzi-1];
		assertEquals(borsa.removeAttrezzo(daEliminare.getNome()), daEliminare);
	}*/
	
	/*
	//test rimuovi attrezzo random da borsa piena; serve solo per vedere come si muovono gli indici
	@Test
	void testRemoveAttrezzoACasoDaBorsaPiena() {
		int numeroAttrezzi = borsa.getAttrezzi().length;
		for (int i = 0; i < numeroAttrezzi; i++){
			Attrezzo attrezzo = new Attrezzo("Attrezzo"+ i, 1);
			borsa.addAttrezzo(attrezzo);	
		}
		ioConsole.mostraMessaggio(borsa.toString());
		Attrezzo daEliminare = borsa.getAttrezzi()[numeroAttrezzi-3];
		assertEquals(borsa.removeAttrezzo(daEliminare.getNome()), daEliminare);
		ioConsole.mostraMessaggio(borsa.toString());
	}
	*/
	
}
