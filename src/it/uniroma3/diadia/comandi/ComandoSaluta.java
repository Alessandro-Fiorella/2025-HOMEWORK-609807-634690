package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_CHI = "Chi devo salutare?";	
	
	@Override
	public void esegui(Partita partita, IO ioConsole) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null)
			ioConsole.mostraMessaggio(MESSAGGIO_CHI);
		else 
			ioConsole.mostraMessaggio(personaggio.saluta());
	}

	@Override
	public String getNome() {
		return "saluta";
	}
}
