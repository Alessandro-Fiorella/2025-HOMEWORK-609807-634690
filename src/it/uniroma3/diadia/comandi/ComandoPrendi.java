package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzo;
	
	
	@Override	
	public void esegui(Partita partita, IO ioConsole) {
		if (nomeAttrezzo == null) {
			ioConsole.mostraMessaggio("Devi specificare l'attrezzo da prendere!");
			return;
		}
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);	// Prova a prendere l'attrezzo dalla stanza
		if(attrezzoDaPrendere != null){	// Se lo ha effettivamente trovato
			if (partita.getBorsa().addAttrezzo(attrezzoDaPrendere)){	// Se c'è spazio lo mette in borsa
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzoDaPrendere.getNome() + " è stato rimosso dalla stanza e aggiunto alla borsa");
			}
			else{	// Altrimenti lo rimette in stanza
				partita.getStanzaCorrente().addAttrezzo(attrezzoDaPrendere);
				ioConsole.mostraMessaggio("L'attrezzo è tornato nella stanza'");
			}
		}	
	}
	@Override
	public void setParametro(String attrezzo) {
		this.nomeAttrezzo = attrezzo;
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
}


