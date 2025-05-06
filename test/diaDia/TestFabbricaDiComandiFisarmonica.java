package diaDia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class TestFabbricaDiComandiFisarmonica {
	
	FabbricaDiComandi factory;
	String nomeComando;
	String parametro;
	Comando comando;
	
	private IOConsole ioConsole = new IOConsole();
	
	@BeforeEach
	public void setUp() {
		factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void test() {
		nomeComando = "vai";
		comando = factory.costruisciComando(nomeComando, ioConsole);
		
	}

}
