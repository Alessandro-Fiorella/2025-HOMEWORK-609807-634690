package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

import it.uniroma3.diadia.attrezzi.Attrezzo;
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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi", "borsa", "stanza", "cfu"};

	private Partita partita;
	private IOConsole ioConsole;
	
	public DiaDia(IOConsole ioConsole) {
		this.ioConsole = ioConsole;
		this.partita = new Partita(ioConsole);
	}

	public void gioca() {
		String istruzione; 

		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
			istruzione = ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	 
	private boolean processaIstruzione(String istruzione) {
	
	Comando comandoDaEseguire;
	FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica(ioConsole);
	
	comandoDaEseguire = factory.costruisciComando(istruzione, ioConsole);
	comandoDaEseguire.esegui(this.partita, ioConsole);
	
	if (this.partita.vinta())
		ioConsole.mostraMessaggio("Hai vinto!");
		
	if (!this.partita.giocatoreisVivo())
		ioConsole.mostraMessaggio("Hai esaurito i CFU...");
		
	return this.partita.isFinita();
}
/*	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome() == null) return false;
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("borsa"))
			this.borsa();
		else if (comandoDaEseguire.getNome().equals("stanza"))
			this.stanza();
		else if (comandoDaEseguire.getNome().equals("cfu"))   //accorpare a stanza per creare guarda
			this.cfu();
		else
			ioConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   */

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().togliCfu();
		}
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	private void prendi (String nomeAttrezzo){
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
		if(attrezzoDaPrendere != null){
			if (partita.getBorsa().addAttrezzo(attrezzoDaPrendere)){
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzoDaPrendere.getNome() + " è stato rimosso dalla stanza e aggiunto alla borsa");
			}
			else{
				partita.getStanzaCorrente().addAttrezzo(attrezzoDaPrendere);
				ioConsole.mostraMessaggio("L'attrezzo è tornato nella stanza'");
			}
		}
	}
	
	private void posa (String nomeAttrezzo){
		Attrezzo attrezzoDaPosare = partita.getBorsa().removeAttrezzo(nomeAttrezzo);
		if(attrezzoDaPosare != null){
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)){
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzoDaPosare.getNome() + " è stato rimosso dalla borsa e aggiunto alla stanza");
			}
			else{
				partita.getBorsa().addAttrezzo(attrezzoDaPosare);
				ioConsole.mostraMessaggio("L'attrezzo è tornato nella borsa'");
			}
		}
	}
	
	private void borsa(){
		ioConsole.mostraMessaggio(partita.getBorsa().toString());
	}
	
	private void stanza(){
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().toString());
	}
	
	private void cfu() {
		ioConsole.mostraMessaggio("Hai " + partita.getGiocatore().getCfu() + " cfu");
	}
	
	public static void main(String[] argc) {
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}
	
	
	
}




