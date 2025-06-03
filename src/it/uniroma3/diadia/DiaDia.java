package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private Partita partita;
	private static IO console;
	
	public DiaDia(Labirinto labirinto, IO console) {
		DiaDia.console = console;
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	 
	private boolean processaIstruzione(String istruzione) {
	
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione, console);
		comandoDaEseguire.esegui(this.partita, console);
		
		if (this.partita.vinta())
			console.mostraMessaggio("Hai vinto!");
			
		if (!this.partita.giocatoreisVivo())
			console.mostraMessaggio("Hai esaurito i CFU...");
			
		return this.partita.isFinita();
	}
	
	public Partita getPartita() {
		return partita;
	}
	
	public static IO getIO() {
		return console;
	}
	
	public static void setIO(IO io) {
		console = io;
	}


	public static void main(String[] argc) {
		IO console = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
								.addStanzaIniziale("Atrio")
								.addAttrezzo("osso", 3)
								.addStanzaVincente("Biblioteca")
								.addAdiacenza("Atrio", "Biblioteca", "nord")
								.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, console);
		gioco.gioca();
	}
}

