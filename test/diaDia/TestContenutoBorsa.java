package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class TestContenutoBorsa {
	private Borsa borsa;
	private Attrezzo piombo;
	private Attrezzo piuma;
	private Attrezzo libro;
	private Attrezzo ps;


	@BeforeEach
	public void setUp() {
		borsa = new Borsa(30);
		piombo = new Attrezzo("piombo", 10);
		piuma = new Attrezzo("piuma", 1);
		libro = new Attrezzo("libro", 5);
		ps = new Attrezzo("ps", 5);

		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(piuma);
		borsa.addAttrezzo(libro);
		borsa.addAttrezzo(ps);
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinati = borsa.getContenutoOrdinatoPerPeso();

		assertEquals(4, ordinati.size());
		assertEquals(piuma, ordinati.get(0));
		assertEquals(piombo, ordinati.get(3));
		assertTrue(ordinati.indexOf(libro) < ordinati.indexOf(piombo));
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> ordinati = borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> i = ordinati.iterator();
		assertEquals(libro, i.next());
		assertEquals(piombo, i.next());
		assertEquals(piuma, i.next());
		assertEquals(ps, i.next());
		}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();

		assertEquals(3, mappa.size());
		assertTrue(mappa.containsKey(1));
		assertTrue(mappa.containsKey(5));
		assertTrue(mappa.containsKey(10));

		assertEquals(Set.of(piuma), mappa.get(1));
		assertEquals(Set.of(libro, ps), mappa.get(5));
		assertEquals(Set.of(piombo), mappa.get(10));
	}
}
