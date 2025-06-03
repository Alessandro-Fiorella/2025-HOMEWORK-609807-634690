package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class TestFabbricaDiComandiFisarmonica {
	
	FabbricaDiComandi factory;
	String nomeComando;
	String parametro;
	AbstractComando comando;
		
	@BeforeEach
	public void setUp() {
		factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testComandoVai() {
		nomeComando = "vai";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "vai");
	}
	
	@Test
	void testComandoPrendi() {
		nomeComando = "prendi";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "prendi");
	}
	
	@Test
	void testComandoPosa() {
		nomeComando = "posa";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "posa");
	}
	
	@Test
	void testComandoGuarda() {
		nomeComando = "guarda";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "guarda");
	}
	
	@Test
	void testComandoFine() {
		nomeComando = "fine";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "fine");
	}
	
	@Test
	void testComandoAiuto() {
		nomeComando = "aiuto";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "aiuto");
	}
	
	@Test
	void testComandoNonValido() {
		nomeComando = "caso";
		comando = factory.costruisciComando(nomeComando);
		assertEquals(comando.getNome(), "non valido");
	}

}
