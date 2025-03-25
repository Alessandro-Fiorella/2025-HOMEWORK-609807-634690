package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBorsa {

	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;

	
	@BeforeEach
	public void setUp() {
		borsa = new Borsa();
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		attrezzo3 = new Attrezzo("C", 3);
		
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
		assertEquals(borsa.getAttrezzi()[0], attrezzo1);
		assertEquals(borsa.getAttrezzi()[1], attrezzo2);
		assertEquals(borsa.getAttrezzi()[2], attrezzo3);
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
		assertNull(borsa.getAttrezzi()[0]);
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
		assertEquals(borsa.getAttrezzi()[0], attrezzo1);
		assertEquals(borsa.getAttrezzi()[1], attrezzo3);
	}
	
	//rimuove l'ultimo attrezzo di una serie di attrezzi
	@Test
	void testRemoveUltimoAttrezzo() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		assertEquals(borsa.removeAttrezzo(attrezzo3.getNome()), attrezzo3);
		assertEquals(borsa.getAttrezzi()[0], attrezzo1);
		assertEquals(borsa.getAttrezzi()[1], attrezzo2);
	}
	
	//rimuove l'ultimo da una borsa piena
	@Test
	void testRemoveAttrezzoDaBorsaPiena() {
		int numeroAttrezzi = borsa.getAttrezzi().length;
		for (int i = 0; i < numeroAttrezzi; i++){
			Attrezzo attrezzo = new Attrezzo("Attrezzo"+ i, 1);
			borsa.addAttrezzo(attrezzo);	
		}
		Attrezzo daEliminare = borsa.getAttrezzi()[numeroAttrezzi-1];
		assertEquals(borsa.removeAttrezzo(daEliminare.getNome()), daEliminare);
	}
	
	/*
	//test rimuovi attrezzo random da borsa piena; serve solo per vedere come si muovono gli indici
	@Test
	void testRemoveAttrezzoACasoDaBorsaPiena() {
		int numeroAttrezzi = borsa.getAttrezzi().length;
		for (int i = 0; i < numeroAttrezzi; i++){
			Attrezzo attrezzo = new Attrezzo("Attrezzo"+ i, 1);
			borsa.addAttrezzo(attrezzo);	
		}
		System.out.println(borsa.toString());
		Attrezzo daEliminare = borsa.getAttrezzi()[numeroAttrezzi-3];
		assertEquals(borsa.removeAttrezzo(daEliminare.getNome()), daEliminare);
		System.out.println(borsa.toString());
	}
	*/
}
