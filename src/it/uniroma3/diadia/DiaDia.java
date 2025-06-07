package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.personaggi.*;

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
	
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
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
		Scanner scannerDiLinee = new Scanner(System.in);
		IO console = new IOConsole(scannerDiLinee);
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
								.addStanzaIniziale("Atrio")
								.addAttrezzo("osso", 3)
								.addPersonaggio(new Mago("Pic", 5))
								.addStanza("N11")
								.addPersonaggio(new Strega())
								.addStanza("N12")
								.addPersonaggio(new Cane())
								.addStanzaVincente("Biblioteca")
								.addAdiacenza("Atrio", "N11", "nord")
								.addAdiacenza("N11", "Atrio", "sud")
								.addAdiacenza("N11", "N12", "nord")
								.addAdiacenza("N12", "N11", "sud")
								.addAdiacenza("N12", "Biblioteca", "nord")
								.addAdiacenza("Biblioteca", "N12", "sud")
								.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, console);
		try {
			gioco.gioca();
		}
		catch (Exception e) {
			console.mostraMessaggio("Eccezione imprevista");
		}
		finally {
			scannerDiLinee.close();
		}
	}
}

