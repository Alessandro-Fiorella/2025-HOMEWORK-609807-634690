package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita, IO ioConsole) {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
}
