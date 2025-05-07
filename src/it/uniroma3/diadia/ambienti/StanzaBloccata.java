package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoSblocco;
	
	public StanzaBloccata(String nome, int massimoAttrezzi, String direzioneBloccata, String attrezzoSblocco, IO ioConsole){
		super(nome, massimoAttrezzi, ioConsole);	// Uso il costruttore della stanza
		this.direzioneBloccata = direzioneBloccata; //direzione bloccata dalla stanza
		this.attrezzoSblocco = attrezzoSblocco; //attrezzio con cui sbloccare
	}
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSblocco, IO ioConsole) {
		this(nome, NUMERO_MASSIMO_ATTREZZI, direzioneBloccata, attrezzoSblocco, ioConsole);
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione){
		if(!this.hasAttrezzo(attrezzoSblocco)&&direzione.equals(direzioneBloccata)) return this;
	
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return toString()+"\n"+"La direzione "+direzioneBloccata+" è bloccata";
	}
	
}
