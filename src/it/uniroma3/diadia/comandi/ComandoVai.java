package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	
	private String direzione;
	
	public ComandoVai (IOConsole ioConsole) {
		
	}

	/*** esecuzione del comando */
	@Override
	public void esegui(Partita partita, IOConsole ioConsole) {
    	Stanza stanzaCorrente = partita.getStanzaCorrente();
    	Stanza prossimaStanza = null;
    	if (direzione==null) {
    		ioConsole.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
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
}
