package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzo;
	
	public ComandoPrendi(IOConsole ioConsole) {
		
	}
	@Override	
	public void esegui(Partita partita, IOConsole ioConsole) {
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
		if(attrezzoDaPrendere != null){
			if (partita.getBorsa().addAttrezzo(attrezzoDaPrendere)){
				ioConsole.mostraMessaggio("L'attrezzo " + attrezzoDaPrendere.getNome() + " è stato rimosso dalla stanza e aggiunto alla borsa");
			}
			else{
				partita.getStanzaCorrente().addAttrezzo(attrezzoDaPrendere);
				ioConsole.mostraMessaggio("L'attrezzo è tornato nella stanza'");
			}
		}	
	}
	
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
