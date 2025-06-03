package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	
	private String direzione;
	

	/*** esecuzione del comando */
	@Override
	public void esegui(Partita partita, IO ioConsole) {
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
	public void setParametro(String direzione) {
		this.direzione = direzione;
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
}

