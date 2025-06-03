package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
		
	/*** esecuzione del comando */
	@Override
	public void esegui(Partita partita, IO ioConsole) {
    	ioConsole.mostraMessaggio("Comando non valido, riprova!");
	}
	
	@Override
	public String getNome() {
		return "non valido";
	}
}
