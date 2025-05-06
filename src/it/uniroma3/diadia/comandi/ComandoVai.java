package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	
	private String direzione;
	

	/*** esecuzione del comando */
	@Override
	public void esegui(Partita partita, IOConsole ioConsole) {
    	Stanza stanzaCorrente = partita.getStanzaCorrente();
    	Stanza prossimaStanza = null;
    	if (direzione==null) {
    		ioConsole.mostraMessaggio("Dove vuoi andare? Riscrivi vai specificando una posizione!"); //TODO: implementare in modo che che chiesa "in che direzione?"
        	return;
    	}
    	prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
    	if (prossimaStanza==null) {
    		ioConsole.mostraMessaggio("Direzione inesistente");
	        return;
	    }
    	partita.setStanzaCorrente(prossimaStanza);
    	ioConsole.mostraMessaggio(partita.getStanzaCorrente().getNome());
    	partita.getGiocatore().togliCfu();
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	@Override
	public String getParametro() {
		return direzione;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
}

