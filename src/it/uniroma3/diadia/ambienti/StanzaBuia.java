package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBuia extends Stanza{
	
	private String attrezzoSblocco;
	
	public StanzaBuia(String nome, int massimoAttrezzi, String attrezzoSblocco, IO ioConsole) {
		super(nome, massimoAttrezzi, ioConsole);
		this.attrezzoSblocco = attrezzoSblocco;
	}
	
	public StanzaBuia(String nome, String attrezzoSblocco, IO ioConsole) {
		this(nome, NUMERO_MASSIMO_ATTREZZI, attrezzoSblocco, ioConsole);
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(attrezzoSblocco))
			return this.toString();
		else
			return "Qui c'è buio pesto!";
	}
	
}
