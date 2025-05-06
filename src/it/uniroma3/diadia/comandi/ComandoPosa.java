package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	
	private String nomeAttrezzo;
	
	
	@Override
	public void esegui (Partita partita, IO ioConsole){
		if (nomeAttrezzo == null) {	// Se nel comando "posa x" l'attrezzo non è stato specificato
			ioConsole.mostraMessaggio("Devi specificare l'attrezzo da posare!");
			return;
		}
		Attrezzo attrezzoDaPosare = partita.getBorsa().removeAttrezzo(nomeAttrezzo);	// Rimuove l'attrezzo dalla borsa
		if(attrezzoDaPosare != null){	// Se l'attrezzo è stato effettivamente trovato
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)){	// Se c'è spazio lo mette nella stanza (lo posa)
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzoDaPosare.getNome() + " è stato rimosso dalla borsa e aggiunto alla stanza");
			}
			else{	// Altrimenti no
				partita.getBorsa().addAttrezzo(attrezzoDaPosare);
				ioConsole.mostraMessaggio("L'attrezzo è tornato nella borsa perchè la stanza è piena");
			}
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
}
