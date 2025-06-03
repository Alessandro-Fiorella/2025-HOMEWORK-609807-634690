package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI =

			"Con chi dovrei interagire?...";

	private String messaggio;

	@Override
	public void esegui(Partita partita, IO ioConsole) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null)
			ioConsole.mostraMessaggio(MESSAGGIO_CON_CHI);
		else
			ioConsole.mostraMessaggio(personaggio.agisci(partita));
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	
	@Override
	public String getNome() {
		return "interagisci";
	}
}
