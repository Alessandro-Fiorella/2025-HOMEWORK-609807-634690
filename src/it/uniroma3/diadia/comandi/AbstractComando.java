package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Questa classe implementa l'interfacci comando fornendo 
 * un funzionamento basico dei metodi set/getParametro, utili 
 * per le classi Comando che non utilizzando parametri ma che implementando Comando
 * dovevano comunque fornire un'implementazione vuota di questi metodi
 */
public abstract class AbstractComando{

	private String parametro;

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getParametro() {
		return this.parametro;
	}

	public abstract void esegui(Partita partita, IO io);

	public abstract String getNome();
}
