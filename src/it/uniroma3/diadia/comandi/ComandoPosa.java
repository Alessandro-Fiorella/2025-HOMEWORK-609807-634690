package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	
	private String nomeAttrezzo;
	
	public ComandoPosa (IOConsole ioConsole) {
		
	}
	
	@Override
	public void esegui (Partita partita, IOConsole ioConsole){
		Attrezzo attrezzoDaPosare = partita.getBorsa().removeAttrezzo(nomeAttrezzo);
		if(attrezzoDaPosare != null){
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)){
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzoDaPosare.getNome() + " è stato rimosso dalla borsa e aggiunto alla stanza");
			}
			else{
				partita.getBorsa().addAttrezzo(attrezzoDaPosare);
				ioConsole.mostraMessaggio("L'attrezzo è tornato nella borsa'");
			}
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
