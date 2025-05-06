package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita, IOConsole ioConsole) {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	
	@Override
	public void setParametro(String parametro) {
		
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
}
