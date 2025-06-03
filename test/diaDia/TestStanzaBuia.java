package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class Punto {
	int x, y;
	Punto(int x, int y){
		this.x=x;
		this.y=y;
	}
}

class TestStanzaBuia {
	
	private StanzaBuia stanzaBuia;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	Punto p1 = new Punto(1,1);
	Punto p2 = new Punto(1,1);
	String a = "A";
	String b = "A";
		
	
	@BeforeEach
	public void setUp() {
		stanzaBuia = new StanzaBuia("prova", "A");
		attrezzo1 = new Attrezzo("A", 1);
		attrezzo2 = new Attrezzo("B", 2);
		stanzaBuia.addAttrezzo(attrezzo1);
		stanzaBuia.addAttrezzo(attrezzo2);
	}
	
	@Test
	public void testStanzaBuiaCompleto() {
		assertNotEquals(stanzaBuia.getDescrizione(), "Qui c'è buio pesto!");	// Non deve venire stampato "Qui c'è buio pesto!"
		stanzaBuia.removeAttrezzo("B");
		assertNotEquals(stanzaBuia.getDescrizione(), "Qui c'è buio pesto!");	// Dopo aver preso un altro oggetto non deve ancora venire stampato
		stanzaBuia.removeAttrezzo("A");
		assertEquals(stanzaBuia.getDescrizione(), "Qui c'è buio pesto!");		// Dopo aver preso l'oggetto bloccante, deve apparire "Qui c'è buio pesto!"
	}
}
