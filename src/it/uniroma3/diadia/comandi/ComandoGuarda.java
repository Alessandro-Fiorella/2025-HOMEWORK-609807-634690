package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	
	@Override
	public void esegui(Partita partita, IOConsole ioConsole) {
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().toString());
		ioConsole.mostraMessaggio("Hai " + partita.getGiocatore().getCfu() + " cfu");
		ioConsole.mostraMessaggio(partita.getBorsa().toString());
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
}
