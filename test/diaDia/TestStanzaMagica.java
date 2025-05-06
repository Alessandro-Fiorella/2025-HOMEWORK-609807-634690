package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaMagica {
	
	private StanzaMagica stanzaMagica;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	
	private IO ioConsole = new IOConsole();
	
	@BeforeEach
	public void setUp() {
		stanzaMagica = new StanzaMagica("prova", 2, 3, ioConsole);
		attrezzo1 = new Attrezzo("Aldo", 1);
		attrezzo2 = new Attrezzo("Boro", 2);
		attrezzo3 = new Attrezzo("Caro", 3);
		attrezzo4 = new Attrezzo("Dono", 4);		
	}
	
	@Test
	void testStanzaMagicaCompleto() { 
		stanzaMagica.addAttrezzo(attrezzo1);
		stanzaMagica.addAttrezzo(attrezzo2);
		stanzaMagica.addAttrezzo(attrezzo3);	// Il terzo elemento viene modificato
		assertTrue(stanzaMagica.hasAttrezzo("oraC")&&stanzaMagica.getAttrezzo("oraC").getPeso()==6);
		stanzaMagica.addAttrezzo(attrezzo4);	// Il quarto elemento non viene aggiunto dato che la stanza è piena
		assertEquals(stanzaMagica.getContatoreAttrezziPosati(), 3);
		Attrezzo newAttrezzo3 = stanzaMagica.getAttrezzo("oraC");
		stanzaMagica.removeAttrezzo("oraC");
		stanzaMagica.addAttrezzo(newAttrezzo3);	// 
		assertTrue(stanzaMagica.hasAttrezzo("Caro")&&stanzaMagica.getAttrezzo("Caro").getPeso()==12);	
	}
}
