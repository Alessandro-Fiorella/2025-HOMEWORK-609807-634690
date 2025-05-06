package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	static final private String[] elencoComandi = {	"vai: spostati da una stanza a un'altra", "posa: posa un oggetto nella stanza", 
													"prendi: prendi un oggetto dalla stanza", "guarda: ottieni info sulla partita", "fine: termina la partita",
													"aiuto: ottieni lista comandi"};

	@Override
	public void esegui(Partita partita, IO ioConsole) {
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i]+" ");
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
}
