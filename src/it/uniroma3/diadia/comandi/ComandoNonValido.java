package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
		
	/*** esecuzione del comando */
	@Override
	public void esegui(Partita partita, IOConsole ioConsole) {
    	ioConsole.mostraMessaggio("Comando non valido, riprova!");
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "non valido";
	}
}
